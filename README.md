# 🎬 Movie Ticket Booking System (MTBS)

<div align="center">

![MTBS Logo](https://via.placeholder.com/150x150.png?text=MTBS+Logo)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com)
[![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com)
[![JDBC](https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white)](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

</div>

## 📋 Table of Contents
- [Overview](#-overview)
- [Features](#-features)
- [Technical Details](#️-technical-details)
- [Installation](#-installation)
- [Usage](#-usage)
- [Screenshots](#-screenshots)
- [Development](#-development)
- [Contributing](#-contributing)
- [License](#-license)

## 📖 Overview

A Java-based Movie Ticket Booking System that provides a user-friendly interface for both administrators and customers to manage movie bookings and cinema operations. Built with Java and MySQL, this system offers a robust solution for cinema management.

<div align="center">
<img src="https://via.placeholder.com/800x400.png?text=System+Overview" alt="System Overview">
</div>

## ✨ Features

### 👨‍💼 Admin Features
- 🔐 Secure login system
- ➕ Add new movies with details
- 🗑️ Delete existing movies
- 📋 View movie listings
- 📊 View booking list
- ⚙️ Manage cinema operations

<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Admin+Dashboard" alt="Admin Dashboard">
</div>

### 👥 Customer Features
- 🎟️ Book movie tickets
- 🔍 Check ticket status
- 📺 View movie listings
- 🎨 User-friendly interface

<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Customer+Interface" alt="Customer Interface">
</div>

## 🛠️ Technical Details

### 📋 Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Server 8.0 or higher
- MySQL Connector/J (JDBC Driver) 8.0.27 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, etc.)

### 🔌 Database Configuration
- Database Name: db
- Port: 3300
- Default Credentials:
  - Username: root
  - Password: aadigandhi@2210

### 📚 Dependencies
```xml
<dependencies>
    <!-- MySQL JDBC Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.27</version>
    </dependency>
</dependencies>
```

### 🏗️ Project Structure
The project consists of a main Java class `MY_MTBS.java` that handles all the functionality including:
- 🔄 Database connections using JDBC
- 🔐 User authentication
- 🎬 Movie management
- 🎟️ Ticket booking
- 📊 Booking list management

<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Project+Structure" alt="Project Structure">
</div>

## 🚀 Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/mtbs.git
cd mtbs
```

2. Set up MySQL database:
   - Create a database named 'db'
   - Configure the database connection settings in the code

3. Add MySQL JDBC driver to your project dependencies

4. Compile and run the Java application
```bash
javac MY_MTBS.java
java MY_MTBS
```

### 🔧 Database Setup
```sql
CREATE DATABASE db;
USE db;

CREATE TABLE cinema (
    movie_name VARCHAR(100),
    show_time VARCHAR(50),
    price INT,
    seats INT,
    C_name VARCHAR(100)
);

CREATE TABLE customer (
    cust_name VARCHAR(100),
    movie_name VARCHAR(100),
    phone_no VARCHAR(15),
    seats_booked INT,
    total_amt INT
);
```

## 💻 Usage

### 👨‍💼 Admin Access
1. Select option 1 for Admin login
2. Use default credentials:
   - Username: root
   - Password: root
3. Access admin control panel features

<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Admin+Login" alt="Admin Login">
</div>

### 👥 Customer Access
1. Select option 2 for Customer access
2. Choose from available options:
   - 🎟️ Book Ticket
   - 🔍 Check Ticket
   - 📺 View Movie List

<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Customer+Menu" alt="Customer Menu">
</div>

## 📸 Screenshots

### Movie Management
<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Movie+Management" alt="Movie Management">
</div>

### Ticket Booking
<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Ticket+Booking" alt="Ticket Booking">
</div>

### Booking List
<div align="center">
<img src="https://via.placeholder.com/600x300.png?text=Booking+List" alt="Booking List">
</div>

## 👨‍💻 Development

### 📌 Technical Notes
- This is a console-based application with a colorful interface using ANSI color codes
- Uses JDBC for database connectivity
- Implements prepared statements for secure database operations
- Features a modular design for easy maintenance and updates

### 🔧 Development Setup
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 🤝 Contributing
We welcome contributions! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🔗 Useful Links
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [JDBC Documentation](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
- [Java Documentation](https://docs.oracle.com/en/java/)


---

<div align="center">
Made with ❤️ using Java & JDBC
</div>
