package lesson2.examples;

import java.util.Arrays;
import java.util.Random;

public class Example3 {
    public static void main(String[] args) {
        int[] mas = new int[11];
        Random rand = new Random();
        for (int i = 0; i < mas.length; i++) {
            mas[i] = rand.nextInt(0, 2);
        }
        System.out.println(Arrays.toString(mas));
        change(mas);
        System.out.println(Arrays.toString(mas));

    }
    /*Написать метод, которому можно передать в качестве аргумента массив, состоящий строго из единиц и нулей
(целые числа типа int). Метод должен заменить единицы в массиве на нули, а нули на единицы и не содержать
ветвлений. Написать как можно больше вариантов метода*/
    public static void change(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = 1 - a[i];
            /*
            * a[i] = (a[i] - 1) * (-1);
            * a[i] = (a[i] + 1) % 2;
            * */
        }
    }
}
