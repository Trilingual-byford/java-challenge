package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @Column(name = "EMPLOYEE_SALARY")
    private Integer salary;

    @Getter
    @Setter
    @Column(name = "DEPARTMENT")
    private String department;

    public Employee(){ }

    public Employee(Long i, String name, int salary, String department) {
        this.id = i;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

}
