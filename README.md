# OrderFlow: E-Commerce Order Processing Engine

## 📌 Project Overview
**OrderFlow** is a backend processing engine built in **Core Java** designed to manage and process high-volume e-commerce transactions based on priority tiering. It demonstrates the use of efficient Data Structures, Algorithms, and Object-Oriented Programming (OOP) principles to handle real-world business rules.

## 🚀 Key Features & Technical Implementations
* **Priority-Based Processing (DSA):** Leveraged a Max-Heap using Java's `PriorityQueue` to ensure that Express/High-Priority orders jump the queue and are processed before Standard orders, achieving **O(log N)** time complexity for insertions and extractions.
* **In-Memory Inventory Cache (Collection API):** Utilized `HashMap` for lightning-fast **O(1)** time complexity inventory validation lookups, avoiding potential database bottlenecks.
* **Extensible Architecture (OOPs):** Designed an abstract base `Order` model with subclasses (`StandardOrder`, `ExpressOrder`). Applied **Polymorphism and Method Overriding** so the processing engine can dynamically calculate fees without rigid `if-else` type-checking.
* **Database Persistence Simulation:** Includes a JDBC simulation module (`DatabaseManager`) to log transactions and processing fees to a PostgreSQL database.

## 🛠️ Technology Stack
* **Language:** Java (Core, Collection API)
* **Concepts:** Data Structures (Max-Heap, HashMaps), Algorithms (Sorting via Comparable interface), OOPs (Inheritance, Polymorphism)
* **Database Target:** PostgreSQL (JDBC simulation)

## 📂 Project Structure
```text
src/
├── Order.java             # Abstract base class implementing Comparable
├── StandardOrder.java     # Subclass with standard fee logic
├── ExpressOrder.java      # Subclass with priority scoring and rush surcharges
├── OrderProcessor.java    # Engine managing PriorityQueue and HashMap
├── DatabaseManager.java   # PostgreSQL JDBC simulation
└── Main.java              # Execution and simulation script
```

## 💡 How It Works
1. The engine registers product inventory into a fast-lookup HashMap.
2. Orders of varying types are generated and fed into the processor.
3. A `PriorityQueue` automatically sorts the incoming queue based on the order's defined priority tier.
4. The engine loops through the queue, polymorphically triggering each order's unique `calculateTotalProcessingFee()` logic.
5. High-priority orders are processed first and their metrics are logged to the database system.
