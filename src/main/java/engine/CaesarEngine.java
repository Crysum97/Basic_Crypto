package engine;

public interface CaesarEngine {
    String encrypt(String input, int key);
    String decrypt(String input, int key);
}
