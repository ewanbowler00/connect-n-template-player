package com.thg.accelerator23.connectn.ai.politicallyconnect;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package com.thehutgroup.accelerator.connectn.player;

import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.GameConfig;
import com.thehutgroup.accelerator.connectn.player.InvalidMoveException;
import com.thehutgroup.accelerator.connectn.player.Position;

import java.util.Arrays;

public class Sudoboard {
    private Counter[][] counterPlacements;
    private GameConfig config;

    public Sudoboard(Counter[][] counterPlacements, GameConfig config) {
        this.counterPlacements = counterPlacements;
        this.config = config;
    }

    public Sudoboard(Sudoboard sudoboard, int x, Counter counter) throws InvalidMoveException {
//        this.counterPlacements = (Counter[][])this.deepCopy(sudoboard.counterPlacements);
        this.config = sudoboard.getConfig();
        this.placeCounterAtPosition(counter, x);
    }

    public Sudoboard(GameConfig config) {
        this.config = config;
        this.counterPlacements = new Counter[config.getWidth()][config.getHeight()];
    }

    public GameConfig getConfig() {
        return this.config;
    }

    private void placeCounterAtPosition(Counter counter, int x) throws InvalidMoveException {
        if (!this.isWithinBoard(new Position(x, 0))) {
            throw new InvalidMoveException("Outside the bounds of the board");
        } else {
            Position position = new Position(x, this.getMinVacantY(x));
            if (this.hasCounterAtPosition(position)) {
                throw new InvalidMoveException("Column is full");
            } else {
                this.counterPlacements[position.getX()][this.getMinVacantY(position.getX())] = counter;
            }
        }
    }

    protected Counter[][] getCounterPlacements() {
        return this.counterPlacements;
    }

    private int getMinVacantY(int x) {
        for(int i = this.config.getHeight() - 1; i >= 0; --i) {
            if (i == 0 || this.counterPlacements[x][i - 1] != null) {
                return i;
            }
        }

        throw new RuntimeException("no y is vacant");
    }

    public Counter getCounterAtPosition(Position position) {
        return this.isWithinBoard(position) ? this.counterPlacements[position.getX()][position.getY()] : null;
    }

    public boolean hasCounterAtPosition(Position position) {
        return this.isWithinBoard(position) && this.counterPlacements[position.getX()][position.getY()] != null;
    }

    public boolean isWithinBoard(Position position) {
        return position.getX() >= 0 && position.getX() < this.config.getWidth() && position.getY() >= 0 && position.getY() < this.config.getHeight();
    }

//    private <T> T[][] deepCopy(T[][] matrix) {
//        return (T[][])Arrays.stream(matrix).map((el) -> {
//            return (T[])el.clone();
//        }).toArray(($) -> {
//            return (T[][])matrix.clone();
//        });
//    }
}

