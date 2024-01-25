package engine;

/**
 * basic interface to handle different implementations of the caesar cipher
 */
public interface CaesarEngine {
    String encrypt(String input, int key);
    String decrypt(String input, int key);
}
