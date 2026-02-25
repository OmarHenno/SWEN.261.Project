# Glossary

## User
**Definition:**
A registered account in the Wandria system that can authenticate and access system features.

**Attributes:**
- username (unique identifier)
- password (securely stored)
- email

**Synonyms:**
Account

**Related Terms:**
Customer, Administrator, Authentication

**Business Rules:**
- Each User must have a unique username.
- Passwords must be securely stored (not plain text).

**Example:**
Username: Dalsuwaidi
Email: dibrahim34@gmail.com


## Customer
**Definition:**
A User who can browse flights, manage a booking cart, and commit bookings.

**Attributes:**
- customerId (unique)
- Associated Cart
- Associated Bookings

**Synonyms:**
Traveler, Client

**Related Terms:**
Cart, Booking, Flight

**Business Rules:**
- Each Customer has exactly one Cart.
- Customers can only access their own Cart and Bookings.

**Example:**
Customer ID: C1023


## Administrator
**Definition:**
A User with elevated permissions who manages the flight catalog and availability.

**Attributes:**
- adminId (unique)

**Synonyms:**
Admin

**Related Terms:**
Flight, Catalog, Availability

**Business Rules:**
- Only Administrators can add, update, or remove Flights.

**Example:**
Admin ID: A001


## Flight
**Definition:**
A catalog entry representing a scheduled travel route that can be booked by Customers.

**Attributes:**
- flightNumber (unique identifier)
- destination
- date
- seats (available seat count)

**Synonyms:**
Route, Trip

**Related Terms:**
Booking, Cart, Availability

**Business Rules:**
- Each Flight must have a unique flightNumber.
- Available seats cannot be negative.
- A Flight can only be reserved if seats > 0.

**Example:**
Flight Number: EK202
Destination: Dubai
Date: 2026-03-15
Seats: 120


## Cart (Booking Cart)
**Definition:**
A collection of selected Flights associated with a Customer prior to booking.

**Attributes:**
- cartId (unique)
- Contains one or more Flights

**Synonyms:**
Booking Cart, Shopping Cart

**Related Terms:**
Customer, Booking, Flight

**Business Rules:**
- Each Customer manages exactly one Cart.
- Cart contents are persistent in the database.
- Only the owning Customer can access their Cart.

**Example:**
Cart ID: CART445


## Booking
**Definition:**
A finalized reservation created when a Customer commits selected Flights from their Cart.

**Attributes:**
- bookingId (unique)
- status (Pending, Confirmed)

**Synonyms:**
Reservation

**Related Terms:**
Customer, Flight, Commit

**Business Rules:**
- A Booking must contain at least one Flight.
- Booking updates Flight availability.
- After Booking is committed, the Cart is cleared.

**Example:**
Booking ID: BKG778
Status: Confirmed


## Commit
**Definition:**
The action performed by a Customer to finalize a Cart and create a Booking.

**Synonyms:**
Finalize, Confirm

**Related Terms:**
Booking, Availability

**Business Rules:**
- Commit reduces available seats of reserved Flights.
- Commit clears the Customer’s Cart.


## Availability
**Definition:**
The number of remaining seats for a Flight.

**Synonyms:**
Seat Count

**Related Terms:**
Flight, Booking

**Business Rules:**
- Availability cannot be negative.
- Booking is only allowed if availability > 0.


## Role-Based Access Control (RBAC)
**Definition:**
A security mechanism that restricts system functionality based on user role.

**Synonyms:**
Role Authorization

**Related Terms:**
User, Customer, Administrator

**Business Rules:**
- Customers cannot modify Flights.
- Administrators cannot access Customer Carts.


## Spring Boot
**Definition:**
A Java-based backend framework used to build RESTful APIs and manage server-side logic.

**Related Terms:**
Backend, REST API, Endpoint, Mustache


## Mustache
**Definition:**
A logic-less templating engine used in the Wandria system to generate dynamic HTML pages by inserting server-side data into predefined templates.

**Attributes:**
- Template files (.mustache)
- Placeholders for dynamic data

**Synonyms:**
Templating Engine

**Related Terms:**
Template, HTML, Frontend, Spring Boot

**Business Rules:**
- Mustache templates do not contain business logic.
- Data is provided by the backend before rendering the page.

**Example:**
File: flights.mustache displays flight data dynamically.


## Template
**Definition:**
A predefined HTML file containing placeholders that are replaced with dynamic data when the page is rendered.

**Attributes:**
- templateName (unique identifier)
- placeholders for dynamic content

**Synonyms:**
View

**Related Terms:**
Mustache, HTML, Frontend

**Business Rules:**
- Templates must follow Mustache syntax.
- Templates are rendered by the backend before being sent to the user.

**Example:**
Template: booking.mustache displays booking confirmation.


## Frontend
**Definition:**
The part of the system that users interact with directly, including web pages and user interface components.

**Attributes:**
- User interface pages
- Interactive elements

**Synonyms:**
Client-side

**Related Terms:**
Mustache, HTML, Backend

**Business Rules:**
- Frontend must display accurate data received from the backend.
- Users interact with the system through the frontend.

**Example:**
The Wandria homepage where users search for flights.


## Backend
**Definition:**
The server-side part of the system responsible for processing requests, executing business logic, and interacting with the database.

**Attributes:**
- Server application
- API endpoints

**Synonyms:**
Server-side

**Related Terms:**
Spring Boot, MariaDB, REST API

**Business Rules:**
- Backend must validate user input.
- Backend manages communication with the database.

**Example:**
Spring Boot server processing flight booking requests.


## HTML
**Definition:**
A markup language used to structure and display content on web pages.

**Attributes:**
- Tags and elements
- Page structure

**Synonyms:**
HyperText Markup Language

**Related Terms:**
Frontend, Template, Mustache

**Business Rules:**
- HTML must follow proper syntax.
- HTML pages are rendered in the user’s browser.

**Example:**
index.html displaying the Wandria homepage.


## MariaDB
**Definition:**
A relational database management system used to store persistent system data.

**Synonyms:**
Database (DB)

**Related Terms:**
Backend, Persistent Storage


## REST API
**Definition:**
A web service architecture used to enable communication between frontend and backend components.

**Acronym:**
API – Application Programming Interface

**Related Terms:**
Endpoint, HTTP, Backend


## Endpoint
**Definition:**
A specific URL that allows communication between client and server.

**Related Terms:**
REST API, Backend, Frontend


## Authentication
**Definition:**
The process of verifying a user’s identity using credentials such as username and password.

**Related Terms:**
User, Authorization, RBAC


## Authorization
**Definition:**
The process of granting or denying access to system features based on user role.

**Related Terms:**
Authentication, RBAC, User


## Functional Requirement (FR)
**Definition:**
A requirement that describes what the system must do.


## Non-Functional Requirement (NFR)
**Definition:**
A requirement that describes system quality attributes such as performance or security.

