package cn.ls.jdbc;

import java.sql.*;

/**
 * Created by Administrator on 2016/11/28.
 */
public class JdbcCRUD {

    static Connection conn = null; // 连接对象
    static Statement stmt = null; // 执行sql语句的句柄对象

    static {
        try {
            // 使用Class.forName()方式来加载数据库的驱动类
            // Class.forName()是Java提供的一种基于反射的方式，直接根据类的全限定名（包+类）
            // 从类所在的磁盘文件（.class文件）中加载类对应的内容，并创建对应的Class对象
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/clickstream",
                        "root",
                        "999");
                stmt = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        insert();
//        update();
//        select();
        delete();
    }

    private static void insert() {
        String sql = "insert into test_user(name,age) values('李四',26)";
        int rtn = 0;
        try {
            rtn = stmt.executeUpdate(sql);
            System.out.println("SQL语句影响了【" + rtn + "】行。");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 最后一定要记得在finally代码块中，尽快在执行完SQL语句之后，就释放数据库连接
            try {
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void update() {
        try {

            String sql = "update test_user set age=27 where name='李四'";
            int rtn = stmt.executeUpdate(sql);

            System.out.println("SQL语句影响了【" + rtn + "】行。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 测试删除数据
     */
    private static void delete() {

        try {
            String sql = "delete from test_user where name='李四'";
            int rtn = stmt.executeUpdate(sql);

            System.out.println("SQL语句影响了【" + rtn + "】行。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * 测试查询数据
     */
    private static void select() {

        // 对于select查询语句，需要定义ResultSet
        // ResultSet就代表了，你的select语句查询出来的数据
        // 需要通过ResutSet对象，来遍历你查询出来的每一行数据，然后对数据进行保存或者处理
        ResultSet rs = null;

        try {
            String sql = "select * from test_user";
            rs = stmt.executeQuery(sql);

            // 获取到ResultSet以后，就需要对其进行遍历，然后获取查询出来的每一条数据
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                System.out.println("id=" + id + ", name=" + name + ", age=" + age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


}
