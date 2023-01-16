package coe.unosquare.benefits.common.exceptions;

/**
 * Invalid payment type exception
 */
public class InvalidPaymentTypeException extends Exception {

    /**
     * Instantiates a new Invalid Payment Type Exception.
     *
     * @param message as the message for the exception
     */
    public InvalidPaymentTypeException(String message) {
        super(message);
    }
}
