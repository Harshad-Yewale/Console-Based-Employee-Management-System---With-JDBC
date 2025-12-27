<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Management System - Core Java & JDBC</title>
</head>
<body>

<h1>Employee Management System (Core Java + JDBC)</h1>
<h3>More than a CRUD console app — a layered Java backend project</h3>

<p>
A <strong>console-based Employee Management System</strong> built using
<strong>Core Java, JDBC, MySQL</strong>, and a clean
<strong>DAO + Service layer architecture</strong>.
</p>

<p>
🎯 <strong>Goal:</strong> To understand how real Java backend applications interact
with databases <em>without frameworks</em> and how the
<strong>Collections Framework</strong> works alongside JDBC.
</p>

<p>
👉 <strong>
<a href="HowToRunProject.md">Click here to view the full setup & run instructions</a>
</strong>
</p>

<hr>

<h2>🚀 Features</h2>
<ul>
    <li>Add new employees</li>
    <li>List employees
        <ul>
            <li>Normal order</li>
            <li>Sort by salary (ascending / descending)</li>
            <li>Sort by name</li>
        </ul>
    </li>
    <li>Search employees by:
        <ul>
            <li>ID</li>
            <li>Name (using <code>LIKE</code>)</li>
            <li>Position</li>
            <li>Salary (less than / greater than)</li>
        </ul>
    </li>
    <li>Update employee details</li>
    <li>Delete employee</li>
</ul>

<p>
All operations use <strong>PreparedStatement</strong> for safety and reliability.
</p>

<hr>

<h2>🛠️ Tech Stack</h2>
<ul>
    <li>Java (Core Java)</li>
    <li>JDBC</li>
    <li>MySQL</li>
    <li>Collections Framework</li>
    <li>DAO & Service Layer Architecture</li>
</ul>

<hr>

<h2>🧱 Project Architecture</h2>

<pre>
src/
│
├── main/
│   └── App.java                → Application entry point (menu & input)
│
├── domain/
│   └── Employee.java           → Entity / Model class
│
├── dao/
│   ├── EmployeeDao.java        → DAO interface
│   └── EmployeeDaoImpl.java    → JDBC implementation
│
├── service/
│   └── EmployeeService.java    → Business logic & validations
│
└── util/
    └── DBConnection.java       → Database connection utility
</pre>

<hr>

<h2>🧠 Key Design Decisions</h2>

<h3>DAO Layer</h3>
<ul>
    <li>Handles only database operations</li>
    <li>Uses <code>PreparedStatement</code> to prevent SQL injection</li>
    <li>Maps <code>ResultSet</code> to <code>Employee</code> objects</li>
</ul>

<h3>Service Layer</h3>
<ul>
    <li>Contains business logic and validations</li>
    <li>Uses Collections for sorting and filtering</li>
    <li>Controls application flow (search, update, delete)</li>
</ul>

<h3>App Layer</h3>
<ul>
    <li>Handles user input and output</li>
    <li>Delegates logic to the service layer</li>
    <li>Keeps UI separate from business logic</li>
</ul>

<hr>

<h2>🗄️ Database Schema</h2>

<pre>
CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeName VARCHAR(50) NOT NULL,
    EmployeePosition VARCHAR(50) NOT NULL,
    EmployeeSalary DECIMAL(10,2) NOT NULL CHECK (EmployeeSalary > 0)
);
</pre>

<hr>

<h2>🧪 Example Operations</h2>

<h3>Add Employee</h3>
<pre>
Enter Employee Name: John
Enter Employee Position: Developer
Enter Employee Salary: 55000
</pre>

<h3>Search by Name (LIKE)</h3>
<pre>
SELECT * FROM Employee WHERE EmployeeName LIKE '%John%';
</pre>

<h3>Update Employee</h3>
<ul>
    <li>Search employee by ID</li>
    <li>Display existing details</li>
    <li>Update name, position, and salary safely</li>
</ul>

<hr>

<h2>📚 What I Learned</h2>
<ul>
    <li>Correct usage of <code>PreparedStatement</code></li>
    <li>Why column names cannot be parameterized</li>
    <li>How Collections complement databases</li>
    <li>DAO vs Service responsibilities</li>
    <li>Debugging logical and JDBC-related bugs</li>
    <li>Writing cleaner, maintainable backend code</li>
</ul>

<hr>

<h2>🟢 Why This Project Matters</h2>
<ul>
    <li>Not a copy-paste CRUD project</li>
    <li>Focuses on backend fundamentals</li>
    <li>Clean separation of concerns</li>
    <li>Strong foundation for Spring Boot & JPA</li>
</ul>

<hr>

<h2>🔮 Future Improvements</h2>
<ul>
    <li>Convert to Spring Boot REST API</li>
    <li>Add JUnit tests</li>
    <li>Implement pagination</li>
    <li>Add transaction management</li>
    <li>Expose REST endpoints</li>
</ul>

<hr>

<h2>👤 Author</h2>
<p>
<strong>Harshad Yewale</strong><br>
</p>

<hr>

<h2>⭐ Final Note</h2>
<p>
This project is intentionally built <strong>without frameworks</strong> to understand
how Java backend systems work internally.
It serves as a strong foundation for advanced backend development.
</p>

</body>
</html>
