package sudoku.Game;

import java.sql.SQLException;

public class SudokuGame {


    //Update the game status after the user enters the number
    public void updateBoard(int row, int col, int value, int[][] board) {
        if (row >= 0 && row < 9 && col >= 0 && col < 9 && value >= 0 && value <= 9) {
            board[row][col] = value;

        }
    }

    //Java and JDBC store a two-dimensional array of integers as BLOB fields
    public void storeArray(int game_difficulty,String game_status,int[][] array) throws ClassNotFoundException {
        ArrayDatabase arrayDatabase = new ArrayDatabase();
        arrayDatabase.storeArray(game_difficulty,game_status,array);
    }

    //Retrieves BLOB data and restores it to a two-dimensional array
    public int[][] retrieveArray(){
        ArrayDatabase arrayDatabase = new ArrayDatabase();
        return arrayDatabase.retrieveArray();
    }
    //Generate simple levels
    public int[][] GenerateSimpleLevels(){
        SudokuGenerator sudokuGenerator = new SudokuGenerator(1);
        return sudokuGenerator.getBoard();
    }
    //Generate medium level
    public int[][] GenerateMediumLevels(){
        SudokuGenerator sudokuGenerator = new SudokuGenerator(2);
        return sudokuGenerator.getBoard();
    }
    //Generate hard levels
    public int[][] GenerateDifficultLevels(){
        SudokuGenerator sudokuGenerator = new SudokuGenerator(3);
        return sudokuGenerator.getBoard();
    }
    //deletion record
    public void deleteArray() throws SQLException {
        ArrayDatabase arrayDatabase = new ArrayDatabase();
        arrayDatabase.deleteArray();
    }


    //Determine whether the user is finished
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
            System.out.println("no");
        }
    }



    //Check whether the user only game is correct
    public String isGameCorrect(int[][] board) {
        SudokuSolved sudokuSolved = new SudokuSolved();
        if (sudokuSolved.isSudokuSolved(board)){
            return "       win!";
        }else {
            return "       lose!";
        }
    }


    //print
    public void print(int[][] board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}

