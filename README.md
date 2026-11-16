# Cinema Ticketing & Concession System (OOP / Java)

An object-oriented cinema management, ticket booking, and concession ordering application built with **Java**.

## My Contributions

System Core & Orchestration (`CinemaSystem`) - Architected the central `CinemaSystem` controller to coordinate interactions across user authentication, movie browsing, seat reservations, and order processing.
User Hierarchy & Authentication (`Person`, `Staff`, `Customer`) - Applied OOP principles (inheritance and encapsulation) to design user role classes, implementing customer sign-up and login workflows.
Booking & Order Processing (`Booking`, `Order`, `OrderItem`) - Engineered the end-to-end reservation and purchasing logic, managing seat bookings alongside itemized order creation and tracking.

---

## Team Project Features
*As part of a collaborative team effort, the complete system also integrates the following modules developed by my teammates:*

* **Ticketing & Payment Processing**: Ticket generation (`Ticket`) and multi-method transaction handling (`Payment`, `PaymentProcess`) supporting `Cash`, `CreditCard`, and `Ewallet` via polymorphism.
* **Hall & Seat Management**: Cinema hall organization (`Hall`), seat layout modeling (`Seat`), and persistent seat reservation records (`SeatBookingFile`).
* **Concessions & Receipts**: Food and beverage catalog management (`Concession`) and itemized transaction receipt generation (`Receipt`).
* **Movie & Schedule Administration**: CRUD operations and file-based persistence for `Movie` and `Schedule` modules.
