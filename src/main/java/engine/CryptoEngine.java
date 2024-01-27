package engine;

import util.CryptoException;

/**
 * basic interface to handle different crypto implementations
 */
public interface CryptoEngine {
    String encrypt(String input, int key) throws CryptoException;
    String encrypt(String input, String key) throws CryptoException;
    String encrypt(String input) throws CryptoException;
    String decrypt(String input, int key) throws CryptoException;
    String decrypt(String input, String key) throws CryptoException;
    String decrypt(String input) throws CryptoException;

    default String cleanString(String input) {
        return input.toUpperCase().replaceAll("[^A-Z]", "");
    }
}
