window.onload = loadCart;

function loadCart() {
    fetch('/cart')
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to load cart');
            }

            return data;
        })
        .then(cart => {
            const tableBody = document.getElementById('cartTableBody');
            tableBody.innerHTML = '';

            if (!cart.items || cart.items.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="5">Your cart is empty.</td></tr>';
                return;
            }

            cart.items.forEach(item => {
                tableBody.innerHTML += `
                    <tr>
                        <td>${item.flightNumber}</td>
                        <td>
                            <input type="number" min="1" value="${item.quantity}" id="qty-${item.flightNumber}">
                            <button onclick="updateQuantity('${item.flightNumber}')">Update</button>
                        </td>
                        <td>${item.price}</td>
                        <td>${item.price * item.quantity}</td>
                        <td>
                            <button onclick="removeFromCart('${item.flightNumber}')">Remove</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}

function updateQuantity(flightNumber) {
    const quantity = parseInt(document.getElementById(`qty-${flightNumber}`).value);

    fetch('/cart/update', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ flightNumber, quantity })
    })
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to update quantity');
            }

            return data;
        })
        .then(() => {
            document.getElementById('message').innerText = 'Quantity updated';
            loadCart();
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}

function removeFromCart(flightNumber) {
    fetch(`/cart/remove?flightNumber=${encodeURIComponent(flightNumber)}`, {
        method: 'DELETE'
    })
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to remove item');
            }

            return data;
        })
        .then(data => {
            document.getElementById('message').innerText = data.message;
            loadCart();
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}

function clearCart() {
    fetch('/cart/clear', {
        method: 'DELETE'
    })
        .then(async response => {
            const data = await response.json().catch(() => null);

            if (!response.ok) {
                throw new Error(data?.message || 'Failed to clear cart');
            }

            return data;
        })
        .then(data => {
            document.getElementById('message').innerText = data.message;
            loadCart();
        })
        .catch(error => {
            document.getElementById('message').innerText = error.message;
        });
}