package tech.reliab.course.morozovda.bank.exception;

public class NotFoundException extends Exception {
	public NotFoundException(int id) {
		super("Error: entity with id=" + id + " doesn't exist");
	}
}
