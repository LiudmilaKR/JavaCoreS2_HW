package lesson2.examples;

import java.util.Arrays;
import java.util.Random;

public class Example2 {
    public static void main(String[] args) {
        int[] mas = new int[3];
        Random rand = new Random();
        for (int i = 0; i < mas.length; i++) {
            mas[i] = rand.nextInt(1, 5);
        }
        System.out.println(Arrays.toString(mas));
        int n = 5;
        shifter(mas, n);
        System.out.println(Arrays.toString(mas));
    }
    /*
    * Написать метод, принимающий на вход массив чисел и параметр n.
    * Метод должен осуществить циклический (последний элемент при сдвиге становится первым)
    * сдвиг всех элементов массива на n позиций;
    * */
    private static void shifter(int[] a, int n) {
        n %= a.length;
        System.out.println(n);
        int shift = a.length + n;
        shift %= a.length;
        for (int i = 0; i < shift; i++) {
            int temp = a[a.length - 1];
            System.arraycopy(a, 0, a, 1, a.length - 1);
            /*Метод System.arraycopy() принимает несколько параметров.
Первым параметром является массив-источник.
Вторым параметром является позиция начала нового массива.
Третий параметр — массив-назначения.
Четвертый параметр является начальным положением целевого массива.
Последний параметр это количество элементов, которые будут скопированы.*/
            a[0] = temp;
        }
    }
}
