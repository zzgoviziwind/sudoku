package sudoku.UI.sudokuAlgorithm;

import java.sql.SQLException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      SudokuGame sudokuGame = new SudokuGame();
//      int[][] board = sudokuGame.GenerateSimpleLevels();
//      sudokuGame.print(board);
//    sudokuGame.storeArray(1,"未完成",board);
        int[][]board = sudokuGame.retrieveArray();
        sudokuGame.print(board);
//      sudokuGame.deleteArray();
//        Scanner in = new Scanner(System.in);
//
//        while (!in.next().equals("4")) {
//            System.out.println("1.生成简单难度");
//            System.out.println("2.查看已生成的难度");
//            System.out.println("3.删除已生成的难度");
//            System.out.println("4.退出");
//            switch (in.next()) {
//                case "1":
//                    int[][] board = sudokuGame.GenerateSimpleLevels();
//                    sudokuGame.print(board);
//                    sudokuGame.storeArray(1,"未完成",board);
//                    break;
//                case "2":
//                    int[][]board1 = sudokuGame.retrieveArray();
//                    sudokuGame.print(board1);
//                    break;
//                case "3":
//                    sudokuGame.deleteArray();
//                    break;
//                default:
//                    break;
//            }
//        }

    }
}
