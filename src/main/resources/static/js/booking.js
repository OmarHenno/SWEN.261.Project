window.onload = loadBookings;

function loadBookings() {
    fetch('/booking')
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to load bookings');
            }

            return data;
        })
        .then(bookings => {
            const tableBody = document.getElementById('bookingTableBody');
            tableBody.innerHTML = '';

            if (!bookings || bookings.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="5">No bookings found.</td></tr>';
                return;
            }

            bookings.forEach(booking => {
                let flightsText = '';

                if (booking.items && booking.items.length > 0) {
                    flightsText = booking.items
                        .map(item =>
                            `${item.flightNumber} (${item.quantity} ${item.quantity === 1 ? 'ticket' : 'tickets'})`
                        )
                        .join(', ');
                }

                let actionButton = '';

                if (booking.status === 'CANCELLED') {
                    actionButton = `
                        <button onclick="uncancelBooking('${booking.bookingId}')">
                            Uncancel
                        </button>
                    `;
                } else {
                    actionButton = `
                        <button onclick="cancelBooking('${booking.bookingId}')">
                            Cancel
                        </button>
                    `;
                }

                const actionsHtml = `
                    ${actionButton}
                    <button onclick="deleteBooking('${booking.bookingId}')" style="margin-left: 6px;">
                        Delete
                    </button>
                `;

                tableBody.innerHTML += `
                    <tr>
                        <td>${booking.bookingId}</td>
                        <td>${booking.status}</td>
                        <td>${booking.totalAmount}</td>
                        <td>${flightsText}</td>
                        <td>${actionsHtml}</td>
                    </tr>
                `;
            });
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}

function cancelBooking(bookingId) {
    fetch(`/booking/${bookingId}/update`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status: 'CANCELLED' })
    })
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to cancel booking');
            }

            return data;
        })
        .then(() => {
            document.getElementById('message').innerText = 'Booking cancelled';
            loadBookings();
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}

function uncancelBooking(bookingId) {
    fetch(`/booking/${bookingId}/update`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status: 'CONFIRMED' })
    })
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to restore booking');
            }

            return data;
        })
        .then(() => {
            document.getElementById('message').innerText = 'Booking restored';
            loadBookings();
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}

function deleteBooking(bookingId) {
    fetch(`/booking/${bookingId}/delete`, {
        method: 'DELETE'
    })
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to delete booking');
            }

            return data;
        })
        .then(data => {
            document.getElementById('message').innerText = data.message;
            loadBookings();
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}