package com.sbu.main;

/**
 * Created by nicholasgenco on 3/1/17.
 */

public class MySQLNotConnectedException extends Exception {

    public MySQLNotConnectedException() {

    }

    public MySQLNotConnectedException(String message) {
        super(message);

    }

    public MySQLNotConnectedException(Throwable cause) {
        super(cause);
    }

    public MySQLNotConnectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
