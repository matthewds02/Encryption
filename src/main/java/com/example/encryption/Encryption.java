package com.example.encryption;

import java.util.*;

public class Encryption {

    public static final String alpha = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String message, int offset, char key) {
        // Handling only lowercase strings
        message = message.toLowerCase();

        // Convert key to uppercase
        key = Character.toUpperCase(key);

        // Determine key value (A=1, B=2, ..., Z=26)
        int keyVal = key - 'A' + 1;

        // Final cipher text
        String cipherText = "";

        for (int i = 0; i < message.length(); i++) {
            // Determine the character position in alpha
            int charPosition = alpha.indexOf(message.charAt(i));

            // Encryption using the first offset
            int keyVal1 = (offset + charPosition) % 26;

            // Encryption using the second offset
            int keyVal2 = (keyVal + keyVal1) % 26;

            // Encrypted character
            char replaceChar;
            if (message.charAt(i) == ' ') { // check if character is space
                replaceChar = '['; // replace with [
            } else if (message.charAt(i) == '[') { // check if character is [
                replaceChar = '['; // do not encrypt again
            } else {
                replaceChar = alpha.charAt(keyVal2); // otherwise, encrypt using Caesar cipher
            }

            // Append the encrypted character
            cipherText += replaceChar;
        }

        // Return ciphered text
        return cipherText;
    }

    public static String decrypt(String cipherText, int offset5, char key5) {
        // Handling only lowercase strings
        cipherText = cipherText.toLowerCase();

        // Convert key to uppercase
        key5 = Character.toUpperCase(key5);

        // Determine key value (A=1, B=2, ..., Z=26)
        int keyVal = key5 - 'A' + 1;

        // Final message after decryption
        String message = "";

        for (int i = 0; i < cipherText.length(); i++) {
            // Determine the character position in alpha
            int charPosition = alpha.indexOf(cipherText.charAt(i));

            // Decryption using the second offset
            int keyVal2 = (charPosition - keyVal + 26) % 26;

            // Decryption using the first offset
            int keyVal1 = (keyVal2 - offset5 + 26) % 26;

            // Decrypted character
            char replaceChar;
            if (cipherText.charAt(i) == '[') { // check if character is [
                replaceChar = ' '; // replace with space
            } else {
                replaceChar = alpha.charAt(keyVal1); // otherwise, decrypt using Caesar cipher
            }

            // Append the decrypted character
            message += replaceChar;
        }

        // Return decrypted message
        return message;
    }

    //main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String message = new String();
        System.out.print("Enter the String for Encryption: ");
        message = sc.nextLine();

        System.out.println("\n\nEnter Offset: ");
        int offset = sc.nextInt();

        System.out.println("\n\nEnter Key (A to Z): ");
        char key = sc.next().charAt(0);

        System.out.println("\nEncrypted msg: " + encrypt(message, offset, key));

        System.out.println("\n\nZelf iets decrypteren(1) of encrypted bericht decrypteren(2)?");
        int keuze = sc.nextInt();

        if (keuze == 1) {
            String cipherText = new String();
            System.out.print("Enter the String for Decryption: ");
            sc.nextLine(); // voeg deze regel toe
            cipherText = sc.nextLine();

            System.out.println("\n\nEnter Offset: ");
            int offset5 = sc.nextInt();

            System.out.println("\n\nEnter Key (A to Z): ");
            char key5 = sc.next().charAt(0);

            String message5 = decrypt(cipherText, offset5, key5);

            System.out.println("Decrypted msg: " + message5);
        } else if (keuze == 2) {
            String cipherText = encrypt(message, offset, key);
            int offset5 = offset;
            char key5 = key;

            String message5 = decrypt(cipherText, offset5, key5);

            System.out.println("Decrypted msg: " + message5);
        }
        else {
            System.out.println("\n\nU hebt geen geldig getal ingevoerd!");
        }
        sc.close();
    }
}
