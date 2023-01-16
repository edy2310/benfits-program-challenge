package coe.unosquare.benefits.services.discount_calculator;

import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The Discount calculator service interface
 */
public interface DiscountCalculator {

    /**
     * Gets the discount depending on the discount calculator instance
     *
     * @param products to calculate the discount
     * @return the decimal discount as a double
     */
    double getDiscount(Map<Product, Integer> products);
}
