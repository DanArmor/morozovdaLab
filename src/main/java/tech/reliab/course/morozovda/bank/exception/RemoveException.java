package tech.reliab.course.morozovda.bank.exception;

public class RemoveException extends Exception {
	public RemoveException() {
		super("Error: can't remove entity");
	}
}