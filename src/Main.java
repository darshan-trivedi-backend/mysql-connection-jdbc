import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

class Student {
    /*
     * Steps to connect your app with your db
     * 1. import package
     * 2. load & register
     * 3. create statement
     * 4. execute statement
     * 5. process the result
     * 6. close the connection
     */

    public void createDBConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/students?characterEncoding=utf8";
            String username = "root";
            String password = "password";

            Connection connection = DriverManager.getConnection(url, username, password);


            Statement statement = connection.createStatement();
            String query = "create table student (id int(3), name varchar(200), email varchar(200));";

            boolean bl = statement.execute(query);

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.createDBConnection();
    }
}
