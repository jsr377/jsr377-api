package javax.application;

public interface MessageSource {
    String getMessage(String key) throws NoSuchMessageException;

    String getMessage(String key, String defaultValue);
}
