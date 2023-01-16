package coe.unosquare.benefits.services.printer;

import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The Print service interface
 */
public interface PrintService {

    /**
     * Print all the products details
     *
     * @param products to print
     */
    void printProducts(Map<Product, Integer> products) throws InvalidProductException;
}
