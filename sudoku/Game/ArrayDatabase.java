package sudoku.Game;

import java.io.*;
import java.sql.*;

public class ArrayDatabase {

    //Connect to the database
    private static final String url = "jdbc:derby:sudu";
    private static final String user = "";
    private static final String password = "";

    //Java and JDBC store a two-dimensional array of integers as BLOB fields
    public void storeArray(int game_difficulty,String game_status,int[][] array) throws ClassNotFoundException {


        try {
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
        int[][] array = null;
        try {
            Connection connection = DriverManager.getConnection(url);

            // Retrieves the serialized array from the database
            String selectQuery = "SELECT sudu_data FROM sudu_game";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            byte[] arrayBytes = null;

            if (resultSet.next()) {
                arrayBytes = resultSet.getBytes("sudu_data");
            }



            //close
            resultSet.close();
            preparedStatement.close();
            connection.close();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            array = (int[][]) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }

    //Delete database data
    public void deleteArray() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        String sql = "truncate table SUDU_GAME ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //Execute SQL query
        int count = preparedStatement.executeUpdate();//影响行数
        //result of handling
        System.out.println(count == 0);
        //close
        preparedStatement.close();
        connection.close();

    }

    }





