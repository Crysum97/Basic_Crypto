package util;


import static jdk.jshell.spi.ExecutionControl.*;

public class CryptoException extends NotImplementedException {
    public CryptoException() {
        super("This implementation is not supported by this instance");
    }
}
