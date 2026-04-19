# Wandria
## Members
1. Omar Henno - 406000628
2. Madya Alfalasi - 390006108
3. Joy AlMekha - 768008942
4. Dhabya Alsuwaidi - 402008006
5. Ahmad Younes - 406000511



## Product Vision
FOR people who frequently travel or plan trips online
WHO need a simple, fast, and trustworthy way to purchase plane tickets without unnecessary complexity
THE Wandria Booking Website IS A modern, ad-free online booking platform
THAT simplifies the booking process into no more than three steps, while offering mileage-based discounts
UNLIKE traditional online travel agencies
OUR PRODUCT provides short connection, full transparency, a user-friendly interface, and a responsive design optimized for all devices


## Presentation
https://www.canva.com/design/DAHARy3NtbU/OmUiFDtqMBOtSEhcH9s8VQ/edit?utm_content=DAHARy3NtbU&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton 




## Assignment 1 Progress
### Completed:
- [x] Domain Analysis & Class Diagram
- [x] Glossary of Terms
- [x] Functional Requirements Document
- [x] Traceability Matrix
- [x] User Stories Definition (Total: 13 stories)
- [x] Sprint Implementation (3 stories completed)

### Sprint 1 Details:
**Sprint Goal:** Deliver a working Flight Catalog page that allows users to view all available flights with complete details through the web interface.

**Duration:** [2026/2/11] to [2026/2/25]

**Completed Stories:**
1. US-03: [Browse Available Flights]

## Assignment 2 – Completed User Stories

1. Flight Catalog Improvements
- implemented by: Ahmad Younes
- Tasks:
     - Added category attribute to Flight model
     - Updated Flight constructor and getters
     - Implemented findByFlightNumber() method in FlightService
     - Implemented findAllSortedByPrice() method (ascending)
     - Updated flights.mustache to display category
     - Created index.mustache home page
     - Created HomeController with GET mapping for /
     - Added navigation button to /flights
     - Applied basic styling using style.css
 
3. US-04: Search Flights by Category
- Implemented by: Omar Henno
- Tasks:
     - Implement searchByCategory() in FlightService
     - Create search endpoint (Get /api/flights/search/category)
     - Add category dropdown in frontend
     - Connect Fetch API to frontend
     - Filtered results are displayed dynamically
  
3. US-04: Search Flights by Destination
- Implemented by: Madya Alfalasi  
- Tasks:
     - Implemented searchByName() in FlightService (case-insensitive)
     - Added GET /api/flights/search/name endpoint
     - Connected frontend using Fetch API
     - Displayed filtered results dynamically
  
4. US-06: Add Flight to booking cart
- Implemented by: Joy Almekha
- Tasks:
     - Created a collection ArrayList in FlightService to store saved flights
     - Implemented addToCollection() method to save flights using flight number
     - Added POST /api/flights/collection endpoint in FlightController
     - Connected frontend button to backend using Fetch API
     - Displayed confirmation message after adding a flight to the collection
 
5. US-09: View My Collection
- Implemented by: Dhabya Alsuwaidi
- Tasks:
    - Added GET /collection mapping in FlightController
    - Retrieved saved flights from FlightService
    - Passed collection data to the Mustache view
    - Created collection.mustache page
    - Added “View My Collection” button in frontend
    - Displayed saved flights in a table
    - Added “Back to Flights” button for navigation

## Assignment 3 – Completed User Stories

1. US-A3-01: User Authentication & Role Handling
- Implemented by: Ahmad Younes
- Tasks:
  - Create Role enum
  - Create User, Customer, and Administrator models
  - Create LoginRequest and UserResponse  model
  - Implement AuthService with in-memory users
  - Populate customer and adminstrator wit hseed samples
  - Create authentication REST endpoints (POST /auth/login, POST /auth/logout, GET /auth/current-user)
  - Implement AuthService service logic (login, logout, current user retrival, and role check)
  - Create login.html and login.js with fetch, success/error messages, form input, and role based ui for frontend

