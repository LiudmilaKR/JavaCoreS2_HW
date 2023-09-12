package lesson2.examples;

import java.util.Arrays;

public class Example6 {
    public static void main(String[] args) {
        int[] mas = {1, 2};
        int size = 2;
        System.out.println(size + " = " + Arrays.toString(mas));
        mas = add(mas, size++, 5);
        System.out.println(size + " = " + Arrays.toString(mas));
        mas = add(mas, size++, 5);
        System.out.println(size + " = " + Arrays.toString(mas));
        mas = add(mas, size++, 5);
        System.out.println(size + " = " + Arrays.toString(mas));
    }
    /*Написать функцию добавления элемента в конец массива таким
    образом, чтобы она расширяла массив при необходимости.
    */
    private static int[] add(int[] arr, int current, int value) {
        if (current == arr.length) {
            int[] temp = new int[arr.length * 2];
            System.out.println(Arrays.toString(temp));
            System.arraycopy(arr, 0, temp, 0, arr.length);
            arr = temp;
        }
        arr[current++] = value;
        return arr;
    }
}
