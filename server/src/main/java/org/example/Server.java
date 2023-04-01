package org.example;

import java.sql.SQLException;

public class Server extends Mediator {

    public static void main(String[] args) throws SQLException {

        Mediator mediator = new Mediator();
        while (true) {
            mediator.connectionWithClient();
        }
    }

}