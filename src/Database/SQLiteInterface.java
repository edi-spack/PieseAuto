package Database;

import Exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteInterface {
    public SQLiteInterface() throws DatabaseException {
        String query = "CREATE TABLE IF NOT EXISTS parts ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT, "
                    + "brand TEXT, "
                    + "model TEXT, "
                    + "price REAL, "
                    + "stock INTEGER"
                    + ");";

        executeQuery(query);
    }

    public void addPart(String id, String name, String brand, String model, double price, int stock) throws DatabaseException {
        String query = "INSERT INTO parts VALUES ("
                + id + ", "
                + name + ", "
                + brand+ ", "
                + model + ", "
                + price + ", "
                + stock
                + ");";

        executeQuery(query);
    }

    public void removePart(String id) throws DatabaseException {
        String query = "DELETE FROM parts WHERE id = '" + id + "';";
        executeQuery(query);
    }

    public void updatePart(String id, String name, String brand, String model, double price, int stock) throws DatabaseException {
        String query = "UPDATE parts SET "
                + "name = " + name + ", "
                + "brand = " + brand+ ", "
                + "model = " + model + ", "
                + "price = " + price + ", "
                + "stock = " + stock + " "
                + "WHERE id = " + id + ";";

        executeQuery(query);
    }

    private void executeQuery(String query) throws DatabaseException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:parts.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate(query);
        }
        catch(SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        finally {
            try {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
    }
}
