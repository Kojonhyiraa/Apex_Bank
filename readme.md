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

* **SavingsAccount:** Enforce a **Minimum Balance of $50**. Any debit that violates this must be blocked.
* **CurrentAccount:** Implement an **Overdraft Limit** (e.g., balance can go down to -$500).


### B. Composition & Data Structures

* **Transaction Class:** Represents a single transaction. It must include `amount`, `LocalDateTime`, and a `TransactionType` (Enum).
* **Association:** Each `Account` object must maintain its own `List<Transaction>` to track its personal history.

### C. Advanced Java Features

* **Custom Exceptions:** You must create and throw:
* `InsufficientFundsException`: Thrown during a debit if account rules (Min-Balance/Overdraft) are violated.
* `AccountNotFoundException`: Thrown if a user searches for an ID that doesn't exist in memory.


## 4. Implementation Guide

| Layer | Responsibility |
| --- | --- |
| **Model Layer** |  the `Transaction` class and the `Account` hierarchy (Savings/Current). |
| **Service Layer** |  a `BankService` to hold collections and manage business logic (finding accounts, processing transfers). |
| **Presentation Layer** |  the `Main` class with a `while(true)` loop and a `switch` statement for user input. |

### Stability & Robustness

* **Error Handling:** the application handles `InputMismatchException` (e.g., if a user enters a string when a number is expected) so the program doesn't terminate unexpectedly.
