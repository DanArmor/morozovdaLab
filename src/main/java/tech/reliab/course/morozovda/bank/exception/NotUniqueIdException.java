package tech.reliab.course.morozovda.bank.exception;

public class NotUniqueIdException extends Exception {
	public NotUniqueIdException(int id) {
		super("Error: entity with id=" + id + " already exists");
	}
}