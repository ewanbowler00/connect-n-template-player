package com.thg.accelerator23.connectn.ai.politicallyconnect;

import com.thehutgroup.accelerator.connectn.player.*;
import com.thg.accelerator23.connectn.ai.politicallyconnect.analysis.BoardAnalyser;

import java.util.ArrayList;
import java.util.List;

public class AIAnalyser {
    BoardAnalyser boardAnalyser;
    GameConfig gameConfig;

    public AIAnalyser(GameConfig gameConfig) {
        this.boardAnalyser = new BoardAnalyser(gameConfig);
        this.gameConfig = gameConfig;
    }

    private boolean isWin(int column, Board board, Counter counter) {
        try {
            Board isItGonnaBeTheWinningBoardOOOOOO = new Board(board, column, counter);
            return boardAnalyser.calculateGameState(isItGonnaBeTheWinningBoardOOOOOO).isWin();
        } catch (InvalidMoveException exception) {
            return false;
        }
    }

    public Integer winningColumn(Board board, Counter counter) {
        for (int column = 0; column < board.getConfig().getWidth(); column++) {
            if (isWin(column, board, counter)) {
                return column;
            }
        }
        return null;
    }

    public boolean isColumnFull(Board board, int column) {
        return board.getCounterAtPosition(new Position(column, board.getConfig().getHeight() - 1)) != null;
    }

    public List<Integer> movesNotBelowGameEndingSpace(Board board, Counter counter) {
        List<Integer> propaDecentMoves = new ArrayList<>();
        for (int column = 0; column < board.getConfig().getWidth(); column++) {
            try {
                Board copyOfBoard = new Board(board, column, counter);
                if (!isWin(column, copyOfBoard, counter) && !isWin(column, copyOfBoard, counter.getOther())) {
                    propaDecentMoves.add(column);
                }
            } catch (InvalidMoveException exception) {
                continue;
            }
        }
        return propaDecentMoves;
    }

    private Counter[][] getCounterPlacements(Board board) {
        Counter[][] counters = new Counter[board.getConfig().getWidth()][board.getConfig().getHeight()];
        for (int x=0; x<board.getConfig().getWidth();x++) {
            for (int y=0; y<board.getConfig().getHeight();y++) {
                counters[x][y] = board.getCounterAtPosition(new Position(x, y));
            }
        }
        return counters;
    }

    public List<Integer> getMovesThatExtendATwo(Board board, Counter counter, List<Integer> listOfCols) {
        List<Integer> movesThatExtendATwo = new ArrayList<>();
        for (Integer column:listOfCols){
            try {
                Board playedMoveBoard = new Board(board, column, counter);
                if (winningColumn(playedMoveBoard, counter) != null){
                    movesThatExtendATwo.add(column);
                }
            } catch (InvalidMoveException exception) {
                continue;
            }
        }
//        Board newConfigBoard = new Board(getCounterPlacements(board), new GameConfig(board.getConfig().getWidth(), board.getConfig().getHeight(), 3));
//        for (int column = 0; column<newConfigBoard.getConfig().getWidth(); column++) {
//            if (isWin(column, newConfigBoard, counter)) {
//                movesThatExtendATwo.add(column);
//            }
//        }
        return movesThatExtendATwo;
    }
}