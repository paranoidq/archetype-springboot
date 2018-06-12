package me.webapp.exception;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public class PagingException extends DaoException {

    public PagingException() {
    }

    public PagingException(String message) {
        super(message);
    }

    public PagingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PagingException(Throwable cause) {
        super(cause);
    }

    public PagingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
