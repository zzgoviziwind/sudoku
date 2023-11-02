package sudoku.Game;

import java.io.*;
import java.sql.*;


public class ArrayDatabase {

    private static final String url = "jdbc:derby:sudu";
    private static final String user = "";
    private static final String password = "";

    //Java和JDBC将一个二维整数数组存储为BLOB字段
    public void storeArray(int game_difficulty,String game_status,int[][] array) throws ClassNotFoundException {


        try {
            Connection connection = DriverManager.getConnection(url);

            // 序列化数组
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(array);
            byte[] arrayData = bos.toByteArray();

            //将序列化的数组存储在数据库中
            String sql = "insert into SUDU_GAME (sudu_difficulty, sudu_status, sudu_data) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //设置参数

            preparedStatement.setInt(1, game_difficulty);
            preparedStatement.setString(2, game_status);
            preparedStatement.setBlob(3, new ByteArrayInputStream(arrayData));

            //5.执行sql
            int count = preparedStatement.executeUpdate();//影响行数
            //处理结果
            System.out.println(count > 0);

            preparedStatement.close();
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //检索BLOB数据并将其还原为二维数组
    public int[][] retrieveArray() {
        int[][] array = null;
        try {
            Connection connection = DriverManager.getConnection(url);

            // 从数据库中检索序列化的数组
            String selectQuery = "SELECT sudu_data FROM sudu_game";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            byte[] arrayBytes = null;

            if (resultSet.next()) {
                arrayBytes = resultSet.getBytes("sudu_data");
            }

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

    public void deleteArray() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        String sql = "truncate table SUDU_GAME ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //5.执行sql
        int count = preparedStatement.executeUpdate();//影响行数
        //处理结果
        System.out.println(count == 0);

        preparedStatement.close();
        connection.close();

    }

    }





