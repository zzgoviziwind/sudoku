package sudokuAlgorithm;

public class SudokuGame {
    private int[][] board;  // 数独棋盘
    private boolean[][] isFilled;  // 记录哪些单元格已填写
//    int game_difficulty;
//    String game_status;
//    int[][] array;

    // 获取数独棋盘的状态
    public int[][] getBoard() {
        return board;
    }

    // 在用户输入数字后更新游戏状态
    public void updateBoard(int row, int col, int value) {
        if (row >= 0 && row < 9 && col >= 0 && col < 9 && value >= 0 && value <= 9) {
            board[row][col] = value;
            isFilled[row][col] = true;
        }
    }

    // 获取已填写的状态
    public boolean[][] getIsFilled() {
        return isFilled;
    }

    //Java和JDBC将一个二维整数数组存储为BLOB字段
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

    //判断用户是否完成
    public boolean isCompleted0(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
    public String isCompleted(){
        if (isCompleted0()){
            return "已完成";
        }else{
            return "未完成";
        }
    }


    // 检查用户数独游戏是否正确
    public void isGameCorrect() {
        SudokuSolved sudokuSolved = new SudokuSolved();
        if (sudokuSolved.isSudokuSolved(board)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
    }


    public static void main(String[] args) {
        SudokuGame sudokuGame = new SudokuGame();
        int[][] board = sudokuGame.GenerateSimpleLevels();


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }




        //打印
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }

}
