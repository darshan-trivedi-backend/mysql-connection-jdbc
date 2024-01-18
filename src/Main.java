/*
     * Steps to connect your app with your db
     * 1. import package
     * 2. load & register
     * 3. create statement
     * 4. execute statement
     * 5. process the result
     * 6. close the connection
     *
     * student (id, name, email)
     * mysql> desc student;
       +-------+--------------+------+-----+---------+-------+
       | Field | Type         | Null | Key | Default | Extra |
       +-------+--------------+------+-----+---------+-------+
       | id    | int          | YES  |     | NULL    |       |
       | name  | varchar(200) | YES  |     | NULL    |       |
       | email | varchar(200) | YES  |     | NULL    |       |
       +-------+--------------+------+-----+---------+-------+
       3 rows in set (0.01 sec)
     *

     */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Student {
    public static void executeQuery(String query) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/students?characterEncoding=utf8";
            String username = "root";
            String password = "password";

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            // Check if it's a SELECT query
            if (query.trim().toLowerCase().startsWith("select")) {
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    System.out.println("ID = " + rs.getInt(1));
                    System.out.println("Name = " + rs.getString(2));
                    System.out.println("Email = " + rs.getString(3));
                    System.out.println("---------------");
                }
            } else {
                boolean bl = statement.execute(query);

                if (bl) {
                    System.out.println(query + " Executed :)");
                }
            }

            // Close resources
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createStudentTable() {
        try {
            String query = "create table if not exists student (id int(3), name varchar(200), email varchar(200));";
            executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addStudent(int id, String name, String emailId) {
        try {
            String query = "insert into student(id, name, email) values (" + id + ", '" + name + "', '" + emailId + "')";
            executeQuery(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String query = "delete from student where id = " + id;
        executeQuery(query);
    }

    public void getAllStudent() {
        String query = "select * from student";
        executeQuery(query);
    }

    public void getStudentById(int id) {
        String query = "select * from student where id = " + id;
        executeQuery(query);
    }

    public void updateStudent() {
        String query = "update student set name = 'pratik' where id = 2";
        executeQuery(query);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.createStudentTable();
        s.getAllStudent();
        s.addStudent(1, "Darshan", "dbt@gmail.com");
        s.addStudent(2, "Dilip", "dks@gmail.com");
        s.getStudentById(1);
        s.updateStudent();
        s.getAllStudent();
        s.deleteStudent(2);
        s.getAllStudent();
    }
}
