package org.everestp;

import org.everestp.views_cli.MenuCLI;

public class Main {

    public static void main(String[] args) {
        DatabaseConnection.createConnection();
        MenuCLI menuCLI = new MenuCLI();
    }
}