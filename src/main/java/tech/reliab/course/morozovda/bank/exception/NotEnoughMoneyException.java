package tech.reliab.course.morozovda.bank.exception;

public class NotEnoughMoneyException extends RuntimeException {
	public NotEnoughMoneyException() {
		super("Error: not enough money");
	}
}