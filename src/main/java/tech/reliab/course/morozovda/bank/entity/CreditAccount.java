package tech.reliab.course.morozovda.bank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreditAccount extends Account {
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int monthCount;
    private BigDecimal creditAmount;
    private BigDecimal remainingCreditAmount;
    private BigDecimal montlyPayment;
    private BigDecimal interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    private void initWithDefaults() {
        dateStart = null;
        dateEnd = null;
        monthCount = 0;
        creditAmount = null;
        remainingCreditAmount = null;
        montlyPayment = null;
        interestRate = null;
        employee = null;
        paymentAccount = null;
    }

    public CreditAccount() {
        super();
        initWithDefaults();
    }

    public CreditAccount(User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
            BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
            BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    public CreditAccount(UUID id, User user, Bank bank, LocalDate dateStart, LocalDate dateEnd, int monthCount,
            BigDecimal creditAmount, BigDecimal remainingCreditAmount, BigDecimal montlyPayment,
            BigDecimal interestRate, Employee employee, PaymentAccount paymentAccount) {
        super(id, user, bank);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.monthCount = monthCount;
        this.creditAmount = creditAmount;
        this.remainingCreditAmount = remainingCreditAmount;
        this.montlyPayment = montlyPayment;
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "{" +
                " dateStart='" + getDateStart() + "'" +
                ", dateEnd='" + getDateEnd() + "'" +
                ", monthCount='" + getMonthCount() + "'" +
                ", creditAmount='" + getCreditAmount() + "'" +
                ", remainingCreditAmount='" + getRemainingCreditAmount() + "'" +
                ", montlyPayment='" + getMontlyPayment() + "'" +
                ", interestRate='" + getInterestRate() + "'" +
                ", employee='" + getEmployee() + "'" +
                ", paymentAccount='" + getPaymentAccount() + "'" +
                "}";
    }

    public LocalDate getDateStart() {
        return this.dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getMonthCount() {
        return this.monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount = monthCount;
    }

    public BigDecimal getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getRemainingCreditAmount() {
        return this.remainingCreditAmount;
    }

    public void setRemainingCreditAmount(BigDecimal remainingCreditAmount) {
        this.remainingCreditAmount = remainingCreditAmount;
    }

    public BigDecimal getMontlyPayment() {
        return this.montlyPayment;
    }

    public void setMontlyPayment(BigDecimal montlyPayment) {
        this.montlyPayment = montlyPayment;
    }

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PaymentAccount getPaymentAccount() {
        return this.paymentAccount;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

}
