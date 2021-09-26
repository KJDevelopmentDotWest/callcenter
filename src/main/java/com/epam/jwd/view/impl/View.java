package com.epam.jwd.view.impl;

import java.util.Scanner;

public class View {


    private static final String START_CONVERSATION_STRING = "Order creation started:";
    private static final String WAIT_STRING = "Wait for free operator or press 1 to exit";
    private static final String OPTIONS_MENU_STRING =
            " 1 - Add device\n" +
            " 2 - Remove device\n" +
            " 3 - Add telephone number\n" +
            " 4 - Print default devices\n" +
            " 5 - Print current order devices\n" +
            " 6 - Exit and Save\n" +
            " 7 - Exit and Disband";
    private static final String INVALID_OPTION_CHOSEN_STRING = "Invalid option chosen, pls try again";
    private final Scanner scanner = new Scanner(System.in);

    public void waitStartMessage(){
        System.out.println(WAIT_STRING);
    }

    public void callStartMessage(){
        System.out.println(START_CONVERSATION_STRING);
    }

    public void printOptionsMenu(){
        System.out.println(OPTIONS_MENU_STRING);
    }

    public void printInvalidInputMessage(){
        System.out.println(INVALID_OPTION_CHOSEN_STRING);
    }

    public int readUserIntInput(){
        return scanner.nextInt();
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
