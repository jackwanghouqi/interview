package interview.blackrock;

import java.io.IOException;

public class Decode {
    /** Copy of original encoding method
     private static String encode(String text) {
     StringBuilder b = new StringBuilder();
     for (int i = 0; i < text.length(); i++) {
     char c = text.charAt(i);
     b.append(c += c + i);
     }
     return b.reverse().toString();
     }
     */
    /*
     * Complete the function below.
     */
    static String decode(String encodedMessage) {
        StringBuilder encodedMessageBuilder = new StringBuilder(encodedMessage);
        String encodedMessageReversed = encodedMessageBuilder.reverse().toString();
        System.out.println(encodedMessageReversed);
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < encodedMessageReversed.length(); i++) {
            char c = encodedMessageReversed.charAt(i);
            b.append((char)((c-i)/2));
        }
        return b.toString();
    }

    private static String encode(String text) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            b.append(c += c + i);
        }
        return b.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        String res;
        String encodedMessage;
        try {
            encodedMessage = encode("testtsrds");
        } catch (Exception e) {
            encodedMessage = null;
        }

        res = decode(encodedMessage);
        System.out.println(res);
    }
}

