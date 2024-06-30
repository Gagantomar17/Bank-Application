# Bank Application

This project is a comprehensive bank application developed using Java Swing and JDBC. The application includes various features such as user login and signup, ATM services, account transfers, loan management, deposits, and a manager dashboard for reviewing and approving or declining requests.

## Table of Contents

- [Features](#features)
  - [Login and Signup](#login-and-signup)
  - [ATM Services](#atm-services)
  - [Account Transfers](#account-transfers)
  - [Loans](#loans)
  - [Deposits](#deposits)
  - [Manager Dashboard](#manager-dashboard)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

### Login and Signup

1. **User Signup**: New users can create an account by providing their personal details such as name, email, phone number, and password. The application validates the input data and stores the user information securely in the database.
2. **User Login**: Registered users can log in by entering their username and password. The application authenticates the user credentials and grants access to the user dashboard.

### ATM Services

1. **Fast Cash**: Users can quickly withdraw a pre-defined amount of cash (e.g., $50, $100, $200) without needing to specify the amount each time.
2. **Cash Withdrawal**: Users can withdraw cash from their account by entering the amount they wish to withdraw. The application checks for sufficient balance before processing the withdrawal.
3. **PIN Change**: Users can change their ATM PIN for added security.
4. **Mini Statement**: Users can view a mini statement that shows the last few transactions on their account.

### Account Transfers

1. **Fund Transfer**: Users can transfer funds from their account to another user's account by entering the recipient's account details and the transfer amount. The application ensures the sender has sufficient balance before processing the transfer.
2. **Transfer History**: Users can view their past account transfers, including the recipient details and the amounts transferred.

### Loans

1. **Loan Application**: Users can apply for different types of loans by entering the loan amount, selecting the loan type (e.g., personal loan, home loan), and specifying the loan duration.
2. **Interest Calculation**: The application calculates the interest based on the loan type and duration and displays it to the user before submission.
3. **Document Submission**: Users can upload necessary documents required for the loan application.
4. **Loan Request Submission**: Users can submit the loan request for review.

### Deposits

1. **Deposit Funds**: Users can deposit funds into their account by entering the deposit amount. The application updates the account balance accordingly.
2. **Deposit History**: Users can view their past deposits, including the amounts and dates of the deposits.

### Manager Dashboard

1. **Dashboard Access**: Managers have access to a dedicated dashboard where they can view all loan requests, account transfers, and deposits.
2. **Review Loan Requests**: Managers can review submitted loan requests, including the amount, duration, interest calculated, and submitted documents.
3. **Approve or Decline Loans**: Managers can approve or decline loan requests based on their review.
4. **View User Details**: Managers can view details of every user, including personal information and account activities.
5. **Total Active Users**: Managers can view the total number of active users in the system.
6. **Top Richest Users**: Managers can view a list of the top richest users based on their account balance.
7. **Total Amount Deposited**: Managers can view the total amount deposited across all user accounts.

## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Database
- JDBC Driver for MySQL

### Steps

1. **Clone the Repository**:
   ```sh
   git clone <repository-url>
   cd bank-application
