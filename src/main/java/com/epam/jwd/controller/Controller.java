package com.epam.jwd.controller;

import com.epam.jwd.view.impl.View;

//this app is so simple that this class is absolutely useless

public class Controller {

    private final View view;

    public Controller(){
        view = new View();
    }

    public void waitStartMessage(){
        view.waitStartMessage();
    }

    public void callStartMessage(){
        view.callStartMessage();
    }

    public void printOptionsMenu(){
        view.printOptionsMenu();
    }

    public int readUserIntInput(){
        return view.readUserIntInput();
    }

    public void printInvalidInputMessage(){
        view.printInvalidInputMessage();
    }

    public void printMessage(String message){
        view.printMessage(message);
    }
}

