package com.javaweb.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static final String DB_URL =
		    "jdbc:mysql://shinkansen.proxy.rlwy.net:14264/railway?useSSL=true&requireSSL=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "iSEMlMNWDbbTRRbXSENiOphCTNzDNHnc";
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
