package lesson2.examples;

import java.util.Scanner;

public class Example8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a, b;
        while (true) {
            System.out.print("Введите a и b (через пробел) => ");
            if (scan.hasNextInt()) {
                a = scan.nextInt();
                b = scan.nextInt();
//                     scan.nextLine();
                break;
            } else {
                System.out.println("Некорректное число, повторите попытку ввода.");
                scan.nextLine();
            }
        }

        System.out.println(a + " " + b);
    }
}
