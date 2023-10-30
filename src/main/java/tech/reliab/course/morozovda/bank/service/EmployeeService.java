package tech.reliab.course.morozovda.bank.service;

import java.util.List;
import java.util.UUID;

import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;

public interface EmployeeService {
    Employee create(Employee employee);

    public Employee getEmployeeById(UUID id);

    public List<Employee> getAllEmployees();

    boolean transferEmployee(Employee employee, BankOffice bankOffice);
}
