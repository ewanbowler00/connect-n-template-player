package com.thg.accelerator23.connectn.ai.politicallyconnect;

import com.thehutgroup.accelerator.connectn.player.*;
import com.thg.accelerator23.connectn.ai.politicallyconnect.analysis.BoardAnalyser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class AlanisFourconnect extends Player {
  public AlanisFourconnect(Counter counter) {
    //TODO: fill in your name here
    super(counter, AlanisFourconnect.class.getName());
  }

  public int validRandomMove(Board board, List<Integer> columnsToPickFrom) {
    int randomMove;
    do {
      randomMove = columnsToPickFrom.get(ThreadLocalRandom.current().nextInt(columnsToPickFrom.size()));
    } while (board.hasCounterAtPosition(new Position(randomMove, board.getConfig().getHeight() - 1)));
    return randomMove;
  }

  public int mostCentralValidMove(Board board, List<Integer> columnsToPickFrom) {
    int middleColumnOnBoard = board.getConfig().getWidth() / 2;
    int closest = 0;
    int distance = Math.abs(columnsToPickFrom.get(0) - middleColumnOnBoard);
    for (int number = 1; number < columnsToPickFrom.size(); number++) {
      int currentDistance = Math.abs(columnsToPickFrom.get(number) - middleColumnOnBoard);
      if (currentDistance < distance) {
        closest = number;
        distance = currentDistance;
      }
    }
    return columnsToPickFrom.get(closest);
  }

//  public int getLowestPlayable(Board board){
//    int highestCol = 0;
//    for (int column=0; column<board.getConfig().getWidth(); column++){
//      for (int ypos=0; ypos<board.getConfig().getHeight(); ypos++){
//        highestCol = ypos;
//        if (board.getCounterAtPosition(new Position(column, ypos)) == null){
//          highestCol = ypos;
//          break;
//        }
//      }
//    }
//  }


  @Override
  public int makeMove(Board board) {
    AIAnalyser slayIAnalyser = new AIAnalyser(board.getConfig());
    BoardAnalyser boardAnalyser = new BoardAnalyser(board.getConfig());

    List<Integer> propaGoodMoves = slayIAnalyser.movesNotBelowGameEndingSpace(board, getCounter());

    try {
      Integer winningMove = slayIAnalyser.winningColumn(board, getCounter());
      if (winningMove != null) {
        return winningMove;
      }
      Integer blockingAWin = slayIAnalyser.winningColumn(board, getCounter().getOther());
      if (blockingAWin != null) {
        return blockingAWin;
      }
      List<Integer> thirdTokenMoves = boardAnalyser.threeInARowPosition(board, getCounter());
      if (!thirdTokenMoves.isEmpty()) {
        System.out.println("Building 3 in a row" + thirdTokenMoves);
        return mostCentralValidMove(board, thirdTokenMoves);
      }
      List<Integer> thirdTokenBlocks = boardAnalyser.threeInARowPosition(board, getCounter().getOther());
      if (!thirdTokenMoves.isEmpty()) {
        System.out.println("Blocking 3 in a row" + thirdTokenBlocks);
        return mostCentralValidMove(board, thirdTokenBlocks);
      }
      return validRandomMove(board, propaGoodMoves);
    } catch (Exception exception) {
      return validRandomMove(board, propaGoodMoves);
    }
  }

//Older version
//  public int makeMove(Board board) {
//    AIAnalyser slayIAnalyser = new AIAnalyser(board.getConfig());
//    List<Integer> propaGoodMoves = slayIAnalyser.movesNotBelowGameEndingSpace(board, getCounter());
//    try {
//      Integer winningMove = slayIAnalyser.winningColumn(board, getCounter());
//      if (winningMove != null) {
//        return winningMove;
//      }
//      Integer blockingAWin = slayIAnalyser.winningColumn(board, getCounter().getOther());
//      if (blockingAWin != null) {
//        return blockingAWin;
//      }
//      List<Integer> builds3InARow = slayIAnalyser.getMovesThatExtendATwo(board, getCounter());
//      if (!builds3InARow.isEmpty()) {
//        System.out.println("building 3 in a row" + builds3InARow);
//        return mostCentralValidMove(board, builds3InARow);
//      }
//      List<Integer> blocksFor2InARow = slayIAnalyser.getMovesThatExtendATwo(board, getCounter().getOther());
//      if (!blocksFor2InARow.isEmpty()) {
//        System.out.println("blocking 2 in a row" + blocksFor2InARow);
//        return mostCentralValidMove(board, blocksFor2InARow);
//      }
//      return validRandomMove(board, propaGoodMoves);
//    } catch (Exception exception) {
//      return validRandomMove(board, propaGoodMoves);
//    }
//  }
}
