package tech.reliab.course.morozovda.bank.exception;

public class NoPaymentAccountException extends RuntimeException {
	public NoPaymentAccountException() {
		super("Error: can't find payment account");
	}
}