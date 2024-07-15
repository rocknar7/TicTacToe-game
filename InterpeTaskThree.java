import java.util.Scanner;

public class InterpeTaskThree  {
    private static char[][] board;
    private static char currentPlayer;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            board = new char[3][3];
            currentPlayer = 'X';
            initializeBoard();
            playGame();
        } while (playAgain());
    }

    // Initialize the board with position numbers
    public static void initializeBoard() {
        char pos = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = pos++;
            }
        }
    }

    // Print the current board
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                printCell(board[i][j]);
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Print a cell with color
    public static void printCell(char cell) {
        if (cell == 'X') {
            System.out.print("\033[1;31m" + cell + "\033[0m | ");  // Red color for X
        } else if (cell == 'O') {
            System.out.print("\033[1;34m" + cell + "\033[0m | ");  // Blue color for O
        } else {
            System.out.print(cell + " | ");
        }
    }

    // Check if the board is full
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for a win
    public static boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Check the rows for a win
    public static boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    // Check the columns for a win
    public static boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    // Check the diagonals for a win
    public static boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2])) || (checkRowCol(board[0][2], board[1][1], board[2][0])));
    }

    // Check if all three values are the same (and not empty) indicating a win
    public static boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 == c2) && (c2 == c3) && (c1 != 'X' || c1 != 'O'));
    }

    // Change player marks back and forth
    public static void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Place a mark at the cell specified by position
    public static boolean placeMark(int pos) {
        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;
        if (board[row][col] != 'X' && board[row][col] != 'O') {
            board[row][col] = currentPlayer;
            return true;
        } else {
            System.out.println("This position is already taken. Try again.");
            return false;
        }
    }

    // Main game loop
    public static void playGame() {
        while (true) {
            printBoard();
            int pos;
            do {
                System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
                pos = scanner.nextInt();
            } while (pos < 1 || pos > 9 || !placeMark(pos));
            if (checkForWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw!");
                break;
            }
            changePlayer();
        }
    }

    // Ask if the players want to play again
    public static boolean playAgain() {
        System.out.println("Do you want to play again? (yes/no): ");
        String response = scanner.next();
        return response.equalsIgnoreCase("yes");
    }
}
