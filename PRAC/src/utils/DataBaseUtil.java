package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {

    //�������ݿ�
    public static Connection getConn() {
        Connection conn = null;
        try {
            //�������ݿ�����
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=prac";
        try {
            conn = DriverManager.getConnection(url, "sa", "123456");
            if (conn != null) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("���ݿ����ӳɹ���");
        return conn;
    }

    //�ر����ݿ�
    public static void closeConn(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
