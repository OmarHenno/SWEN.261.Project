function renderTable(flights) {
    const tableBody = document.getElementById("flightsTableBody");
    const message = document.getElementById("message");

    tableBody.innerHTML = "";

    if (!flights || flights.length === 0) {
        message.textContent = "No flights found matching your search.";
        return;
    }

    message.textContent = "";

    flights.forEach(function (flight) {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${flight.flightNumber}</td>
            <td>${flight.destination}</td>
            <td>${flight.category}</td>
            <td>${flight.formattedDepartureTime}</td>
            <td>${flight.seatsAvailable}</td>
            <td>${flight.price}</td>
            <td>
                 <button onclick="showEditForm('${flight.flightNumber}')" style="background:#2196F3;color:white;margin-right:5px;">Edit</button>
                 <button onclick="confirmDelete('${flight.flightNumber}')" style="background:#f44336;color:white;">Delete</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

function loadAllFlights() {
    fetch("/api/flights")
        .then(response => response.json())
        .then(data => renderTable(data))
        .catch(error => document.getElementById("message").textContent = "Error loading flights.");
}

function searchByDestination() {
    const keyword = document.getElementById("destInput").value;

    fetch(`/api/flights/search/name?keyword=${encodeURIComponent(keyword)}`)
        .then(response => response.json())
        .then(data => renderTable(data))
        .catch(error => document.getElementById("message").textContent = "Error searching destination.");
}

function searchByCategory() {
    const category = document.getElementById("categoryInput").value;

    if (category === "") {
        loadAllFlights();
        return;
    }

    fetch(`/api/flights/search/category?category=${encodeURIComponent(category)}`)
        .then(response => response.json())
        .then(data => renderTable(data))
        .catch(error => document.getElementById("message").textContent = "Error searching category.");
}



window.onload = loadAllFlights;

function showAddForm() {
    document.getElementById("formTitle").textContent = "Add New Flight";
    document.getElementById("editingFlightNumber").value = "";
    document.getElementById("fFlightNumber").value = "";
    document.getElementById("fFlightNumber").disabled = false;
    document.getElementById("fDestination").value = "";
    document.getElementById("fCategory").value = "International";
    document.getElementById("fDepartureTime").value = "";
    document.getElementById("fSeats").value = "";
    document.getElementById("fPrice").value = "";
    document.getElementById("flightForm").style.display = "block";
}

function showEditForm(flightNumber) {
    fetch(`/api/flights/${flightNumber}`)
        .then(response => response.json())
        .then(flight => {
            document.getElementById("formTitle").textContent = "Edit Flight";
            document.getElementById("editingFlightNumber").value = flight.flightNumber;
            document.getElementById("fFlightNumber").value = flight.flightNumber;
            document.getElementById("fFlightNumber").disabled = true;
            document.getElementById("fDestination").value = flight.destination;
            document.getElementById("fCategory").value = flight.category;
            document.getElementById("fSeats").value = flight.seatsAvailable;
            document.getElementById("fPrice").value = flight.price;
            document.getElementById("flightForm").style.display = "block";
        });
}

function submitFlightForm() {
    const editingId = document.getElementById("editingFlightNumber").value;
    const dateValue = document.getElementById("fDepartureTime").value;

    const flightData = {
        flightNumber: document.getElementById("fFlightNumber").value,
        destination: document.getElementById("fDestination").value,
        category: document.getElementById("fCategory").value,
        departureTime: dateValue ? dateValue + ":00" : null,
        seats: parseInt(document.getElementById("fSeats").value),
        price: parseFloat(document.getElementById("fPrice").value)
    };

    const isEdit = editingId !== "";
    const url = isEdit ? `/flights/${editingId}/update` : `/flights/add`;
    const method = isEdit ? "PUT" : "POST";

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(flightData)
    })
        .then(response => response.json())
        .then(() => {
            const msg = isEdit ? "Flight updated successfully!" : "Flight added successfully!";
            document.getElementById("message").textContent = msg;
            document.getElementById("flightForm").style.display = "none";
            loadAllFlights();
        })
        .catch(() => document.getElementById("message").textContent = "Error saving flight.");
}

function confirmDelete(flightNumber) {
    if (confirm(`Are you sure you want to delete flight ${flightNumber}?`)) {
        fetch(`/flights/${flightNumber}/delete`, { method: "DELETE" })
            .then(response => response.json())
            .then(() => {
                document.getElementById("message").textContent = `Flight ${flightNumber} deleted successfully!`;
                loadAllFlights();
            })
            .catch(() => document.getElementById("message").textContent = "Error deleting flight.");
    }
}

function cancelForm() {
    document.getElementById("flightForm").style.display = "none";
}