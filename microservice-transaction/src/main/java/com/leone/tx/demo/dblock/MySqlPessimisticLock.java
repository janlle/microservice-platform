package com.leone.tx.demo.dblock;

import java.sql.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-16
 **/
public class MySqlPessimisticLock {

    private static final String url = "jdbc:mysql://39.108.125.41:3306/boot?useSSL=false";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "cloud";


    public static void main(String[] args) {
        pessimisticLock();
    }


    private static void pessimisticLock() {
        int count = 100;
        while (count > 0) {
            count--;
            new Thread(() -> {
                Statement statement = null;
                Connection conn = null;
                ResultSet resultSet = null;
                try {
                    Class.forName(name);
                    conn = DriverManager.getConnection(url, user, password);
                    beginTransaction(conn);
                    statement = conn.createStatement();
                    String querySql = "SELECT * FROM t_account WHERE account_id = 1 for UPDATE";
                    resultSet = statement.executeQuery(querySql);
                    int money = 0;
                    while (resultSet.next()) {
                        System.out.println(Thread.currentThread().getName() + "抢到了锁 accountId: " + resultSet.getString("account_id")
                                + " userId: " + resultSet.getString("user_id")
                                + " money: " + resultSet.getString("money"));

                        money = resultSet.getInt("money");
                    }
                    String updateSql = "UPDATE t_account SET money = " + (money - 1) + " WHERE account_id = 1 AND money > 0";

                    // 执行 SQL， rows 代表执行 SQL 语句改变数据库的行数
                    int rows = statement.executeUpdate(updateSql);

                    if (rows > 0) {
                        System.out.println("更新成功" + Thread.currentThread().getName() + "  库存金额: " + (money - 1));
                        commitTransaction(conn);
                    } else {
                        System.out.println("更新失败" + Thread.currentThread().getName() + "  库存金额: " + money);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                        if (statement != null) {
                            statement.close();
                        }
                        if (resultSet != null) {
                            resultSet.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }, "线程--: " + count).start();
        }
    }


    /**
     * 开始事务
     *
     * @param connection
     */
    public static void beginTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 提交事务
     *
     * @param connection
     */
    public static void commitTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
