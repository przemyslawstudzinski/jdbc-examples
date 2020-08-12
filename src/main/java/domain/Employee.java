package domain;

import java.sql.Date;

public final class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private float salary;
    private Date employmentDate;
    private String title;

    public Employee(int id, String firstName, String lastName, float salary, Date employmentDate, String title) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.employmentDate = employmentDate;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public float getSalary() {
        return salary;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", employmentDate=" + employmentDate +
                ", title='" + title + '\'' +
                '}';
    }
}
