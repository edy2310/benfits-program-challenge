package coe.unosquare.benefits.services.payment;

import coe.unosquare.benefits.common.exceptions.InvalidPaymentTypeException;
import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The Payment service interface
 */
public interface PaymentService {

    /**
     * Calculates the total of an order after the discount
     *
     * @param paymentType to know the discount type
     * @param products to calculate the total
     * @return the total as a double
     */
    Double pay(String paymentType, Map<Product, Integer> products) throws InvalidPaymentTypeException, InvalidProductException;
}
