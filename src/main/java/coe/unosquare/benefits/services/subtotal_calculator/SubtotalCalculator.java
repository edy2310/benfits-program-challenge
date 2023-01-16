package coe.unosquare.benefits.services.subtotal_calculator;

import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The Subtotal calculator service interface
 */
public interface SubtotalCalculator {

    /**
     * Calculates the subtotal of the products
     *
     * @param products to calculate
     * @return the subtotal as a double
     */
    double getSubtotal(Map<Product, Integer> products) throws InvalidProductException;
}
