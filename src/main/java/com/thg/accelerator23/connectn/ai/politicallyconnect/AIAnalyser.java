package com.thg.accelerator23.connectn.ai.politicallyconnect;

import com.thehutgroup.accelerator.connectn.player.*;
import com.thg.accelerator23.connectn.ai.politicallyconnect.analysis.BoardAnalyser;

import java.util.ArrayList;
import java.util.List;

public class AIAnalyser {
    BoardAnalyser boardAnalyser;
    BoardAnalyser boardAnalyserTwo;
    GameConfig twoInARow = new GameConfig(10, 8, 3);

    public AIAnalyser(GameConfig gameConfig) {
        this.boardAnalyser = new BoardAnalyser(gameConfig);
        this.boardAnalyserTwo = new BoardAnalyser(twoInARow);
    }

    private boolean isWin(int column, Board board, Counter counter, boolean findTwoInARow) {
        if (findTwoInARow == false) {
            try {
                Board isItGonnaBeTheWinningBoardOOOOOO = new Board(board, column, counter);
                return boardAnalyser.calculateGameState(isItGonnaBeTheWinningBoardOOOOOO).isWin();
            } catch (InvalidMoveException exception) {
                return false;
            }
        } else {
            try {
                Board isItGonnaBeTheWinningBoardOOOOOO = new Board(board, column, counter);
                return boardAnalyserTwo.calculateGameState(isItGonnaBeTheWinningBoardOOOOOO).isWin();
            } catch (InvalidMoveException exception) {
                return false;
            }
        }
    }

    public Integer winningColumn(Board board, Counter counter) {
        for (int column = 0; column < board.getConfig().getWidth(); column++) {
            if (isWin(column, board, counter, false)) {
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
                if (!isWin(column, copyOfBoard, counter, false) && !isWin(column, copyOfBoard, counter.getOther(), false)) {
                    propaDecentMoves.add(column);
                } else if (!isWin(column, copyOfBoard, counter, true) && !isWin(column, copyOfBoard, counter.getOther(), true)) {
                    propaDecentMoves.add(column);
                }
            } catch (InvalidMoveException exception) {
                continue;
            }
        }
        return propaDecentMoves;
    }
}