
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    final private static int FIELD_LENGTH = 5;
    final private static int NUMBER_OF_ATTEMPTS_LIMIT = 10;

    final private static int PLAYER = 2;
    final private static int ITEM = 8;
    final private static int EMPTY_CELL = 0;
    final private static int BORDER = 1;

    final private static int UP = 50;
    final private static int DOWN = 56;
    final private static int LEFT = 52;
    final private static int RIGHT = 54;

    public static void main(String[] args) throws InterruptedException {


    // Задаём базовое игровое поле без игрока и без предмета.
    //                   0  1  2  3  4  5
        int[][] field = {{1, 1, 1, 1, 1, 1},     //0
                         {1, 0, 0, 0, 0, 1},     //1
                         {1, 0, 0, 0, 0, 1},     //2
                         {1, 0, 0, 0, 0, 1},     //3
                         {1, 0, 0, 0, 0, 1},     //4
                         {1, 1, 1, 1, 1, 1}};    //5

        int counterAttempts = 0;

        // Определяем стартовое положение игрока (playerLocationX, playerLocationY) и предмета (itemLocationX, itemLocationY).
        
        // Создаём ArrayList длиной FIELD_LENGTH, заполняем его значениями от 1 до FIELD_LENGTH, 
        // перемешиваем эти значения, после берём значения под индексами 0 и 1 и присваиваем 
        // их playerLocationX и itemLocationX, что гарантирует нам, что значения не повтаряться и PLAYER и ITEM 
        // не окажатся в одной ячейке. То же самое делаем для координаты Y.
        ArrayList<Integer> listForLocationX = new ArrayList<>();
        for (int i = 1; i < FIELD_LENGTH; i++) {
            listForLocationX.add(i);
        }
        Collections.shuffle(listForLocationX);

        int playerLocationX = listForLocationX.get(0);
        int itemLocationX = listForLocationX.get(1);

        ArrayList<Integer> listForLocationY = new ArrayList<>();
        for (int i = 1; i < FIELD_LENGTH; i++) {
            listForLocationY.add(i);
        }
        Collections.shuffle(listForLocationY);

        int playerLocationY = listForLocationY.get(0);
        int itemLocationY = listForLocationY.get(1);

        // Добавляем игрока и предмет на игровое поле. Выводим игровое поле в консоль.
        field[playerLocationX][playerLocationY] = PLAYER;
        field[itemLocationX][itemLocationY] = ITEM;

        for (int i = 1; i < FIELD_LENGTH; i++) {
            for (int j = 1; j < FIELD_LENGTH; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println("This is the game field.");
        System.out.println("\"2\" is a player, \"8\" is an item.");
        System.out.println("You can move on the field.");
        System.out.println("\"2\" is for UP, \"8\" is for DOWN, \"4\" is for LEFT, \"6\" is for RIGHT.");
        System.out.println("Be wise when you choose your way.");
        System.out.println("Ready or not - let's go!");
        Thread.sleep(500);
        System.out.print("3");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(200);
            System.out.print(".");
        }
        System.out.print("2");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(200);
            System.out.print(".");
        }
        System.out.print("1");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(200);
            System.out.print(".");
        }
        System.out.println("GO!");
        Thread.sleep(500);

        // Двигаемся по полю и пытаемся подобрать предмет.
        // Делаем это пока количество попыток меньше, чем NUMBER_OF_ATTEMPTS_LIMIT.
        for (int k = 0; k < NUMBER_OF_ATTEMPTS_LIMIT; k++) {

            System.out.println("Which way?");

            Scanner in = new Scanner(System.in);
            String check = in.nextLine();

            if (check.length() != 1) {
                counterAttempts++;
                if (counterAttempts == NUMBER_OF_ATTEMPTS_LIMIT) {
                    continue;
                }
                System.out.println("Choose you way carefully!");
            }

            int move = check.charAt(0);

            if ((move != UP) & (move != DOWN) & (move != LEFT) & (move != RIGHT)) {
                counterAttempts++;
                if (counterAttempts == NUMBER_OF_ATTEMPTS_LIMIT) {
                    continue;
                }
                System.out.println("Choose you way carefully!");
            }

            switch (move) {
                // Двигаемся вверх.
                case UP -> {
                    counterAttempts++;
                    switch (field[playerLocationX - 1][playerLocationY]) {
                        case EMPTY_CELL -> {
                            playerLocationX = playerLocationX - 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX + 1][playerLocationY] = EMPTY_CELL;
                            for (int i = 1; i < FIELD_LENGTH; i++) {
                                for (int j = 1; j < FIELD_LENGTH; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                        }
                        case BORDER -> {
                            if (counterAttempts == NUMBER_OF_ATTEMPTS_LIMIT) {
                                continue;
                            }
                            System.out.println("You hit the border. Go another way.");
                        }
                        case ITEM -> {
                            playerLocationX = playerLocationX - 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX + 1][playerLocationY] = EMPTY_CELL;
                        }
                        default -> System.out.println("Error!");
                    }
                }

                // Двигаемся вниз.
                case DOWN -> {
                    counterAttempts++;
                    switch (field[playerLocationX + 1][playerLocationY]) {
                        case EMPTY_CELL -> {
                            playerLocationX = playerLocationX + 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX - 1][playerLocationY] = EMPTY_CELL;
                            for (int i = 1; i < FIELD_LENGTH; i++) {
                                for (int j = 1; j < FIELD_LENGTH; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                        }
                        case BORDER -> {
                            if (counterAttempts == NUMBER_OF_ATTEMPTS_LIMIT) {
                                continue;
                            }
                            System.out.println("You hit the border. Go another way.");
                        }
                        case ITEM -> {
                            playerLocationX = playerLocationX + 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX - 1][playerLocationY] = EMPTY_CELL;
                        }
                        default -> System.out.println("Error!");
                    }
                }

                // Двигаемся влево.
                case LEFT -> {
                    counterAttempts++;
                    switch (field[playerLocationX][playerLocationY - 1]) {
                        case EMPTY_CELL -> {
                            playerLocationY = playerLocationY - 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX][playerLocationY + 1] = EMPTY_CELL;
                            for (int i = 1; i < FIELD_LENGTH; i++) {
                                for (int j = 1; j < FIELD_LENGTH; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                        }
                        case BORDER -> {
                            if (counterAttempts == NUMBER_OF_ATTEMPTS_LIMIT) {
                                continue;
                            }
                            System.out.println("You hit the border. Go another way.");
                        }
                        case ITEM -> {
                            playerLocationY = playerLocationY - 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX][playerLocationY + 1] = EMPTY_CELL;
                        }
                        default -> System.out.println("Error!");
                    }
                }

                // Двигаемся вправо.
                case RIGHT -> {
                    counterAttempts++;
                    switch (field[playerLocationX][playerLocationY + 1]) {
                        case EMPTY_CELL -> {
                            playerLocationY = playerLocationY + 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX][playerLocationY - 1] = EMPTY_CELL;
                            for (int i = 1; i < FIELD_LENGTH; i++) {
                                for (int j = 1; j < FIELD_LENGTH; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                        }
                        case BORDER -> {
                            if (counterAttempts == NUMBER_OF_ATTEMPTS_LIMIT) {
                                continue;
                            }
                            System.out.println("You hit the border. Go another way.");
                        }
                        case ITEM -> {
                            playerLocationY = playerLocationY + 1;
                            field[playerLocationX][playerLocationY] = PLAYER;
                            field[playerLocationX][playerLocationY - 1] = EMPTY_CELL;
                        }
                        default -> System.out.println("Error!");
                    }
                }
            } // Конец внешнего switch.

            // Проверяем, остался ли предмет на игровом поле. Если предмета нет, то завершаем игру.
            int count = 0;
            for (int i = 1; i < FIELD_LENGTH; i++) {
                for (int j = 1; j < FIELD_LENGTH; j++) {
                    if (field[i][j] == ITEM) {
                        count++;
                    }
                }
            }
            if (count == 0) {
                for (int i = 1; i < FIELD_LENGTH; i++) {
                    for (int j = 1; j < FIELD_LENGTH; j++) {
                        System.out.print(field[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println("""
                        ░░░░░░░░░░░░░░░░░░░░
                        ░░░░░▄▀▀▀▄░░░░░░░░░░
                        ▄███▀░◐░░░▌░░░░░░░░░
                        ░░░░▌░░░░░▐░░░░░░░░░
                        ░░░░▐░░░░░▐░░░░░░░░░
                        ░░░░▌░░░░░▐▄▄░░░░░░░
                        ░░░░▌░░░░▄▀▒▒▀▀▀▀▄
                        ░░░▐░░░░▐▒▒▒▒▒▒▒▒▀▀▄
                        ░░░▐░░░░▐▄▒▒▒▒▒▒▒▒▒▒▀▄
                        ░░░░▀▄░░░░▀▄▒▒▒▒▒▒▒▒▒▒▀▄
                        ░░░░░░▀▄▄▄▄▄█▄▄▄▄▄▄▄▄▄▄▄▀▄
                        ░░░░░░░░░░░▌▌░▌▌░░░░░
                        ░░░░░░░░░░░▌▌░▌▌░░░░░
                        ░░░░░░░░░▄▄▌▌▄▌▌░░░░░""");
                System.out.println("You picked it up!");
                System.out.println("Hail to the goose!");
                break;
            }

        } // Конец for(k99).
        if (counterAttempts == NUMBER_OF_ATTEMPTS_LIMIT) {
            System.out.println("Okay. That's enough.");
            Thread.sleep(1000);
            System.out.println("You are way too slow. LOL");
            Thread.sleep(1000);
            System.out.println("""
                    ░░░░░░░▄▄███████▄▄░░░░░░░░░░░░░░░░░░
                    ░░░░░▄███████▀██████▄░░░░░░░░░░░░░░░
                    ░░░▄████▀▀░░░░░░░▀▀██▄░░░░░░░░░░░░░░
                    ░░░████░░░▄█████▄░░▀███░░░░░░▄░░░░░░
                    ░░████░░░█▀░░░░███▄░░██░░░░░███░░▄█▄
                    ░░████░░░█░░░░░░███░░██░░░░░░█░░░▀█▀
                    ░░░████░░░░░░░░▄███░░░█░░░░░▄▀░░░▄▀░
                    ░░░▀████▄░░░░▄▄███▀░░█▀░░░░░█▄░░░█░░
                    ░░░░░▀███████████▄▄▄██████████▄▄█░░░
                    ░░░░░░░▀▀███████████████████████▀░░░
                    ░░░░▄▄▄█████▀▀▀▀▀▀▀▀▀▀▀▀▀███████▄░░░
                    ▄█████████████████████████████████░░""");
        }

    } // Конец метода main.
}