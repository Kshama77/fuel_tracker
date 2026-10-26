# FuelTracker ⛽

A beginner-friendly Java console app to record fuel purchases and track mileage.

## Features
- Add fuel purchases with date, odometer reading, fuel quantity, and total cost
- View all fuel records
- Calculate total fuel spending and total fuel purchased
- Estimate average cost per unit of fuel
- Validate dates and numeric input
- Simple menu-based interface

## Requirements
- Java JDK 8 or newer

## Run the app
Open a terminal in the project folder and compile:

```bash
javac -d out src/*.java
```

Then run:

```bash
java -cp out FuelTrackerApp
```

## Project structure
```text
FuelTracker/
├── README.md
├── .gitignore
└── src/
    ├── FuelRecord.java
    └── FuelTrackerApp.java
```

## Java concepts practiced
Classes, objects, constructors, encapsulation, ArrayList, loops, conditionals, methods, Scanner, LocalDate, and exception handling.

## Note
Records are stored in memory only while the program is running. They are cleared when you exit the app.
