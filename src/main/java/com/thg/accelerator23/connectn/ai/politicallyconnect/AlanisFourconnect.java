package com.thg.accelerator23.connectn.ai.politicallyconnect;

import com.thehutgroup.accelerator.connectn.player.*;

import java.util.concurrent.ThreadLocalRandom;


public class AlanisFourconnect extends Player {
  public AlanisFourconnect(Counter counter) {
    //TODO: fill in your name here
    super(counter, AlanisFourconnect.class.getName());
  }

  @Override
  public int makeMove(Board board) {
//    for (int i = 0; i < board.getConfig().getWidth(); i++) {
    if (!board.hasCounterAtPosition(new Position(5, 0))) {
      return (int) Math.floor((board.getConfig().getWidth() / 2));
    } else {
      return winOnNext(board);
//      System.out.println(board.getCounterAtPosition(new Position(5, 0)).getClass().getSimpleName());
//      return 4;
    }
  }

  public int winOnNext(Board board) {
    final int boardHeight = board.getConfig().getHeight();
    final int boardWidth = board.getConfig().getWidth();
    int col;
    int row;

    for (col = 0; col < boardWidth; col++) {
      for (row = 0; row < boardHeight; row++) {
        System.out.println("col:" + col);
        System.out.println("row:" + row);
        System.out.println("COUNTER:" + board.getCounterAtPosition(new Position(col, row)));
        if (board.getCounterAtPosition(new Position(col, row)) == null)
          continue;

        if (row + 3 < boardHeight) {
          System.out.println("A HI");
          if (board.getCounterAtPosition(new Position(col, row)) == null || board.getCounterAtPosition(new Position(col, row + 1)) == null || board.getCounterAtPosition(new Position(col, row + 2)) == null) {
            continue;
          } else {
            System.out.println("A HII");
            if (board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col, row + 1)))
                    && board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col, row + 2)))) {
              System.out.println("A) FOUND 3");
              if (!board.hasCounterAtPosition(new Position(col, row + 3))) {
                System.out.println("col OUTPUT:" + col);
                return col;
              }
            }
          }
        }
        if (col + 3 < boardWidth) {
          System.out.println("B HI");
          if (board.getCounterAtPosition(new Position(col, row)) == null || board.getCounterAtPosition(new Position(col+1, row)) == null || board.getCounterAtPosition(new Position(col+2, row)) == null) {
            continue;
          } else {
            System.out.println("B HII");
            if (board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col+1, row)))
                    && board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col+2, row)))) {
              System.out.println("B) FOUND 3");
              if (!board.hasCounterAtPosition(new Position(col+3, row))) {
                System.out.println("col OUTPUT:" + col+3);
                return col+3;
              }
            }
          }
        }
        if (row + 3 < boardHeight) {
          if (board.getCounterAtPosition(new Position(col, row )) == null || board.getCounterAtPosition(new Position(col+1, row + 1)) == null || board.getCounterAtPosition(new Position(col+2, row + 2)) == null) {
            continue;
          } else {
            if (board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col+1, row+1)))
                    && board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col+2, row+2)))) {
              System.out.println("C) FOUND 3");
              if (!board.hasCounterAtPosition(new Position(col+3, row))) {
                System.out.println("col OUTPUT:" + col+3);
                return col+3;
              }
            }
          }
        }
        if (row - 3 >= 0) {
          if (board.getCounterAtPosition(new Position(col+1, row - 1)) == null || board.getCounterAtPosition(new Position(col+2, row - 2)) == null || board.getCounterAtPosition(new Position(col+2, row - 3)) == null) {
            continue;
          } else {
            if (board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col+1, row-1)))
                    && board.getCounterAtPosition(new Position(col, row)).equals(board.getCounterAtPosition(new Position(col+2, row-2)))) {
              System.out.println("D) FOUND 3");
              if (!board.hasCounterAtPosition(new Position(col+3, row))) {
                System.out.println("col OUTPUT:" + col+3);
                return col+3;
              }
            }
          }
        }
      }

      //TODO: some crazy analysis
      //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it

    }
    System.out.println("NOT FOUND 3");
    return ThreadLocalRandom.current().nextInt(0, 9);
  }
}

//  public static int checkWin(int[][] board) {
//    final int HEIGHT = board.length;
//    final int WIDTH = board[0].length;
//    final int EMPTY_SLOT = 0;
//    for (int r = 0; r < HEIGHT; r++) { // iterate cols, bottom to top
//      for (int c = 0; c < WIDTH; c++) { // iterate rowumns, left to right
//        int player = board[r][c];
//        if (player == EMPTY_SLOT)
//          continue; // don't check empty slots
//
//        if (c + 3 < WIDTH &&
//                player == board[r][c+1] && // look right
//                player == board[r][c+2] &&
//                player == board[r][c+3])
//          return player;
//        if (r + 3 < HEIGHT) {
//          if (player == board[r+1][c] && // look up
//                  player == board[r+2][c] &&
//                  player == board[r+3][c])
//            return player;
//          if (c + 3 < WIDTH &&
//                  player == board[r+1][c+1] && // look up & right
//                  player == board[r+2][c+2] &&
//                  player == board[r+3][c+3])
//            return player;
//          if (c - 3 >= 0 &&
//                  player == board[r+1][c-1] && // look up & left
//                  player == board[r+2][c-2] &&
//                  player == board[r+3][c-3])
//            return player;
//        }
//      }
//    }
//    return EMPTY_SLOT; // no winner found
//  }