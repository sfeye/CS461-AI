import service.MCTS;
import utils.Board;
import utils.Position;

import java.util.Scanner;

// This is a modified version of Baeldung's version of MCTS tic-tac-toe
// https://github.com/eugenp/tutorials/tree/master/algorithms-searching/src/main/java/com/baeldung/algorithms/mcts

public class Project2Application {

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();
        MCTS cpu = new MCTS ();

        System.out.println("Please enter the game mode: 0 for cpu vs cpu, 1 for human vs cpu...");
        Scanner isc = new Scanner(System.in);
        int gameType = isc.nextInt();

        System.out.println("Please input the level of the cpu (1-10)... The higher the level the longer it takes to 'think'.");
        Scanner dsc = new Scanner(System.in);
        int level = dsc.nextInt();

        cpu.setLevel(level);

        board.printBoard();

        if(gameType == 0) {
            while (board.checkStatus() == -1) {

                //CPU1 play
                board = cpu.findNextMove(board, 1);
                board.printBoard();
                System.out.println("\n" + "_____________" + "\n");

                if (board.checkStatus() == -1) {

                    //CPU2 play
                    board = cpu.findNextMove(board, 2);
                    board.printBoard();
                    System.out.println("\n" + "_____________" + "\n");
                }
            }
        }

        if (gameType == 1) {
            while (board.checkStatus() == -1) {

                //Player 1 play
                Scanner sc = new Scanner(System.in);
                System.out.println("\n" + "Player 1 select a row to play...");
                int col = sc.nextInt();
                Position pos = new Position(board.getLowest(col), col);

                board.performMove(1, pos);

                board.printBoard();

                System.out.println("\n" + "_____________" + "\n");

                if (board.checkStatus() == -1) {

                    //CPU play
                    board = cpu.findNextMove(board, 2);
                    board.printBoard();
                }
            }
        }

        board.printStatus();
    }
}
