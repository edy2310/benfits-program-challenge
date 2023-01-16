package coe.unosquare.benefits.services.printer;

import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The Print service implementation
 */
public class PrintServiceImpl implements PrintService{

    /**
     * Print all the products details
     *
     * @param products to print
     */
    public void printProducts(Map<Product, Integer> products) throws InvalidProductException {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            if (product == null) {
                throw new InvalidProductException("Product cannot be null.");
            }
            System.out.print("Product:{" + product.getName() + ","
                    + product.getPrice() + ","
                    + product.getType()
                    + "},Quantity:" + entry.getValue()
                    + ",Total:" + product.getPrice() * entry.getValue());
        }
    }
}
