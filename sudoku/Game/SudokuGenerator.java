package sudoku.Game;

import java.util.Random;

public class SudokuGenerator {
    private int[][] Board;
    private int dod;
    private int removeCount;

    public SudokuGenerator(int dod) {
        this.dod =dod;
        Board = new int[9][9];
        generateSudoku();
    }

    // 生成数独谜题
    private void generateSudoku() {
        fillValues(0, 0);
        removeValues();
    }

    // 递归填充数独谜题
    private boolean fillValues(int row, int col) {
        if (row == 9) {
            return true;
        }
        if (col == 9) {
            return fillValues(row + 1, 0);
        }
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();

        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(9 - i);
            int num = nums[index];
            nums[index] = nums[8 - i];
            if (isValidMove(row, col, num)) {
                Board[row][col] = num;
                if (fillValues(row, col + 1)) {
                    return true;
                }
                Board[row][col] = 0;
            }
        }
        return false;
    }

    // 验证移动是否有效
    private boolean isValidMove(int row, int col, int num) {
        // 检查同一行和同一列是否有相同的数字
        for (int i = 0; i < 9; i++) {
            if (Board[row][i] == num || Board[i][col] == num) {
                return false;
            }
        }

        // 检查所在 3x3 子网格内是否有相同的数字
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (Board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // 随机移除一些数字以形成谜题
    private void removeValues() {
        Random random = new Random();
        // 要移除的数字数量，可以根据难度调整


        if(dod == 1){
            removeCount = 38;
        }
        if(dod == 2){
            removeCount = 45;
        }
        if(dod == 3){
            removeCount = 51;
        }


        while (removeCount > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (Board[row][col] != 0) {
                Board[row][col] = 0;
                removeCount--;
            }
        }
    }


    public void setBoard(int[][] board) {
        Board = board;
    }

    public int[][] getBoard() {
        return Board;
    }
}
