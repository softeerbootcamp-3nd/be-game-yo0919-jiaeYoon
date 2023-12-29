import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private static int[][] board;
    private static final int BOARD_SIZE = 16;
    private static int currentPlayer;

    public void start() {
        init();
        int winner;

        while (true) {
            setPosition();
            if (checkWin()) {
                winner = currentPlayer;
                break;
            }
            switchPlayer();
        }
        System.out.printf("player%d가 승리했습니다.", winner);
    }

    private void init() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 1;
    }

    private void setPosition() {
        int x, y;
        while (true) {
            try {
                int[] input = readInput();
                x = input[0];
                y = input[1];
                isValid(x, y);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        board[x][y] = currentPlayer;
        showBoard();
    }

    private int[] readInput() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("player%d님 차례입니다. 행과 열을 입력해주세요 : ", currentPlayer);
        String input = sc.nextLine();
        return Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private void isValid(int x, int y) {
        checkRange(x, y);
        checkDuplicate(x, y);
    }

    private void checkDuplicate(int x, int y) {
        if (board[x][y] != 0) {
            throw new IllegalArgumentException("이미 돌이 놓여져있습니다. 행과 열을 다시 입력해주세요");
        }
    }
    private void checkRange(int x, int y) {
        if (x < 1 || x > 15 || y < 0 || y > 15) {
            throw new IllegalArgumentException("입력 값이 범위를 벗어났습니다. 1-15 사이의 숫자를 입력해주세요.");
        }
    }

    private void showBoard() {
        int xAxis = 1;
        int yAxis = 1;
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if (c == 0 && r == 0) {
                    System.out.print("   ");
                    continue;
                }
                if (c == 0) {
                    System.out.printf("%2d ", yAxis++);
                    continue;
                }
                if (r == 0) {
                    System.out.printf("%2d ", xAxis++);
                    continue;
                }
                if (board[r][c] == 0) {
                    System.out.print(" · ");
                } else {
                    System.out.printf(" %d ", board[r][c]);
                }
            }
            System.out.println();
        }
    }

    private void switchPlayer() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }

    private boolean checkWin() {
        int player = currentPlayer;
        for (int i = 0; i < BOARD_SIZE-1; i++) {
            for (int j = 0; j < BOARD_SIZE-1 - 4; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player &&
                        board[i][j + 3] == player && board[i][j + 4] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i < BOARD_SIZE - 5; i++) {
            for (int j = 0; j < BOARD_SIZE-1; j++) {
                if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player &&
                        board[i + 3][j] == player && board[i + 4][j] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i < BOARD_SIZE - 5; i++) {
            for (int j = 0; j < BOARD_SIZE - 5; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player &&
                        board[i + 3][j + 3] == player && board[i + 4][j + 4] == player) {
                    return true;
                }
            }
        }

        for (int i = 0; i < BOARD_SIZE - 5; i++) {
            for (int j = 4; j < BOARD_SIZE-1; j++) {
                if (board[i][j] == player && board[i + 1][j - 1] == player && board[i + 2][j - 2] == player &&
                        board[i + 3][j - 3] == player && board[i + 4][j - 4] == player) {
                    return true;
                }
            }
        }
        return false;
    }
}