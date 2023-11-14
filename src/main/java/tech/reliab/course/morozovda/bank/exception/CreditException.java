package tech.reliab.course.morozovda.bank.exception;

public class CreditException extends Exception {
	public CreditException() {
		super("Error: can't give credit");
	}
}