package lesson2.examples;

import java.util.Arrays;
import java.util.Random;

public class Example7 {
    public static void main(String[] args) {
        int[] mas = new int[11];
        Random rand = new Random();
        for (int i = 0; i < mas.length; i++) {
            mas[i] = rand.nextInt(1, 5);
        }
        System.out.println(Arrays.toString(mas));
        pigeon(mas);
        System.out.println(Arrays.toString(mas));
    }
    /*Написать метод, осуществляющий
    сортировку одномерного массива подсчётом.
    Важное ограничение состоит в том, что для
    этой сортировки диапазон значений исходного
    массива должен находиться в разумных
    пределах, например, не более 1000.
    x[2, 1, 0, 4, 3, 0, 0, 1, 2] →
    t[3(x0), 2(x1), 2(x2), 1(x3), 1(x4)] → x[0, 0, 0, 1, 1, 2, 2, 3, 4]
    */
    private static void pigeon(int[] arr) {
        final int min = getMin(arr);
        final int max = getMax(arr);
        int[] freq = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) freq[arr[i] - min]++;
        int arrIndex = 0;
        for (int i = 0; i < freq.length; i++) {
            for (int el = freq[i]; el > 0; el --) arr[arrIndex++] = i + min;
        }
    }

    private static int getMax(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        return max;
    }

    private static int getMin(int[] a) {
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) min = a[i];
        }
        return min;
    }
}
