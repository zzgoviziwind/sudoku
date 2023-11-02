package sudokuAlgorithm;

public class SudokuSolved {
    public boolean isSudokuSolved(int[][] board) {
        // 检查每行是否包含1-9的所有数字
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

        // 检查每列是否包含1-9的所有数字
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

        // 检查每个3x3子网格是否包含1-9的所有数字
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
