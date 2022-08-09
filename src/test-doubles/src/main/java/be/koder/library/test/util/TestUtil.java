package be.koder.library.test.util;

public enum TestUtil {

    INSTANCE;

    public void fail() {
        throw new UnsupportedOperationException("This test should not be called.");
    }
}