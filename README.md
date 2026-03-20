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
- implemented by: Omar Henno
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
      
## Links
- **GitHub Repository:** [https://github.com/OmarHenno/SWEN.261.Project]
- **Trello Board:** [https://trello.com/b/oohUrZmx/airline-booking-system]
- **Functional Requirements:** [https://docs.google.com/document/d/1hIS5PdctGRMD0HX2McC6kIexj2GsmNJRGOslc2PiyXw/edit?tab=t.t1tiqzsjl4rb#heading=h.65a1jwayjipc]
- **Traceability Matrix:** [https://docs.google.com/spreadsheets/d/1qqbs3zcrVFXm439ohfdp7rLJMMd8G21_1qi0N8dOSZ4/edit?gid=0#gid=0]
