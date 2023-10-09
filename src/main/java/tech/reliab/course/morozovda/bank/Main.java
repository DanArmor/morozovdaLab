package tech.reliab.course.morozovda.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import tech.reliab.course.morozovda.bank.entity.Bank;
import tech.reliab.course.morozovda.bank.entity.BankAtm;
import tech.reliab.course.morozovda.bank.entity.BankOffice;
import tech.reliab.course.morozovda.bank.entity.Client;
import tech.reliab.course.morozovda.bank.entity.CreditAccount;
import tech.reliab.course.morozovda.bank.entity.Employee;
import tech.reliab.course.morozovda.bank.entity.PaymentAccount;
import tech.reliab.course.morozovda.bank.service.AtmService;
import tech.reliab.course.morozovda.bank.service.BankOfficeService;
import tech.reliab.course.morozovda.bank.service.BankService;
import tech.reliab.course.morozovda.bank.service.CreditAccountService;
import tech.reliab.course.morozovda.bank.service.EmployeeService;
import tech.reliab.course.morozovda.bank.service.ClientService;
import tech.reliab.course.morozovda.bank.service.PaymentAccountService;
import tech.reliab.course.morozovda.bank.service.impl.AtmServiceImpl;
import tech.reliab.course.morozovda.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.morozovda.bank.service.impl.BankServiceImpl;
import tech.reliab.course.morozovda.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.morozovda.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.morozovda.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.morozovda.bank.service.impl.ClientServiceImpl;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        BankService bankService = new BankServiceImpl();
        Bank bank = bankService.create(new Bank("Iron Bank of Braavos"));
        System.out.println(bank);

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        BankOffice bankOffice = bankOfficeService.create(new BankOffice(
                "Main office of Iron Bank of Braavos",
                "Free City of Braavos",
                bank,
                true,
                true,
                0,
                true,
                true,
                true,
                bank.getTotalMoney(),
                new BigDecimal("700")));
        System.out.println(bankOffice);

        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService
                .create(new Employee("Tycho Nestoris", LocalDate.of(270, 2, 21), Employee.Job.Worker, bank, true,
                        bankOffice, true, new BigDecimal("10")));
        System.out.println(employee);

        AtmService atmService = new AtmServiceImpl();
        BankAtm bankAtm = atmService.create(new BankAtm("First ATM of Braavos", bankOffice.getAddress(), BankAtm.Status.WORKING, bank,
                bankOffice, employee, true, true, new BigDecimal("0"), new BigDecimal("25")));
        System.out.println(bankAtm);

        ClientService userService = new ClientServiceImpl();
        Client user = userService
                .create(new Client("Stannis Baratheon", LocalDate.of(264, 2, 15), "Dragon Stone",
                        new BigDecimal("1000"), bank, new BigDecimal("999999999")));
        System.out.println(user);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        PaymentAccount paymentAccount = paymentAccountService
                .create(new PaymentAccount(user, bank, new BigDecimal("9000")));
        System.out.println(paymentAccount);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        CreditAccount creditAccount = creditAccountService.create(new CreditAccount(user, bank,
                LocalDate.of(298, 1, 1), LocalDate.of(302, 1, 1), 48, new BigDecimal("29000"),
                new BigDecimal("29000"),
                new BigDecimal("100"), new BigDecimal("2"), employee, paymentAccount));
        System.out.println(creditAccount);
    }
}