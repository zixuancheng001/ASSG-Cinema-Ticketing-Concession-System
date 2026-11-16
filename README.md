# Cinema Ticketing & Concession System (OOP / Java)

An object-oriented cinema management, ticket booking, and concession ordering application built with **Java**.

## My Contributions

1. System Core & Orchestration (`CinemaSystem`) - Architected the central `CinemaSystem` controller to coordinate interactions across user authentication, movie browsing, seat reservations, and order processing.
2. User Hierarchy & Authentication (`Person`, `Staff`, `Customer`) - Applied OOP principles (inheritance and encapsulation) to design user role classes, implementing customer sign-up and login workflows.
3. Booking & Order Processing (`Booking`, `Order`, `OrderItem`) - Engineered the end-to-end reservation and purchasing logic, managing seat bookings alongside itemized order creation and tracking.

---

## Team Project Features
*As part of a collaborative team effort, the complete system also integrates the following modules developed by my teammates:*

4. Ticketing & Payment Processing - Ticket generation (`Ticket`) and multi-method transaction handling (`Payment`, `PaymentProcess`) supporting `Cash`, `CreditCard`, and `Ewallet` via polymorphism.
5. Hall & Seat Management - Cinema hall organization (`Hall`), seat layout modeling (`Seat`), and persistent seat reservation records (`SeatBookingFile`).
6. Concessions & Receipts - Food and beverage catalog management (`Concession`) and itemized transaction receipt generation (`Receipt`).
7. Movie & Schedule Administration - RUD operations and file-based persistence for `Movie` and `Schedule` modules.

## How to Run Locally

1. **Prerequisites**: Install **Java Development Kit (JDK)** and **JCreator LE** (or any standard Java IDE / terminal with `javac`).
2. **Download**: Clone this repository or click **Code** -> **Download ZIP** and extract it to a local folder.
3. **Open & Run (via JCreator LE)**:
   - Open **JCreator LE** and go to **File** -> **Open Workspace...** (or open the `.jcp` / `.java` files in the `src` folder).
   - Select the main entry class (`CinemaSystem.java`).
   - Press `F5` (or click **Build** -> **Build Project** followed by **Run** -> **Run Project**) to compile and launch the application.
   
## System Preview

<img width="707" height="552" alt="Login & Register" src="https://github.com/user-attachments/assets/a5a1ff3f-0df3-4c57-8974-74e27a80afcf" />
<img width="1917" height="966" alt="Movie Menu" src="https://github.com/user-attachments/assets/6a5bbe9d-eecb-44b9-8b02-b85def2d2b99" />
<img width="961" height="512" alt="Movie Schedule & Seat" src="https://github.com/user-attachments/assets/e25a473e-1ebb-495b-8469-35255ade535a" />
<img width="862" height="441" alt="Snack Menu" src="https://github.com/user-attachments/assets/e51bbd9d-718a-484b-9936-87976754e4fa" />
<img width="795" height="387" alt="Order Details" src="https://github.com/user-attachments/assets/09411f9b-5b97-49b6-8dae-2a85cf82c1b0" />
<img width="787" height="837" alt="Checkout" src="https://github.com/user-attachments/assets/d9f3d9ce-7ae4-43a8-ab45-f1c6c7952ce2" />
