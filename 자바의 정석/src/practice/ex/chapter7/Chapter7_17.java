package practice.ex.chapter7;

public class Chapter7_17 {
    public static void main(String[] args) {

    }
}

abstract class Unit {
    int x, y; // 현재 위치

    abstract void move(int x, int y);

    void stop() {

    }
}

class Marine extends Unit{
    void stimPack() {

    }

    @Override
    void move(int x, int y) {

    }
}

class Tank extends Unit{
    void changeMode() {

    }

    @Override
    void move(int x, int y) {

    }
}

class DropShip extends Unit{
    void load() {

    }

    void unload() {

    }

    @Override
    void move(int x, int y) {

    }
}