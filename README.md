# OrderFlow: Enterprise Order Processing Engine

## 📌 Project Overview
**OrderFlow** is an enterprise-grade backend processing engine built in **Core Java** using a standard **Maven** architecture. It is designed to manage and process high-volume e-commerce transactions based on priority tiering. It demonstrates the use of efficient Data Structures, Algorithms, custom Exception handling, and Object-Oriented Programming (OOP) principles to handle real-world business rules.

## 🚀 Key Features & Technical Implementations
* **Priority-Based Processing (DSA):** Leveraged a Max-Heap using Java's `PriorityQueue` to ensure that Express/High-Priority orders jump the queue and are processed before Standard orders, achieving **O(log N)** time complexity.
* **In-Memory Inventory Cache (Collection API):** Utilized `HashMap` for lightning-fast **O(1)** time complexity inventory validation lookups, avoiding database bottlenecks.
* **Extensible Architecture (OOPs):** Designed an abstract base `Order` model with subclasses (`StandardOrder`, `ExpressOrder`). Applied **Polymorphism and Method Overriding** so the processing engine can dynamically calculate fees without rigid `if-else` type-checking.
* **Enterprise Exception Handling:** Built a custom hierarchy of runtime exceptions (`OrderProcessingException`, `InventoryShortageException`) for fault tolerance and clear debugging.
* **Database Persistence Simulation:** Includes a JDBC simulation module (`DatabaseManager`) initialized via an `application.properties` configuration file to log transactions to a PostgreSQL database.

## 🛠️ Technology Stack
* **Language:** Java 11 (Core, Collection API)
* **Build Tool:** Maven
* **Concepts:** Data Structures (Max-Heap, HashMaps), Algorithms (Comparable interface sorting), OOPs (Inheritance, Polymorphism)
* **Database Target:** PostgreSQL (JDBC simulation)

## 📂 Project Structure
```text
src/
├── main/
│   ├── java/com/orderflow/
│   │   ├── database/       # DB config & JDBC integration
│   │   ├── exception/      # Custom Runtime Exceptions
│   │   ├── model/          # Abstract classes & Polymorphic models
│   │   ├── service/        # Priority Queue processing & Inventory Logic
│   │   └── Main.java       # Application Entry Point
│   └── resources/
│       └── application.properties # Environment configuration
├── test/
│   └── java/com/orderflow/ # JUnit Test cases (Planned)
└── pom.xml                 # Maven Dependency Management
```

## 💡 How It Works
1. The `InventoryManager` registers product inventory into a fast-lookup HashMap.
2. Orders of varying types are generated and fed into the `OrderProcessor`.
3. A `PriorityQueue` automatically sorts the incoming queue based on the order's defined priority tier.
4. The engine loops through the queue, checking inventory availability, and polymorphically triggering each order's unique `calculateTotalProcessingFee()` logic.
5. High-priority orders are processed first, inventory is safely deducted, and metrics are logged to the database system. Any shortage throws an `InventoryShortageException`.
