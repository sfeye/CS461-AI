import service.MCTS;
import utils.Board;
import utils.Position;

import java.util.Scanner;

public class Project2Application {

    public static void main(String[] args) {
        Board board = new Board();
        MCTS cpu = new MCTS ();
        board.printBoard();

        while (board.checkStatus() == -1) {

            //Player 1 play
            Scanner sc = new Scanner(System.in);
            System.out.println( "\n" + "Player 1 select a row to play...");
            int col = sc.nextInt();
            Position pos = new Position(board.getLowest(col), col);
            board.performMove(1, pos);
            board.printBoard();

            System.out.println("\n" + "_____________" + "\n");

            if(board.checkStatus() == -1) {

                //CPU play
                board = cpu.findNextMove(board, 2);
                board.printBoard();
            }
        }

        board.printStatus();
    }
}
