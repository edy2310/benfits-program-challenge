package coe.unosquare.benefits.services.subtotal_calculator;

import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The Subtotal calculator service implementation
 */
public class SubtotalCalculatorImpl implements SubtotalCalculator{

    /**
     * Calculates the subtotal of the products
     *
     * @param products to calculate
     * @return the subtotal as a double
     */
    public double getSubtotal(Map<Product, Integer> products) throws InvalidProductException {
        double subtotal = 0;
        for(Map.Entry<Product, Integer> entry : products.entrySet()){
            if(entry.getKey() == null){
                throw new InvalidProductException("Product cannot be null.");
            }
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }
}
