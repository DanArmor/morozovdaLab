package tech.reliab.course.morozovda.bank.service.impl;

import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee create(Employee employee) {
        if (employee == null) {
            return null;
        }

        if (employee.getSalary().signum() < 0) {
            System.err.println("Error: Employee - salary must be non-negative");
            return null;
        }

        // TODO: Добавить механизм проверки на имеющийся офис и добавление в офис

        return new Employee(employee);
    }

    @Override
    public boolean transferEmployee(Employee employee, BankOffice bankOffice) {
        // TODO: Добавить механизм перевода сотрудника в новый офис

        return true;
    }

}
