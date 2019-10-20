package com.example.worldinfoapplication.util;

import java.io.Serializable;

public class SessionInfo implements Serializable {
    /**
     * Instance (Singleton)
     */
    private static SessionInfo instance = new SessionInfo();

    /**
     * Private constructor
     */
    private SessionInfo() {
    }

    /**
     * To get the singleton instance.
     *
     * @return SessionInfo instance
     */
    public static SessionInfo getInstance() {
        return instance;
    }

}
