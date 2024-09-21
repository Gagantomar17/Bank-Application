# Bank Application

This project is a comprehensive bank application developed using Java Swing and JDBC. It includes robust banking services such as user login and signup, ATM services, account transfers, loan management, deposits, stock investments, and a manager dashboard for handling user activities and approving/rejecting loan requests.

## Table of Contents

- [Features](#features)
  - [Login and Signup](#login-and-signup)
  - [ATM Services](#atm-services)
  - [Account Transfers](#account-transfers)
  - [Loans](#loans)
  - [Deposits](#deposits)
  - [Investments](#investments)
  - [Manager Dashboard](#manager-dashboard)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

### Login and Signup

1. **User Signup**: Secure sign-up process where users provide personal details. All data is validated and securely stored in the database.
2. **User Login**: Fast and secure authentication using a username and password, with access control for the user dashboard.

### ATM Services

1. **Fast Cash**: Allows users to withdraw predefined cash amounts ($50, $100, $200) instantly.
2. **Cash Withdrawal**: Users can withdraw any specific amount, with automatic balance checks.
3. **PIN Change**: Allows users to securely change their ATM PIN.
4. **Mini Statement**: Displays recent transactions for user accounts.

### Account Transfers

1. **Fund Transfer**: Enables users to transfer funds between accounts with verification for sufficient balance.
2. **Transfer History**: Provides a comprehensive view of past transfers, including recipient details and amounts.

### Loans

1. **Loan Application**: Supports applying for various loan types (e.g., personal, home loans), with configurable loan durations.
2. **Interest Calculation**: Automatic calculation of interest rates based on loan type and duration.
3. **Loan Request Submission**: Users can submit loan requests for manager review and approval.

### Deposits

1. **Fixed Deposits (FD)**: Users can invest in Fixed Deposit schemes, choosing the tenure and receiving competitive interest rates.
2. **Stocks Investment**: Now integrated with real-time stock market data via an external API, allowing users to invest in various stocks. The system supports investments in both US and Indian stock markets, with real-time price updates and portfolio management.
3. **Portfolio Overview**: Users can track and manage their stock investments, view real-time performance, and get detailed insights into their portfolio.

### Manager Dashboard

1. **Loan Review**: Managers can review loan applications, check supporting documentation, and either approve or reject requests.
2. **User Management**: View all registered users and their activity, including loans, transfers, and deposits.
3. **Top Users**: The dashboard provides insights into top users based on account balances.
4. **System Overview**: Managers can view statistics, such as total active users and the total amount deposited.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Database
- JDBC Driver for MySQL
- (For Stock Investments) API keys for integrated stock market data providers (Alpha Vantage or similar)

### Steps

1. **Clone the Repository**:
   ```sh
   git clone <repository-url>
   cd bank-application
