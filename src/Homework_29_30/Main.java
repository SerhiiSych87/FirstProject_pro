package Homework_29_30;

// Створити базу даних у Workbench та підключити до IntelijIdea та створити тестову таблицю.
// Заповнити її даними за допомогою запитів MySQL у IntelijIdea. Використовуючи JDBC, написати приклад виконання всіх запитів.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/programming";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root123";

    public static void main(String[] args) throws SQLException {

        registerDriver();

        Connection connection_my = null;
        Statement statement = null;

        try {

            // using statements

            connection_my = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            statement = connection_my.createStatement();

            statement.execute("INSERT INTO courses (name_of_cource, number_of_students, comment) values ('Java for beginners', 40, 'Basic cource')");
            statement.execute("INSERT INTO courses (name_of_cource, number_of_students, comment) values ('Java', 30, 'Comprehensive study')");

            statement.execute("alter table courses add teacher varchar (25)");

            statement.execute("INSERT INTO courses (name_of_cource, number_of_students, comment, teacher) values ('Cotlin', 25, 'Addition', 'Petrovich')");

            // use temp variable for update

            int changed = statement.executeUpdate("UPDATE courses set name_of_cource = 'Java Pro' where name_of_cource = 'Java'");
            int changed_t = statement.executeUpdate("UPDATE courses set teacher = 'Krasnui' where name_of_cource LIKE 'Java%'");

            System.out.println("changed");


            // using package adding - - - Batches

            statement.addBatch("insert into courses (name_of_cource, number_of_students, comment, teacher) values ('C++', 28, 'Basic knowledge', 'Lewandowski')");
            statement.addBatch("insert into courses (name_of_cource, number_of_students, comment, teacher) values ('Python', 33, 'modern programming language', 'Krasnui')");

            boolean closed_my = statement.isClosed();
            System.out.println(closed_my);


        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                connection_my.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}