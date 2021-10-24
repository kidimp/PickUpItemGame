import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private static int fieldLength = 5;
    public static void main (String[] args){

// First commit test
// Задаём базовое игровое поле без игрока и предмета.
        //               0  1  2  3  4  5
        int[][] field = {{1, 1, 1, 1, 1, 1},     //0
                         {1, 0, 0, 0, 0, 1},     //1
                         {1, 0, 0, 0, 0, 1},     //2
                         {1, 0, 0, 0, 0, 1},     //3
                         {1, 0, 0, 0, 0, 1},     //4
                         {1, 1, 1, 1, 1, 1}};    //5

        int counter = 0;

// Определяем стартовое положение игрока (xp, yp) и предмета (xi, yi).
        ArrayList<Integer> listX = new ArrayList<>();
        for (int i = 1; i < fieldLength; i++) {
            listX.add(i);
        }
        Collections.shuffle(listX);

        int xp = listX.get(0);
        int xi = listX.get(1);

        ArrayList<Integer> listY = new ArrayList<>();
        for (int i = 1; i < fieldLength; i++) {
            listY.add(i);
        }
        Collections.shuffle(listY);

        int yp = listY.get(0);
        int yi = listY.get(1);

// Добавляем игрока и предмет на игровое поле. Выводим игровое поле в консоль.
        field[xp][yp] = 2;
        field[xi][yi] = 8;

        for (int i = 1; i < fieldLength; i++) {
            for (int j = 1; j < fieldLength; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
// Двигаемся по полю и пытаемся подобрать предмет.
        for (int k = 0; k < 99; k++) {
            System.out.println("Which way?");

            Scanner in = new Scanner(System.in);
            String check = in.nextLine();
            if (check.length() != 1){
                System.out.println("Choose you way carefully!");
                continue;
            }

            int move = check.charAt(0);

            if ((move != 56) & (move != 50) & (move != 52) & (move != 54)){
                System.out.println("Choose you way carefully!");
                continue;
            }

            switch (move) {
                // Двигаемся вверх.
                case 56: {
                    switch (field[xp - 1][yp]) {
                        case 0:
                            xp = xp - 1;
                            field[xp][yp] = 2;
                            field[xp + 1][yp] = 0;
                            for (int i = 1; i < 5; i++) {
                                for (int j = 1; j < 5; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                            break;
                        case 1:
                            System.out.println("You hit the border. Go another way.");
                            break;
                        case 8:
                            xp = xp - 1;
                            field[xp][yp] = 2;
                            field[xp + 1][yp] = 0;
                            break;
                        default:
                            System.out.println("Error!");
                            break;
                    }
                    break;
                }
                // Двигаемся вниз.
                case 50: {
                    switch (field[xp + 1][yp]) {
                        case 0:
                            xp = xp + 1;
                            field[xp][yp] = 2;
                            field[xp - 1][yp] = 0;
                            for (int i = 1; i < 5; i++) {
                                for (int j = 1; j < 5; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                            break;
                        case 1:
                            System.out.println("You hit the border. Go another way.");
                            break;
                        case 8:
                            xp = xp + 1;
                            field[xp][yp] = 2;
                            field[xp - 1][yp] = 0;
                            break;
                        default:
                            System.out.println("Error!");
                            break;
                    }
                    break;
                }
                // Двигаемся влево.
                case 52: {
                    switch (field[xp][yp - 1]) {
                        case 0:
                            yp = yp - 1;
                            field[xp][yp] = 2;
                            field[xp][yp + 1] = 0;
                            for (int i = 1; i < 5; i++) {
                                for (int j = 1; j < 5; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                            break;
                        case 1:
                            System.out.println("You hit the border. Go another way.");
                            break;
                        case 8:
                            yp = yp - 1;
                            field[xp][yp] = 2;
                            field[xp][yp + 1] = 0;
                            break;
                        default:
                            System.out.println("Error!");
                            break;
                    }
                    break;
                }
                // Двигаемся вправо.
                case 54: {
                    switch (field[xp][yp + 1]) {
                        case 0:
                            yp = yp + 1;
                            field[xp][yp] = 2;
                            field[xp][yp - 1] = 0;
                            for (int i = 1; i < 5; i++) {
                                for (int j = 1; j < 5; j++) {
                                    System.out.print(field[i][j] + "  ");
                                }
                                System.out.println();
                            }
                            break;
                        case 1:
                            System.out.println("You hit the border. Go another way.");
                            break;
                        case 8:
                            yp = yp + 1;
                            field[xp][yp] = 2;
                            field[xp][yp - 1] = 0;
                            break;
                        default:
                            System.out.println("Error!");
                            break;
                    }
                    break;
                }
            } // Конец внешнего switch.

// Проверяем, остался ли предмет на игровом поле. Если предмета нет, то завершаем игру.
            int count = 0;
            for (int i = 1; i < fieldLength; i++) {
                for (int j = 1; j < fieldLength; j++) {
                    if (field[i][j] == 8) {
                        count++;
                    }
                }
            }
            if (count == 0){
                for (int i = 1; i < fieldLength; i++) {
                    for (int j = 1; j < fieldLength; j++) {
                        System.out.print(field[i][j] + "  ");
                    }
                    System.out.println();
                }
                System.out.println("Your pick it up!");
                System.out.println("Hail to the goose!");
                System.out.println("░░░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░▄▀▀▀▄░░░░░░░░░░\n" +
                        "▄███▀░◐░░░▌░░░░░░░░░\n" +
                        "░░░░▌░░░░░▐░░░░░░░░░\n" +
                        "░░░░▐░░░░░▐░░░░░░░░░\n" +
                        "░░░░▌░░░░░▐▄▄░░░░░░░\n" +
                        "░░░░▌░░░░▄▀▒▒▀▀▀▀▄\n" +
                        "░░░▐░░░░▐▒▒▒▒▒▒▒▒▀▀▄\n" +
                        "░░░▐░░░░▐▄▒▒▒▒▒▒▒▒▒▒▀▄\n" +
                        "░░░░▀▄░░░░▀▄▒▒▒▒▒▒▒▒▒▒▀▄\n" +
                        "░░░░░░▀▄▄▄▄▄█▄▄▄▄▄▄▄▄▄▄▄▀▄\n" +
                        "░░░░░░░░░░░▌▌░▌▌░░░░░\n" +
                        "░░░░░░░░░░░▌▌░▌▌░░░░░\n" +
                        "░░░░░░░░░▄▄▌▌▄▌▌░░░░░");
                break;
            }

            counter++;

        } // Конец for(k99).
            if (counter == 99){
                System.out.println("Okay. That's enough.");
                System.out.println("You are way too slow. LOL");
                System.out.println("░░░░░░░▄▄███████▄▄░░░░░░░░░░░░░░░░░░\n" +
                        "░░░░░▄███████▀██████▄░░░░░░░░░░░░░░░\n" +
                        "░░░▄████▀▀░░░░░░░▀▀██▄░░░░░░░░░░░░░░\n" +
                        "░░░████░░░▄█████▄░░▀███░░░░░░▄░░░░░░\n" +
                        "░░████░░░█▀░░░░███▄░░██░░░░░███░░▄█▄\n" +
                        "░░████░░░█░░░░░░███░░██░░░░░░█░░░▀█▀\n" +
                        "░░░████░░░░░░░░▄███░░░█░░░░░▄▀░░░▄▀░\n" +
                        "░░░▀████▄░░░░▄▄███▀░░█▀░░░░░█▄░░░█░░\n" +
                        "░░░░░▀███████████▄▄▄██████████▄▄█░░░\n" +
                        "░░░░░░░▀▀███████████████████████▀░░░\n" +
                        "░░░░▄▄▄█████▀▀▀▀▀▀▀▀▀▀▀▀▀███████▄░░░\n" +
                        "▄█████████████████████████████████░░");
            }

    } // Конец метода main.
}