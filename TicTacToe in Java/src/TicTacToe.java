import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    // 3 x 3 matrix, sperated by | to distinguish rows and columns
    private static char[][] board = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' },
            { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

    static String player = "player";
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(final String[] args) {
        // Get User input
        final Scanner scan = new Scanner(System.in);
        final Random rand = new Random();

        String res = " ";

        // Starting new game with blank board.
        printBoard();

        while (true) {

            System.out.println("Enter you placement (1-9): ");

            // Position of next moves
            int cpuPos = rand.nextInt(9) + 1;
            int pos = scan.nextInt();

            // User Move
            while (!validParam(pos)) {
                System.out.println("Invalid placement! Enter a different placement");
                printBoard();
                pos = scan.nextInt();
            }
            makeMove(pos, "player");

            // Check for end game
            res = checkWinner();
            if (res != "Keep Playing !") {
                printBoard();
                System.out.println(res);
                break;
            }

            // CPU Move
            while (!validParam(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            makeMove(cpuPos, "cpu");

            // Check for end game
            res = checkWinner();
            if (res != "Keep Playing !") {
                printBoard();
                System.out.println(res);
                break;
            }

            // Update board
            printBoard();
        }
        scan.close();
    }

    public static boolean validParam(int i) {
        return !playerPositions.contains(i) && !cpuPositions.contains(i) && i < 10 && i > 0;
    }

    public static void printBoard() {
        for (final char[] row : board) {
            for (final char c : row) {
                // Prints each row of gameboard
                System.out.print(c);
            }
            // Starts a new line
            System.out.println();
        }
    }

    public static void makeMove(final int pos, final String user) {
        char symbol = ' ';

        if (user.equals(player)) {
            symbol = 'X';
            playerPositions.add(pos);
        } else {
            // CPU's turn
            symbol = 'O';
            cpuPositions.add(pos);
        }

        switch (pos) {
        case 1:
            board[0][0] = symbol;
            break;
        case 2:
            board[0][2] = symbol;
            break;
        case 3:
            board[0][4] = symbol;
            break;
        case 4:
            board[2][0] = symbol;
            break;
        case 5:
            board[2][2] = symbol;
            break;
        case 6:
            board[2][4] = symbol;
            break;
        case 7:
            board[4][0] = symbol;
            break;
        case 8:
            board[4][2] = symbol;
            break;
        case 9:
            board[4][4] = symbol;
            break;
        default:
            break;
        }
    }

    public static String checkWinner() {
        final List<List> winningCombos = new ArrayList<List>();

        final List row1 = Arrays.asList(1, 2, 3);
        final List row2 = Arrays.asList(4, 5, 6);
        final List row3 = Arrays.asList(7, 8.9);

        final List col1 = Arrays.asList(1, 2, 3);
        final List col2 = Arrays.asList(4, 5, 6);
        final List col3 = Arrays.asList(7, 8, 9);

        final List diagonal1 = Arrays.asList(1, 5, 9);
        final List diagonal2 = Arrays.asList(3, 5, 7);

        winningCombos.add(row1);
        winningCombos.add(row2);
        winningCombos.add(row3);
        winningCombos.add(col1);
        winningCombos.add(col2);
        winningCombos.add(col3);
        winningCombos.add(diagonal1);
        winningCombos.add(diagonal2);

        for (final List l : winningCombos) {
            if (playerPositions.containsAll(l)) {
                return "You Win !";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU Wins !";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {

                return "It's a Tie !";
            }
        }
        return "Keep Playing !";
    }
}