2. US-A3-03: Manage Flight Catalog (CRUD)
-Implemented by: Joy
-Tasks:

    -Added setters to Flight model (setFlightNumber, setDestination, setCategory, setDepartureTime, setSeats, setPrice)
    -Added saveFlight() method to FlightService for adding new flights
    - Added updateFlight() method to FlightService for modifying existing flights
    - Added deleteFlight() method to FlightService for removing flights
    - Implemented POST /api/flights/add endpoint (admin only)
    - Implemented PUT /api/flights/{id}/update endpoint (admin only)
    - Implemented DELETE /api/flights/{id}/delete endpoint (admin only)
    - Added admin check using AuthService so only admins can modify flights
    - Added Add Flight button visible to admin users only
    - Built Add/Edit form with pre-populated fields for edit mode
    - Added Edit and Delete buttons in flights table (admin only)
    - Added confirmation popup before deleting a flight
    - Added success messages after add, update, and delete actions
    - Added unit tests for saveFlight, updateFlight, and deleteFlight (10/10 tests passing)

3. US-A3-04: Manage Private Booking Cart
- Implemented by: Madya Alfalasi
- Tasks:
    - Created Cart and CartItem models
    - Stored carts and cart items using in-memory ArrayList
    - Linked cart to customer using customerId
    - Implemented addFlightToCart() with validation for quantity and sold-out flights
    - Implemented updateQuantity() with validation checks
    - Implemented removeFlightFromCart() method
    - Implemented clearCart() method
    - Implemented getCartByCustomer() method
    - Added REST endpoints for cart operations (/cart, /cart/add, /cart/update, /cart/remove, /cart/clear)
    - Connected frontend to backend using Fetch API
    - Added “Add to Cart” button in flights table
    - Prevented adding sold-out flights and displayed “Sold Out” in frontend
    - Created cart page (cart.html)
    - Displayed cart items in table
    - Added quantity controls, remove button, and clear cart button
    - Displayed confirmation and error messages for cart actions
4.

5. US-A3-05: Finalize Bookings And Managing Bookings
- Implemented by: Dhabya Alsuwaidi
- Tasks:
  - Created Booking model (bookingId, customerId, status, totalAmount, items)
  - Created BookingItem model (flightNumber, quantity, finalPrice)
  - Created UpdateBookingRequest model for booking status updates 
  - Stored bookings and booking items in separate lists 
  - Linked bookings to customer using customerId 
  - Implemented checkout logic (convert cart to booking, validate seats, calculate total, clear cart)
  - Implemented GET /booking endpoint to retrieve all bookings for current customer 
  - Implemented GET /booking/{id} endpoint to retrieve a specific booking 
  - Implemented POST /booking/checkout endpoint to finalize booking 
  - Implemented PUT /booking/{id}/update endpoint to update booking status 
  - Implemented DELETE /booking/{id}/delete endpoint to remove a booking 
  - Reduced available flight seats after successful checkout 
  - Restored seats when booking is cancelled 
  - Reduced seats again when booking is uncancelled and restored to confirmed 
  - Added “Checkout” button to cart page 
  - Created booking.html page for booking history 
  - Displayed bookings in table format with ID, status, total amount, and flights 
  - Formatted flights display as “flightNumber (X ticket/tickets)” 
  - Added Cancel button for confirmed bookings 
  - Replaced Cancel with Uncancel button when booking status becomes cancelled 
  - Kept Delete button visible for all bookings 
  - Added navigation buttons (Home, Flights, Cart, Login)
  - Displayed success and error messages for user actions 
  - JUnit testing for BookingService

6. US-A3-01: User Authentication & Role Handling
- Implemented by: Ahmad Younes
- Tasks:
  - Created a consistent navigation bar across all pages with buttons for Home, Flights, Cart, Bookings, and Login
  - Updated all pages to follow a unified design so the interface feels consistent throughout the application
  - Enhanced buttons with better styling, hover effects, and rounded corners to improve usability
  - Improved tables by adjusting spacing, styling headers, and adding hover effects for better readability
  - Styled forms such as the login and flight forms to make them cleaner and easier to use
  - Edited the shared style.css file to keep the design consistent across all pages
  - Ensured all improvements were done without changing any backend logic
  - Removing unused mustache files


## Links
- **GitHub Repository:** [https://github.com/OmarHenno/SWEN.261.Project]
- **Trello Board:** [https://trello.com/b/oohUrZmx/airline-booking-system]
- **Functional Requirements:** [https://docs.google.com/document/d/1hIS5PdctGRMD0HX2McC6kIexj2GsmNJRGOslc2PiyXw/edit?tab=t.t1tiqzsjl4rb#heading=h.65a1jwayjipc]
- **Traceability Matrix:** [https://docs.google.com/spreadsheets/d/1qqbs3zcrVFXm439ohfdp7rLJMMd8G21_1qi0N8dOSZ4/edit?gid=0#gid=0]
