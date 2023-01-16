package com.thg.accelerator23.connectn.ai.politicallyconnect;

import com.thehutgroup.accelerator.connectn.player.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class UrDaSellsAIvon extends Player {
    public UrDaSellsAIvon(Counter counter) {
        super(counter, UrDaSellsAIvon.class.getName());
    }

    public int validRandomMove(Board board, List<Integer> columnsToPickFrom){
        int randomMove;
        do {
            randomMove = columnsToPickFrom.get(ThreadLocalRandom.current().nextInt(columnsToPickFrom.size()));
        } while (board.hasCounterAtPosition(new Position(randomMove, board.getConfig().getHeight()-1)));
        return randomMove;
    }

    public int mostCentralValidMove(Board board, List<Integer> columnsToPickFrom){
        int middleColumnOnBoard = board.getConfig().getWidth()/2;
        int closest = 0;
        int distance = Math.abs(columnsToPickFrom.get(0)-middleColumnOnBoard);
        for (int number=1; number<columnsToPickFrom.size(); number++){
            int currentDistance = Math.abs(columnsToPickFrom.get(number)-middleColumnOnBoard);
            if (currentDistance <  distance){
                closest = number;
                distance = currentDistance;
            }
        }
        return columnsToPickFrom.get(closest);
    }



    @Override
    public int makeMove(Board board) {
        AIAnalyser slayIAnalyser = new AIAnalyser(board.getConfig());
        List<Integer> propaGoodMoves = slayIAnalyser.movesNotBelowGameEndingSpace(board, getCounter());
        try {
            Integer winningMove = slayIAnalyser.winningColumn(board, getCounter());
            if (winningMove != null) {
                return winningMove;
            }
            Integer blockingAWin = slayIAnalyser.winningColumn(board, getCounter().getOther());
            if (blockingAWin != null){
                return blockingAWin;
            }
            return validRandomMove(board, propaGoodMoves);
        } catch(Exception exception){
            return validRandomMove(board, propaGoodMoves);
        }

    }
}


