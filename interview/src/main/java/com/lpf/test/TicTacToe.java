package com.lpf.test;

import java.util.Arrays;

/**
 * TicTacToe
 *
 * @author lipengfei
 * @create 2025-03-28 15:15
 **/
public class TicTacToe {


    // 棋盘宽度
    private int width;

    // 当前的棋盘
    private char[][] grid;

    // 获胜人
//    private Player winner;

    public TicTacToe(int width) {
        this.width = width;
        this.grid = new char[width][width];
        for (int i = 0; i < width; i++) { // 棋盘初始化
            Arrays.fill(grid[i], '.');
        }
//        this.winner = Player.UNKNOWN;
    }


    public int move(int raw, int col, Player player) {
        if (grid[raw][col] != '.') {
            throw new RuntimeException("invalid step");
        }

//        char flag = player == Player.PLAYER_A ? 'x' : '0';
        grid[raw][col] = player.flag;

        return isPlayerWin(raw, col, player) ? player.no : Player.UNKNOWN.no;
    }


    private boolean isPlayerWin(int raw, int col, Player player) {
        char flag = player.flag;

        boolean interrupt = false;
        // 行
        for (int i = 0; i < grid[raw].length; i++) {
            if (grid[raw][i] != flag) { // 只要有一个不满足就
                interrupt = true;
                break;
            }
        }
        if (!interrupt) {
            return true;
        }

        // 列
        interrupt = false;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] != flag) {
                interrupt = true;
                break;
            }
        }
        if (!interrupt) {
            return true;
        }

        // 判断主对角线
        interrupt = false;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i] != flag) {
                interrupt = true;
                break;
            }
        }
        if (!interrupt) {
            return true;
        }

        // 判断副对角线
        interrupt = false;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][grid.length - i - 1] != flag) {   // 0,3   1,2
                interrupt = true;
                break;
            }
        }

        return !interrupt;
    }


    enum Player {
        UNKNOWN(0, '.'),
        PLAYER_A(1, 'x'),
        PLAYER_B(2, '0');

        int no;
        char flag;

        Player(int i, char c) {
            this.no = i;
            this.flag = c;
        }

        public int getNo() {
            return no;
        }

        public char getFlag() {
            return flag;
        }
    }


    public static void main(String[] args) {
        TicTacToe game1 = new TicTacToe(3);

        int move1 = game1.move(0, 0, Player.PLAYER_A);
        System.out.println("Game result=" + move1);
        int move2 = game1.move(0, 2, Player.PLAYER_B);
        System.out.println("Game result=" + move2);
        int move3 = game1.move(2, 2, Player.PLAYER_A);
        System.out.println("Game result=" + move3);
        int move4 = game1.move(1, 1, Player.PLAYER_B);
        System.out.println("Game result=" + move4);
        int move5 = game1.move(2, 0, Player.PLAYER_A);
        System.out.println("Game result=" + move5);
        int move6 = game1.move(1, 0, Player.PLAYER_B);
        System.out.println("Game result=" + move6);
        int move7 = game1.move(2, 1, Player.PLAYER_A);
        System.out.println("Game result=" + move7);

    }
}
