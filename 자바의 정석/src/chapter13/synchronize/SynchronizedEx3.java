package chapter13.synchronize;

import java.util.ArrayList;

public class SynchronizedEx3 {
    public static void main(String[] args) throws InterruptedException {
        Table table = new Table();

        new Thread(new Cook(table), "COOK").start();
        new Thread(new Customer(table, "donut"), "CUSTOMER1").start();
        new Thread(new Customer(table, "burger"), "CUSTOMER2").start();

        Thread.sleep(100);
        System.exit(0);
    }
}

class Customer implements Runnable {
    private Table table;
    private String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
            // catch
            String name = Thread.currentThread().getName();
            if (eatFood()) {
                System.out.println(name + " ate a " + food);
            } else {
                System.out.println(name + " fail to eat.");
            }
        }//while
    }
    boolean eatFood() {
        return table.remove(food);
    }
}

class Cook implements Runnable {
    private Table table;

    Cook(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            // 임의의 요리를 선택해 table에 추가
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
        }//while
    }//run

}

class Table {
    String[] dishNames = {"donut", "burger", "donut"}; // donut이 더 자주 나온다.
    final int MAX_FOOD = 6; // 테이블에 놓을 수 있는 최대 음식 개수

    private ArrayList<String> dishes = new ArrayList<>();

    public void add(String dish) {
        // 테이블에 음식이 가득 찼으면, 테이블에 음식 추가 X
        if (dishes.size() > MAX_FOOD) {
            return;
        }
        dishes.add(dish);
        System.out.println("Dishes: " + dishes);
    }

    public boolean remove(String dishName) {
        // 지정된 요리와 일치하는 요리를 테이블에서 제거한다.
        for (int i = 0; i < dishes.size(); i++) {
            if (dishName.equals(dishes.get(i))) {
                dishes.remove(i);
                return true;
            }
        }
        return false;
    }
    public int dishNum() {
        return dishNames.length;
    }
}
