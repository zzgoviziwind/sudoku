package sudoku.UI.sudokuAlgorithm;

import java.io.*;
import java.sql.*;



public class ArrayDatabase {


    private static final String url = "jdbc:mysql://localhost:3306/sudoku";
    private static final String user = "root";
    private static final String password = "123456";

    //Java和JDBC将一个二维整数数组存储为BLOB字段
    public void storeArray(int game_difficulty,String game_status,int[][] array) throws ClassNotFoundException {


        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            // 序列化数组
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(array);
            byte[] arrayData = bos.toByteArray();

            //将序列化的数组存储在数据库中
            String sql = "insert into sudoku_game (game_difficulty, game_status, game_data) values (?,?,?)";
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
            Connection connection = DriverManager.getConnection(url, user, password);

            // 从数据库中检索序列化的数组
            String selectQuery = "SELECT game_data FROM sudoku_game ";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("game_data");
                ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
                array = (int[][]) ois.readObject();
            }

            connection.close();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return array;
    }
    }
