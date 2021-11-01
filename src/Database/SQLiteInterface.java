package Database;

import Common.AutoPart;
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

        executeSqlUpdate(query);
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

        executeSqlUpdate(query);
    }

    public void removePart(String id) throws DatabaseException {
        String query = "DELETE FROM parts WHERE id = '" + id + "';";
        executeSqlUpdate(query);
    }

    public void updatePart(String id, String name, String brand, String model, double price, int stock) throws DatabaseException {
        String query = "UPDATE parts SET "
                + "name = " + name + ", "
                + "brand = " + brand+ ", "
                + "model = " + model + ", "
                + "price = " + price + ", "
                + "stock = " + stock + " "
                + "WHERE id = " + id + ";";

        executeSqlUpdate(query);
    }

    public AutoPart getPart(String id) throws DatabaseException {
        String query = "SELECT id, name, brand, model, price, stock "
                + "FROM parts WHERE id = " + id + ";";

        ResultSet resultSet = executeSqlQuery(query);

        try {
            resultSet.next();
            String name = resultSet.getString("name");
            String brand = resultSet.getString("brand");
            String model = resultSet.getString("model");
            double price = resultSet.getDouble("price");
            int stock = resultSet.getInt("stock");

            return new AutoPart(id, name, brand, model, price, stock);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void executeSqlUpdate(String query) throws DatabaseException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:parts.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
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

    private ResultSet executeSqlQuery(String query) throws DatabaseException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:parts.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            return statement.executeQuery(query);
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
