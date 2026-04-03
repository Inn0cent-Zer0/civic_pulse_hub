# 💰 Personal Finance Tracker and Budgeting App

## 📌 Project Title

**Personal Finance Tracker and Budgeting App**

---

## 📖 Project Overview

With increasing financial uncertainty and the growing need for effective money management, individuals require simple yet powerful tools to monitor their finances.

This application provides a personalized platform where users can:

* Track daily income and expenses
* Set budgets and savings goals
* Visualize financial trends
* Export financial data
* (Optional) Engage with a community through financial tips

---

## 🎯 Objectives / Outcomes

* 🔐 Secure user authentication and role management
* 📊 Expense and income tracking with categorized entries
* 🎯 Custom budgeting and savings goal features
* 📈 Graphical visualization of financial data over time
* 📁 Data export in PDF/CSV and cloud backup support
* 💬 Optional community-based financial tips forum

---

## 🧩 Modules Implemented

### 1. User Authentication and Profile Management

* JWT-based authentication system
* Role-based access (User/Admin)
* Profile setup with income, savings, and expense targets

### 2. Expense and Income Tracking Module

* Add daily income and expense entries
* Categorization (Rent, Food, Travel, etc.)
* Transaction history log
* Edit and delete functionality

### 3. Budget and Savings Goals

* Set monthly budgets by category
* Track spending against budgets
* Define and monitor savings goals

### 4. Financial Trends and Visualization (Completed ✅)

* Monthly spending comparison
* Pie charts for category-wise spending
* Bar charts for income vs expenses

### 5. Data Export and Community Forum (Planned)

* Export financial records (PDF/CSV)
* Cloud backup (Google Drive / Dropbox)
* Financial tips forum with posts, comments, and likes

---

## 🚀 Current Progress

✔ Completed up to **Week 7**

* Authentication system implemented
* Transaction tracking working
* Budgeting and savings module functional
* Data visualization completed

⏳ Pending (Week 8)

* Data export functionality
* Cloud backup integration
* Community forum (optional)

---

## 📅 Module-wise Implementation

### 🔹 User Authentication and Profile Management

* User registration and login using JWT
* Role-based access control (User/Admin)
* Profile creation and management

### 🔹 Expense and Income Tracking

* Add and manage income/expense entries
* Category-based tracking (Food, Rent, Travel, etc.)
* Edit and delete transactions

### 🔹 Budget and Savings Goals

* Set monthly budgets
* Track spending against budget
* Monitor savings goals

### 🔹 Financial Trends and Visualization

* Monthly spending comparison
* Pie charts for category-wise spending
* Bar charts for income vs expenses

### 🔹 Data Export and Community Forum (Planned)

* Export data to PDF/CSV
* Cloud backup support
* Community forum for financial tips

---

## 📊 Evaluation Criteria

* ✅ Week 2: Login and profile setup operational
* ✅ Week 4: Transaction tracking functional
* ✅ Week 6: Budget and savings tracking working
* ✅ Week 7: Visualizations implemented and accurate
* ⏳ Week 8: Export and cloud sync pending

---

## 🏗️ System Architecture

(To be added)

---

## 🔄 Workflow Diagram

(To be added)

---

## 🗄️ Database Schema

(To be added)

---

## 🛠️ Technologies Used

* **Backend:** Java, Spring Boot
* **Frontend:** HTML, CSS (Static pages)
* **Database:** (Configure in application.properties, e.g., MySQL)
* **Build Tool:** Gradle
* **Authentication:** JWT & Spring Security
* **Visualization:** Chart-based frontend (HTML pages)

---

## 📂 Project Structure

```
tracker/
│── src/main/java/com/finance/tracker
│   ├── config/               # Security configuration (JWT, Spring Security)
│   ├── controller/           # REST Controllers (Auth, Budget, Transactions, Savings)
│   ├── dto/                  # Data Transfer Objects
│   ├── model/                # Entity classes
│   ├── repository/           # JPA Repositories
│   ├── service/              # Business logic layer
│   ├── security/             # Security-related classes
│   ├── HomeController.java   # Home routing controller
│   └── TrackerApplication.java # Main Spring Boot application
│
│── src/main/resources
│   ├── static/               # Frontend HTML pages
│   │   ├── index.html
│   │   ├── login.html
│   │   ├── dashboard.html
│   │   └── budget.html
│   ├── templates/            # (Optional template engine)
│   └── application.properties # App configuration
│
│── src/test/                 # Test cases
│── build.gradle              # Gradle build configuration
```

---

## ▶️ How to Run the Project

1. Clone the repository
2. Navigate to the project folder:

   ```bash
   cd tracker
   ```
3. Configure database in `application.properties`
4. Build the project:

   ```bash
   ./gradlew build
   ```
5. Run the application:

   ```bash
   ./gradlew bootRun
   ```
6. Open browser:

   ```
   http://localhost:8080
   ```

---

## 📌 Future Enhancements

* Mobile application version 📱
* AI-based spending insights 🤖
* Smart budget recommendations 💡
* Multi-currency support 🌍

---

## 👩‍💻 Author

**Vaishnavi Mani**

---

## ⭐ Notes

This project is currently under development and has been completed up to Week 7 milestones. Remaining features will be implemented in the final phase.
