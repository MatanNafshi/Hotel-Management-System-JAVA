
# Hotel Management System

This project is a **Java-based application** designed to manage hotel operations, including customer bookings, room management, employee and department handling, and more. The system is built using **object-oriented programming (OOP) principles** and follows a modular structure to handle different aspects of hotel management efficiently.

## Project Overview

The Hotel Management System provides the following functionalities:
- **Customer Management**: Handles customer information, room bookings, and other related interactions.
- **Room Management**: Manages the types of rooms (e.g., Standard Rooms, VIP Rooms), availability, and bookings.
- **Employee & Department Management**: Organizes employees by department, including department managers and other staff.
- **Booking System**: Allows users to make room bookings, check availability, and manage reservations.

## Key Components

1. **LoginScreen.java**: Manages user authentication and login into the system. Ensures that only authorized users can access the system.
  
2. **MainDashboard.java**: The main interface for managing all system functions, including bookings, customer data, room availability, and more.
   
3. **Customer.java**: Represents the customer class, encapsulating customer details such as name, contact information, and booking history.

4. **Room.java / StandardRoom.java**: Manages different types of rooms (standard, VIP, etc.), handling availability and related information.

5. **Booking.java**: Manages room reservations, linking customers to their respective bookings and handling dates, room preferences, and availability.

6. **DepartmentManager.java / Employee.java**: These classes handle employee data and the organization of staff within departments (e.g., housekeeping, management).

7. **Hotel.java**: The main class that orchestrates the overall system, managing the relationships between rooms, customers, and employees.

8. **Main.java**: The entry point of the application where the system starts, initializing components and displaying the login screen.

**License**

This project is for educational purposes. Feel free to modify and adapt it to your own use cases.
