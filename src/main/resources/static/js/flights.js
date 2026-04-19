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