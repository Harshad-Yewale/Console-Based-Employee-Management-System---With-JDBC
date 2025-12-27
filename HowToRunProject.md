<h2>⚙️ Installation & Running the Project</h2>

<p>
This project is a <strong>console-based Java backend application</strong> built using
<strong>Core Java, JDBC, and MySQL</strong>. No frameworks are required.
</p>

<p>
You only need:
</p>

<ul>
  <li>Java (JDK 17 or compatible)</li>
  <li>MySQL</li>
  <li>Any IDE (IntelliJ IDEA / VS Code)</li>
</ul>

<hr>

<h3>🗄️ 1. Database Setup</h3>

<p>Create a MySQL database and table using the following SQL:</p>

<pre>
CREATE DATABASE EmployeeDB;
USE EmployeeDB;

CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeName VARCHAR(50) NOT NULL,
    EmployeePosition VARCHAR(50) NOT NULL,
    EmployeeSalary DECIMAL(10,2) NOT NULL CHECK (EmployeeSalary &gt; 0)
);
</pre>

<hr>

<h3>🔐 2. Database Credentials Configuration</h3>

<p>
The application reads database credentials from <strong>environment variables</strong>
(not hardcoded in source code).
</p>

<p>The following variables are required:</p>

<ul>
  <li><strong>URL</strong> → JDBC connection URL</li>
  <li><strong>USERNAME</strong> → MySQL username</li>
  <li><strong>PASSWORD</strong> → MySQL password</li>
</ul>

<p><strong>Example JDBC URL:</strong></p>

<pre>jdbc:mysql://localhost:3306/EmployeeDB</pre>

<hr>

<h4>🟢 Option A: IntelliJ IDEA (System Environment Variables)</h4>

<ol>
  <li>Go to <strong>Run → Edit Configurations</strong></li>
  <li>Select your application run configuration</li>
  <li>Open <strong>Environment Variables</strong></li>
  <li>Add the following:</li>
</ol>

<pre>
URL=jdbc:mysql://localhost:3306/EmployeeDB
USERNAME=your_mysql_username
PASSWORD=your_mysql_password
</pre>

<p>Click <strong>Apply</strong> and <strong>Run</strong>.</p>

<hr>

<h4>🟢 Option B: VS Code (.env file)</h4>

<p>Create a <code>.env</code> file in the project root:</p>

<pre>
URL=jdbc:mysql://localhost:3306/EmployeeDB
USERNAME=your_mysql_username
PASSWORD=your_mysql_password
</pre>

<p>
Make sure your environment or extension loads the <code>.env</code> file
before running the application.
</p>

<p><strong>Note:</strong> Do not commit the <code>.env</code> file to GitHub.</p>

<hr>

<h3>▶️ 3. Run the Application</h3>

<ol>
  <li>Open the project in your IDE</li>
  <li>Locate <code>App.java</code> inside the <code>main</code> package</li>
  <li>Run the <code>main()</code> method</li>
</ol>

<p>
Once running, the console-based menu will appear and you can:
</p>

<ul>
  <li>Add employees</li>
  <li>List and sort employees</li>
  <li>Search employees</li>
  <li>Update employee details</li>
  <li>Delete employees</li>
</ul>

<hr>

<h3>⚠️ Important Notes</h3>

<ul>
  <li>This project is intentionally built <strong>without frameworks</strong> to focus on fundamentals</li>
  <li>All SQL operations use <code>PreparedStatement</code></li>
  <li>Collections are used for sorting and in-memory processing</li>
</ul>

<p>
Future versions may convert this project into a <strong>Spring Boot REST API</strong>.
</p>
