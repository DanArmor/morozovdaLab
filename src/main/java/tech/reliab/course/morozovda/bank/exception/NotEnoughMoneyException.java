package tech.reliab.course.morozovda.bank.exception;

public class NotEnoughMoneyException extends Exception {
	public NotEnoughMoneyException() {
		super("Error: not enough money");
	}
}