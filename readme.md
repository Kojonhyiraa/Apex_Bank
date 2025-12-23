# Assignment: Apex CLI Banking System

## 1. Project Overview

The objective is to build a robust **Command Line Interface (CLI) Banking System** in Java. This project evaluates your understanding of Object-Oriented Programming (OOP) principles, clean code architecture, and modern Java features. All data for this application will be stored **in-memory** for the duration of the program's execution.

---

## 2. Core Functional Requirements

The following features are mandatory:

* **Account Creation:** Users must be able to create either a **Savings** or **Current** account.
* **Automated ID Generation:** Account numbers must be auto-generated and unique using static members.
* **Balance Inquiry:** Check the current balance of a specific account using its ID.
* **Credit (Deposit):** Increase the balance of an account.
* **Debit (Withdrawal):** Decrease the balance while enforcing specific account rules.
* **Mini-Statement:** Display the last 5 transactions associated with the account, formatted clearly in the console.

---

## 3. Technical Specifications

### A. The Pillars of OOP

1. **Abstraction:** Create an abstract class `Account`. It must contain shared properties (`accountNumber`, `balance`, `accountHolderName`) and an abstract method `calculateInterest()`.
2. **Inheritance:**
* **SavingsAccount:** Must enforce a **Minimum Balance of $50**. Any debit that violates this must be blocked.
* **CurrentAccount:** Must implement an **Overdraft Limit** (e.g., balance can go down to -$500).


3. **Encapsulation:** All class fields must be **private**. Access must be controlled via getters and setters. Data validation (e.g., preventing negative deposit amounts) must happen inside these methods.
4. **Polymorphism:** Store all accounts in a single `List` or `Map`. Use dynamic method dispatch to process transactions regardless of the specific account type.

### B. Composition & Data Structures

* **Transaction Class:** Represents a single transaction. It must include `amount`, `LocalDateTime`, and a `TransactionType` (Enum).
* **Association:** Each `Account` object must maintain its own `List<Transaction>` to track its personal history.

### C. Advanced Java Features

* **Custom Exceptions:** You must create and throw:
* `InsufficientFundsException`: Thrown during a debit if account rules (Min-Balance/Overdraft) are violated.
* `AccountNotFoundException`: Thrown if a user searches for an ID that doesn't exist in memory.


* **Interfaces:** Implement an `Authenticatable` interface with a `verifyPin(int pin)` method that all account subclasses must implement.
* **Streams & Lambdas:** Use the Java Stream API to sort and filter the transaction list to retrieve exactly the **5 most recent records** for the mini-statement.
* **Static Members:** Use a static counter to manage the auto-incrementing account ID system.

---

## 4. Implementation Guide

| Layer | Responsibility |
| --- | --- |
| **Model Layer** | Define the `Transaction` class and the `Account` hierarchy (Savings/Current). |
| **Service Layer** | Create a `BankService` to hold collections and manage business logic (finding accounts, processing transfers). |
| **Presentation Layer** | Create the `Main` class with a `while(true)` loop and a `switch` statement for user input. |

### Stability & Robustness

* **Error Handling:** Ensure the application handles `InputMismatchException` (e.g., if a user enters a string when a number is expected) so the program doesn't terminate unexpectedly.
* **Validation:** Use setters to ensure logic like `amount > 0` is respected for all financial movements.

Would you like me to generate a boilerplate code structure for the `Account` abstract class and the `BankService` to get you started?