package lesson2.examples;

import java.util.Arrays;
import java.util.Random;

public class Example5 {
    public static void main(String[] args) {
        int[] mas = new int[5];
        Random rand = new Random();
        for (int i = 0; i < mas.length; i++) {
            mas[i] = rand.nextInt(1, 5);
        }
        System.out.println(Arrays.toString(mas) + " => " + checkBalance(mas));
    }
    /*Написать метод, в который передается не пустой одномерный
целочисленный массив, метод должен вернуть true если в массиве есть
место, в котором сумма левой и правой части массива равны. Примеры:
checkBalance([1, 1, 1, || 2, 1]) → true,
checkBalance([2, 1, 1, 2, 1]) → false,
checkBalance([10, || 1, 2, 3, 4]) → true.
Абстрактная граница показана символами ||, эти символы в массив не
входят.*/
    private static boolean checkBalance(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        if (sum % 2 != 0) return false;
        int left = 0;
        for (int i = 0; i < a.length; i++) {
            left += a[i];
            sum -=a[i];
            if (left == sum) return true;
        }
        return false;
    }
}
