# AAD-JSP-Assignment
# 📢 Complaint Management System (CMS)

A full-stack web application that allows users to submit complaints and track their status, while admins and employees can efficiently manage and resolve these complaints. This project follows the **MVC architecture** using **Java Servlets, JSP, JDBC**, and **Apache DBCP2** for database interaction.

---

## 🚀 Project Overview

The **Complaint Management System** simplifies and streamlines the process of filing and resolving complaints in an organization. It includes role-based access for employees, and admins, and provides real-time updates on complaint statuses.

---

## 🧩 Technologies Used

| Layer         | Technologies / Tools                      |
|---------------|-------------------------------------------|
| Frontend      | HTML, CSS, Bootstrap, JSP                 |
| Backend       | Java (Servlets, JDBC)                     |
| Architecture  | MVC (Model-View-Controller)               |
| Database      | MySQL                                     |
| Connection    | Apache Commons DBCP2 (Connection Pooling) |
| Server        | Apache Tomcat                             |
| Tools         | IntelliJ IDEA, Postman, Git               |

---

## 📂 Project Structure
![Backend.png](web%2Fscreenshots%2FBackend.png)
![Frontend.png](web%2Fscreenshots%2FFrontend.png)

---

## 🔑 User Roles & Access

| Role         | Capabilities                                                                              |
|--------------|-------------------------------------------------------------------------------------------|
| **Employee** | Submit new complaint, view personal complaint list, update & delete unresolved complaints |
| **Admin**    | Full control: View all complaints, update & delete any complaint                          |

---

## 📊 Dashboard Features

Each user type has their own dashboard with relevant stats and actions.

---


## 🔧 How to Run Locally

### 1. Clone the repository
```bash
git clone https://github.com/Seenathul-Ilma/AAD-JSP-Assignment.git
cd CMS
```   

### 2. Set up the MySQL database

```sql
CREATE DATABASE complaintdb;
USE complaintdb;

CREATE TABLE user (
  user_id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  password VARCHAR(255),
  role VARCHAR(20)
);

CREATE TABLE complaint (
  complaint_id VARCHAR(255) PRIMARY KEY,
  user_id VARCHAR(255),
  title VARCHAR(150),
  description TEXT,
  date_submitted DATETIME,
  status VARCHAR(100),
  admin_remarks TEXT,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);
```  

### 3. Configure Database Connection
Configure your BasicDataSource with:
```
BasicDataSource ds = new BasicDataSource();
ds.setUrl("jdbc:mysql://localhost:3306/complaintdb");
ds.setUsername("your-username");
ds.setPassword("your-password");
ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

ServletContext context = getServletContext();
context.setAttribute("dataSource", ds);
```

📌 Place this in an AppInitServlet with loadOnStartup = 1 or use a ServletContextListener to ensure it initializes on server start.

### 4. Deploy on Apache Tomcat
Build the WAR or deploy the project folder to webapps/

Start the Tomcat server

Visit: http://localhost:8080/CMS_Web_exploded/

## 💡 Future Improvements

- Password encryption using BCrypt for enhanced security
- AJAX-based dynamic updates for smoother user experience
- Export complaints as PDF/Excel reports for easy sharing and record-keeping
- Develop a REST API version with JSON responses for better integration and flexibility

---

## 👩‍💻 Author

**Zeenathul Ilma**
- GitHub: [@Seenathul-Ilma](https://github.com/Seenathul-Ilma)
- Website: [zeenathulilma.vercel.app](https://zeenathulilma.vercel.app)

---

## 📝 License 1: Academic and Personal Learning License
This project is © 2025 Zeenathul Ilma. It is not open-source and may not be reused or copied without permission.

This project was created and developed by Zeenathul Ilma as part of academic and personal learning initiatives.
All rights reserved. No part of this project may be copied, reused, or distributed without written permission from the author.

🚫 Copying or reusing this code without permission is strictly prohibited and may result in academic consequences.

## 📝 License 2: Proprietary License
This project is licensed under a [Proprietary License](LICENSE.txt)
