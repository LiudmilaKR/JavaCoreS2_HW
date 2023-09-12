package lesson2.homeworkV1;

import java.util.Random;
import java.util.Scanner;

public class Program {
    /**
     * Простая реализация
     */
    private static  final int WIN_COUNT = 4; // Выигрышная комбинация
    private static final char DOT_HUMAN = 'X'; // Фишка игрока - человек
    private static final char DOT_AI = '0'; // Фишка игрока - комп
    private static final char DOT_EMPTY = '*'; // Признак пустого поля
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field; // Двумерный массив хранит текущее состояние игрового поля
    private static int fieldSizeX; // Размерность игрового поля
    private static int fieldSizeY; // Размерность игрового поля

    public static void main(String[] args) {
        do {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!")) break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Комп победил!")) break;
            }
            System.out.println("Желаете сыграть ещё раз? (Y - да): ");
        } while (scanner.next().equalsIgnoreCase("Y"));
    }
    /**
     * Инициализация объектов игры
     */
    private static void initialize() {
        while (true) {
            System.out.print("Введите размеры игрового поля (через пробел) => ");
            if (scanner.hasNextInt()) {
                 fieldSizeX = scanner.nextInt();
                 fieldSizeY = scanner.nextInt();
                if (fieldSizeX < WIN_COUNT || fieldSizeY < WIN_COUNT) {
                    System.out.printf("Размеры поля должны быть больше или равны %d", WIN_COUNT);
                    System.out.println();
                } else break;
            } else {
                System.out.println("Некорректное число, повторите попытку ввода.");
                scanner.nextLine();
            }
        }
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }
    /* Отрисовка игрового поля
     *    1  2  3
     * 1  *  X  0
     * 2  *  *  0
     * 3  *  *  0
     * ----------
     */
    private static void printField() {
        System.out.print(" ");
        for (int y = 0; y < fieldSizeY * 2 + 1; y++) {
            System.out.print(y % 2 == 0 ? "  " : y / 2 + 1);
        }
        System.out.println();
        for (int x = 0; x < fieldSizeX; x++) {
            System.out.print(x + 1 + "  ");
            for (int y = 0; y < fieldSizeY; y++) {
                System.out.print(field[x][y] + "  ");
            }
            System.out.println();
        }
        for (int x = 0; x < fieldSizeX * 2 + 3; x++) {
            System.out.print("-");
        }
        System.out.println();
    }
    /**
     * Обработка хода игрока (человек)
     */
    private static void humanTurn() {
        int x, y;
        do {
             while (true) {
                 System.out.printf("Введите координаты хода X (от 1 до %d) => ", fieldSizeX);
                 if (scanner.hasNextInt()) {
                     x = scanner.nextInt() - 1;
//                     scanner.nextLine();
                     break;
                 } else {
                     System.out.println("Некорректное число, повторите попытку ввода.");
                     scanner.nextLine();
                 }
             }
             while (true) {
                 System.out.printf("Введите координату хода Y (от 1 до %d) => ", fieldSizeY);
                 if (scanner.hasNextInt()) {
                     y = scanner.nextInt() - 1;
//                     scanner.nextLine();
                     break;
                 } else {
                     System.out.println("Некорректное число, повторите попытку ввода.");
                     scanner.nextLine();
                 }
             }
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Проверка, является ли ячейка пустой (DOT_EMPTY)
     * @param x номер строки
     * @param y номер столбца
     * @return true, если поле пустое * (не занято 0 или X)
     */
    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }
    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность игрового поля)
     * @param x номер строки
     * @param y номер столбца
     * @return true, если x и y валидны
     */
    private static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Обработка хода компьютера
     */
    private static void aiTurn () {
        int x, y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /** Проверка победы по каждому из полей
     *
     * @param c фишка игрока
     * @return true, если есть выигрышное поле, false - если нет
     */
    private static boolean checkWin(char c) {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (field[x][y] == c && check(x, y, c, WIN_COUNT)) return true;
            }
        }
        return false;
    }
    /**
     * проверка победы по всем направлениям
     * @param x номер строки
     * @param y номер столбца
     * @param c фишка игрока
     * @param win проверяемая комбинация
     * @return true, если какое-то из направлений - выигрышное, false - если нет
     */
    static boolean check(int x, int y, char c, int win) {
        if ((y + win) <= fieldSizeY) {
            if (check1(x, y, c, win)) return true; // вправо
        }
        if ((x + win) <= fieldSizeX) {
            if (check2(x, y, c, win)) return true; // вниз
        }
        if ((x + win) <= fieldSizeX && (y + win) <= fieldSizeY) {
            if (check3(x, y, c, win)) return true; // диагональ вправо вниз
        }
        if ((y + win) <= fieldSizeY && (x - win + 1) >= 0) {
            if (check4(x, y, c, win)) return true; // диагональ вправо в
        }
        return false;
    }

    /**
     * Проверка win штук подряд фишек по горизонтали x вправо
     * @param x номер строки
     * @param y номер столбца
     * @param c фишка игрока
     * @param win проверяемая комбинация
     * @return true, если win фишек подряд по x, false - если нет
     */
    static boolean check1(int x, int y, char c, int win) {
        int col = 0;
        for (int i = 0; i < win; i++) {
            if (field[x][y + i] == c) col++;
        }
        return (col == win);
    }
    /**
     * Проверка win штук подряд фишек по вертикали y вниз
     * @param x номер строки
     * @param y номер столбца
     * @param c фишка игрока
     * @param win проверяемая комбинация
     * @return true, если win фишек подряд по y, false - если нет
     */
    static boolean check2(int x, int y, char c, int win) {
        int col = 0;
        for (int i = 0; i < win; i++) {
            if (field[x + i][y] == c) col++;
        }
        return (col == win);
    }
    /**
     * Проверка win штук подряд фишек по диагонали вправо вниз
     * @param x номер строки
     * @param y номер столбца
     * @param c фишка игрока
     * @param win проверяемая комбинация
     * @return true, если win фишек подряд по диагонали вправо вниз, false - если нет
     */
    static boolean check3(int x, int y, char c, int win) {
        int col = 0;
        for (int i = 0; i < win; i++) {
            if (field[x + i][y + i] == c) col++;
        }
        return (col == win);
    }
    /**
     * Проверка win штук подряд фишек по диагонали вправо вверх
     * @param x номер строки
     * @param y номер столбца
     * @param c фишка игрока
     * @param win проверяемая комбинация
     * @return true, если win фишек подряд по диагонали вправо вверх, false - если нет
     */
    static boolean check4(int x, int y, char c, int win) {
        int col = 0;
        for (int i = 0; i < win; i++) {
            if (field[x - i][y + i] == c) col++;
        }
        return (col == win);
    }
        /**
         * Проверка на ничью
         * @return true, если все поля заполнены, false, если есть ещё свободные поля для хода
         */
    private static boolean checkDraw() {
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * Проверка состояния игры
     * @param c фишка игрока
     * @param s победный слоган
     * @return true в случае окончания игры, false, если игра продолжается
     */
    private static boolean checkGameState(char c, String s) {
        if (checkWin(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
}
