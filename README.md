# DSA-Project
### Bank App with Swing and MySQL
A simple bank application with a graphical user interface developed in Java using the Swing framework. The application interacts with a MySQL database to perform essential banking operations.
### Features
-Checking internet connection:
Defines a Java interface and classes that support monitoring internet connections and notifying registered listeners of connection changes.
- Sign in/Sign up: 
This module handles user login and sign-up processes. It ensures secure access through password.
- Deposit: 
This module allows users to deposit money into their account, from which cash is converted into data on the app
- Withdraw
This module allows users to withdraw existing money from their account. When withdrawing, the withdrawal amount must be less than or equal to the existing balance.
- Past Transaction
This module supports users in retrieving old transactions, helping users manage finances
- Logout: 
This module is used to exit the app interface and return to the login interface
### The techniques & tools used
- Java language using Intellij IDE design the interface for users from the database and application logic flow
- MySQL for storing, retrieving data and create diagram from the database
- Java JDBC to connect a database from MySQL to the GUI.
### How to run the app
- Connect to the internet
- Create the database in MySQL followed by the database diagram
- Connect MySQL and Intellij IDE by mysql-connector-j-8.1.0.jar
- Run the AppLauncher.java

