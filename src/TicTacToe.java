import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char board[][];

    public TicTacToe() {

        board = new char[3][3];
    }

    void initBoard() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println(" ");
            System.out.println("-------------");
        }
    }

     static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            board[row][col] = mark;
        } else {
            System.out.println("Index is out of bond");
        }
    }

    static boolean checkColWin() {

        for (int j = 0; j <=2; j++) {
            if (board[0][j] !=  ' ' && board[0][j] == board[1][j] &&
                    board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        //int i = 0;
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] !=  ' ' &&  board[i][0] == board[i][1]  &&
                    board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDigWin() {
        //int i = 0;
        for (int i = 0; i <= 2; i++) {
            if (board[0][0] !=' ' && board[0][0] == board[1][1]  &&
                    board[1][1] == board[2][2]
            ||  board[0][2] !=' ' &&  board[0][2] == board[1][1]  &&
                    board[1][1] == board[2][0]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDraw() {
        //int i = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
            if (board[i][j] ==' ') {
                return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        //System.out.printf("Hello and welcome!");
        TicTacToe  t  = new TicTacToe();
        t.initBoard();

        HumanPlayer  p1 =  new HumanPlayer("Bob",  'X');
        AIPlayer  p2 =  new AIPlayer("TAI",  'O');

        Player cp;
        cp = p1;

        while(true) {
            System.out.println(cp.name + "s turn");
            cp.makeMove();
            TicTacToe.displayBoard();

            if (TicTacToe.checkDigWin() || TicTacToe.checkRowWin() || TicTacToe.checkColWin()) {
                System.out.println(cp.name + " has won");
                break;
            }
            else if(TicTacToe.checkDraw()){
                System.out.println("Game is draw");
                break;
            }else {
                if (cp == p1) {
                    cp = p2;
                } else {
                    cp = p1;
                }
            }
        }

    }
}
 abstract class Player{
    String name;
    char mark;
    abstract void makeMove();
     boolean isVaildMove(int row, int col){
         if(row>=0 && row<=2 && col>=0 && col<=2){
             if(TicTacToe.board[row][col] == ' '){
                 return true;
             }
         }
         return false;
     }
}
class AIPlayer extends Player{

    AIPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }
    void makeMove() {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do {
            Random rv = new Random();
            row = rv.nextInt(3);
            col = rv.nextInt(3);
        }
        while (!isVaildMove(row, col));
        TicTacToe.placeMark(row, col,mark);
    }
}
class HumanPlayer extends Player{

    HumanPlayer(String name, char mark){
       this.name = name;
       this.mark = mark;
    }
    void makeMove() {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter row & col");
            row = sc.nextInt();
            col = sc.nextInt();
        }
        while (!isVaildMove(row, col));
        TicTacToe.placeMark(row, col,mark);

    }
}
