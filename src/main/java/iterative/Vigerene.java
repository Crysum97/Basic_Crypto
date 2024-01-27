package iterative;

import engine.CryptoEngine;
import util.CryptoException;

import java.util.Arrays;

/**
 * iterative implementation of the vigerene encryption
 */
public class Vigerene implements CryptoEngine {

    /**
     * not supported
     * @param input text to encrypt
     * @param key key to use
     * @return encrypted text
     * @throws CryptoException always
     */
    @Override
    public String encrypt(String input, int key) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * encrypts a given text using a given text key
     * @param input text to encrypt
     * @param key text key to use
     * @return encrypted text
     */
    @Override
    public String encrypt(String input, String key) {
        // construct char table
        char[][] array = generate_table();
        String cleaned = cleanString(input);
        String cleanedKey = cleanString(key);

        // prepare result
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        // iterate through string
        for (int index = 0; index < cleaned.length(); index++) {
            // lookup resulting character
            result.append(array[cleaned.charAt(index) - 65][cleanedKey.charAt(keyIndex++) - 65]);
            if (keyIndex >= key.length()) {
                keyIndex = 0;
            }
        }
        return result.toString();
    }

    /**
     * not supported
     * @param input text to encrypt
     * @return encrypted text
     * @throws CryptoException always
     */
    public String encrypt(String input) throws CryptoException{
        throw new CryptoException();
    }

    /**
     * not supported
     * @param input text to decrypt
     * @param key key to use
     * @return decrypted text
     * @throws CryptoException always
     */
    @Override
    public String decrypt(String input, int key) throws CryptoException {
        throw new CryptoException();
    }

    /**
     * decrypts a given text using a given text key
     * @param input text to decrypt
     * @param key text key to use
     * @return decrypted text
     */
    @Override
    public String decrypt(String input, String key) {
        // construct char table
        char[][] array = generate_table();
        String cleaned = cleanString(input);
        String cleanedKey = cleanString(key);

        // prepare result
        StringBuilder result = new StringBuilder();
        int keyIndex = 0;
        // iterate through string
        for (int index = 0; index < cleaned.length(); index++) {
            int keyLetter = cleanedKey.charAt(keyIndex) - 65;
            // lookup resulting character
            for (int i = 0; i < 26; i++) {
                if (cleaned.charAt(index) == array[keyLetter][i]) {
                    result.append(array[0][i]);
                    keyIndex++;
                    if(keyIndex >= key.length()) {
                        keyIndex = 0;
                    }
                    break;
                }
            }
        }
        return result.toString();
    }

    /**
     * not supported
     * @param input text to decrypt
     * @return decryted text
     * @throws CryptoException always
     */
    @Override
    public String decrypt(String input) throws CryptoException {
        throw new CryptoException();
    }


    /**
     * Constructs the character table used for encryption and decryption
     * @return vigerene character table
     */
    private static char[][] generate_table() {
        char[][] array = new char[26][26];

        for (int x = 0; x < 26; x++) {
            for (int y = 0; y < 26; y++) {
                // determine which character is written to the table
                int ascii = ((x + y) % 26) + 65;
                array[x][y] = (char)ascii;
            }
        }

        // uncomment to print the char table
//        for(char[] sub: array) {
//            System.out.println(Arrays.toString(sub));
//        }

        return array;
    }
}
