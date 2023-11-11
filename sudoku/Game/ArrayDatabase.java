package sudoku.Game;

import java.io.*;
import java.sql.*;

public class ArrayDatabase {

    //Connect to the database
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String url = "jdbc:derby:sudu;create=true";
    private static final String user = "";
    private static final String password = "";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            if (!isTableExist()) {
                createTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ;
    }

    public static boolean isTableExist() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url);
            String selectQuery = "select * from SUDU_GAME";
            preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet s = preparedStatement.executeQuery();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            connection.close();
            return false;

        }
        return true;
    }
    public static void createTable() throws SQLException {
        Connection connection = DriverManager.getConnection(url);

        // Retrieves the serialized array from the database
        String executeCreate = "CREATE TABLE SUDU_GAME (\n" +
                "   sudu_difficulty INT NOT NULL,\n" +
                "   sudu_status VARCHAR(255),\n" +
                "   sudu_data Blob,\n" +
                "   PRIMARY KEY (sudu_difficulty)\n" +
                ")";
        PreparedStatement preparedStatement = connection.prepareStatement(executeCreate);
        int ret = preparedStatement.executeUpdate();
        System.out.printf("executeCreate  ret is : %d\n", ret);
        preparedStatement.close();
        connection.close();
    }

    public void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%d ", array[i][j]);
            }
            System.out.println();
        }
    }

    //Java and JDBC store a two-dimensional array of integers as BLOB fields
    public void storeArray(int game_difficulty, String game_status, int[][] array) {

        System.out.println("storeArray");
        printArray(array);
        try {
            deleteArray();
            Connection connection = DriverManager.getConnection(url);

            // Serialized array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(array);
            byte[] arrayData = bos.toByteArray();

            //Store the serialized array in the database
            String sql = "insert into SUDU_GAME (sudu_difficulty, sudu_status, sudu_data) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //set up parameters
            preparedStatement.setInt(1, game_difficulty);
            preparedStatement.setString(2, game_status);
            preparedStatement.setBlob(3, new ByteArrayInputStream(arrayData));

            //Execute SQL query
            int count = preparedStatement.executeUpdate();//影响行数
            //result of handling
            System.out.println(count > 0);
            //close
            preparedStatement.close();
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    //Retrieves BLOB data and restores it to a two-dimensional array
    public int[][] retrieveArray() {
        System.out.println("retrieveArray");
        int[][] array = null;
        try {
            Connection connection = DriverManager.getConnection(url);

            // Retrieves the serialized array from the database
            String selectQuery = "SELECT sudu_difficulty, sudu_status, sudu_data FROM sudu_game";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            byte[] arrayBytes = null;
            Integer dif = null;
            String s = null;
            if (resultSet.next()) {
                arrayBytes = resultSet.getBytes("sudu_data");
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayBytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                array = (int[][]) objectInputStream.readObject();
            }

            //close
            resultSet.close();
            preparedStatement.close();
            connection.close();

            if (arrayBytes == null) {
                System.out.println("arrayBytes == null");
                return null;
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            array = (int[][]) objectInputStream.readObject();
            printArray(array);
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }

    //Delete database data
    public void deleteArray() {
        System.out.println("deleteArray");
        try {
            Connection connection = DriverManager.getConnection(url);
            String sql = "TRUNCATE TABLE SUDU_GAME";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //Execute SQL query
            int count = preparedStatement.executeUpdate();//影响行数
            //result of handling
            System.out.println(count == 0);
            //close
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("no table SUDU_GAME");
        }
    }
}



