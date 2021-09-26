package com.epam.jwd.service.exception;

public class WrongThreadException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Permission Denied: Method called from wrong thread";
    private static final String DEFAULT_MESSAGE_WITH_THREAD_NAME_PROVIDED = "Permission Denied: Method called from";

    public WrongThreadException(){
        super(DEFAULT_MESSAGE);
    }

    public WrongThreadException(String threadName){
        super(DEFAULT_MESSAGE_WITH_THREAD_NAME_PROVIDED + " " + threadName);
    }

    public WrongThreadException(String message, String threadName){
        super(message + " " + threadName);
    }
}
