package lesson2.examples;

public class Example4 {
    public static void main(String[] args) {
        int n = 5;
        int[][] mas = new int[n][n];
        fillDiagonal(mas);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println();
        }

    }
    /*Создать квадратный целочисленный
массив (количество строк и столбцов
одинаковое), заполнить его диагональные
элементы единицами, используя цикл(ы) */
    private static void fillDiagonal(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            a[i][i] = 1;
            a[i][a.length - 1 - i] = 1;
        }
    }
}
