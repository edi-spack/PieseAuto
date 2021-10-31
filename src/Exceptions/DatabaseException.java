package Exceptions;

public class DatabaseException extends Exception{
    public DatabaseException(String message) {
        System.err.println(message);
    }
}
