package tech.reliab.course.morozovda.bank.service;

import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;

public interface EmployeeService {
    Employee create(Employee employee);

    boolean transferEmployee(Employee employee, BankOffice bankOffice);
}
