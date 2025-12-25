package domain;

public class Employee {
    
    private int employeeId;
    private String employeeName;
    private String employeePosition;
    private double employeeSalary;

    public Employee(String employeeName, String employeePosition, double employeeSalary) {
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.employeeSalary = employeeSalary;
    }

    public Employee(int employeeId, String employeeName, String employeePosition, double employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.employeeSalary = employeeSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }
}
