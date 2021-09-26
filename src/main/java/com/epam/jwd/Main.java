package com.epam.jwd;

import com.epam.jwd.service.repositoryservice.Service;

//this main is for "real" use

public class Main {
    public static void main(String[] args) {
        Service service = Service.getInstance();
        service.addCustomer();
    }
}
