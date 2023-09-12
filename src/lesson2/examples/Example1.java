package lesson2.examples;

public class Example1 {
    /**
     * Написать метод «Шифр Цезаря», с булевым параметром зашифрования/расшифрования, и числовым ключом;
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(caesar("", 5, true));
        System.out.println(caesar("baby", 5, true));
        System.out.println(caesar("baby", 5, false));
    }
    private static String caesar(String in, int key, boolean encrypt) {
        if (in == null || in.isEmpty()) return null;
        final int len = in.length();
        char[] out = new char[len];
        for (int i = 0; i < len; i++) {
            out[i] = (char) (in.charAt(i) + ((encrypt) ? key : -key));
        }
        return new String(out);
    }
}
