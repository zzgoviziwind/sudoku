package sudoku.Game;

public class SudokuSolved {
    public boolean isSudokuSolved(int[][] board) {
        // Check that each row contains all numbers from 1 to 9
        for(int row = 0; row < 9; row++) {
            boolean[] isPresent = new boolean[9];
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];
                if (num < 1 || num > 9 || isPresent[num - 1]) {
                    return false;
                }
                isPresent[num - 1] = true;
            }
        }


        // Check that each column contains all numbers from 1 to 9
        for (int col = 0; col < 9; col++) {
            boolean[] isPresent = new boolean[9];
            for (int row = 0; row < 9; row++) {
                int num = board[row][col];
                if (num < 1 || num > 9 || isPresent[num - 1]) {
                    return false;
                }
                isPresent[num - 1] = true;
            }
        }

        // Check that each 3x3 subgrid contains all numbers 1-9
        for (int rowStart = 0; rowStart < 9; rowStart += 3) {
            for (int colStart = 0; colStart < 9; colStart += 3) {
                boolean[] isPresent = new boolean[9];
                for (int row = rowStart; row < rowStart + 3; row++) {
                    for (int col = colStart; col < colStart + 3; col++) {
                        int num = board[row][col];
                        if (num < 1 || num > 9 || isPresent[num - 1]) {
                            return false;
                        }
                        isPresent[num - 1] = true;
                    }
                }
            }
        }

        return true;
    }
}
