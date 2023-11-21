package tech.reliab.course.morozovda.bank.exception;

public class CreditException extends RuntimeException {
	public CreditException() {
		super("Error: can't give credit");
	}
}