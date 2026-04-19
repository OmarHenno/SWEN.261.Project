let currentUser = null;

function login() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const message = document.getElementById("message");
    const roleMessage = document.getElementById("roleMessage");

    if (!username || !password) {
        message.textContent = "Please enter both username and password.";
        roleMessage.textContent = "";
        return;
    }

    fetch("/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    })
        .then(function (response) {
            if (!response.ok) {
                return response.json().then(function (data) {
                    throw new Error(data.message);
                });
            }
            return response.json();
        })
        .then(function (user) {
            currentUser = user;
            message.textContent = "Login successful.";
            updateRoleBasedUI();
        })
        .catch(function (error) {
            currentUser = null;
            message.textContent = error.message;
            roleMessage.textContent = "";
        });
}

function logout() {
    const message = document.getElementById("message");
    const roleMessage = document.getElementById("roleMessage");

    fetch("/auth/logout", {
        method: "POST"
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            currentUser = null;
            message.textContent = data.message;
            roleMessage.textContent = "";
            updateRoleBasedUI();
        })
        .catch(function () {
            message.textContent = "Logout failed.";
        });
}

function updateRoleBasedUI() {
    const roleMessage = document.getElementById("roleMessage");

    if (!currentUser) {
        roleMessage.textContent = "No user logged in.";
        return;
    }

    if (currentUser.role === "ADMIN") {
        roleMessage.textContent = "Logged in as ADMIN. You can manage flights.";
    } else if (currentUser.role === "CUSTOMER") {
        roleMessage.textContent = "Logged in as CUSTOMER. You can browse flights, manage cart, and book.";
    }
}

function loadCurrentUser() {
    fetch("/auth/current-user")
        .then(function (response) {
            if (!response.ok) {
                return null;
            }
            return response.json();
        })
        .then(function (user) {
            if (user) {
                currentUser = user;
                document.getElementById("message").textContent = "User session restored.";
                updateRoleBasedUI();
            }
        })
        .catch(function () {
            document.getElementById("message").textContent = "";
        });
}

window.onload = function () {
    loadCurrentUser();
};