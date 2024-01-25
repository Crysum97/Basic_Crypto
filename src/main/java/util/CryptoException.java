package util;

import jdk.jshell.spi.ExecutionControl;

import static jdk.jshell.spi.ExecutionControl.*;

public class CryptoException extends NotImplementedException {
    public CryptoException(String message) {
        super(message);
    }
}
