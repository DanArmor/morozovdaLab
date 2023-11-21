package tech.reliab.course.morozovda.bank.exception;

public class NoPaymentAccount extends RuntimeException {
	public NoPaymentAccount() {
		super("Error: can't find payment account");
	}
}