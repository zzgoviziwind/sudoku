package sudoku.Game;

import java.sql.SQLException;

public class SudokuGame {


    // 在用户输入数字后更新游戏状态
    public void updateBoard(int row, int col, int value, int[][] board) {
        if (row >= 0 && row < 9 && col >= 0 && col < 9 && value >= 0 && value <= 9) {
            board[row][col] = value;

        }
    }

//    Java和JDBC将一个二维整数数组存储为BLOB字段
    public void storeArray(int game_difficulty,String game_status,int[][] array) throws ClassNotFoundException {
        ArrayDatabase arrayDatabase = new ArrayDatabase();
        arrayDatabase.storeArray(game_difficulty,game_status,array);
    }

    //检索BLOB数据并将其还原为二维数组
    public int[][] retrieveArray(){
        ArrayDatabase arrayDatabase = new ArrayDatabase();
        return arrayDatabase.retrieveArray();
    }
    //生成简单关卡
    public int[][] GenerateSimpleLevels(){
        SudokuGenerator sudokuGenerator = new SudokuGenerator(1);
        return sudokuGenerator.getBoard();
    }
    //生成中等关卡
    public int[][] GenerateMediumLevels(){
        SudokuGenerator sudokuGenerator = new SudokuGenerator(2);
        return sudokuGenerator.getBoard();
    }
    //生成困难关卡
    public int[][] GenerateDifficultLevels(){
        SudokuGenerator sudokuGenerator = new SudokuGenerator(3);
        return sudokuGenerator.getBoard();
    }
//    删除记录
    public void deleteArray() throws SQLException {
        ArrayDatabase arrayDatabase = new ArrayDatabase();
        arrayDatabase.deleteArray();
    }


    //判断用户是否完成

    public void isCompleted(int[][] board){
        boolean flag = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j]==0){
                    flag = false;
                }
            }
        }

        if (flag){
            System.out.println("completed");
        }else{
            System.out.println("uncompleted");
        }
    }



    // 检查用户数独游戏是否正确
    public String isGameCorrect(int[][] board) {
        SudokuSolved sudokuSolved = new SudokuSolved();
        if (sudokuSolved.isSudokuSolved(board)){
            return "       win!";
        }else {
            return "       lose!";
        }
    }


    //打印
    public void print(int[][] board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}

