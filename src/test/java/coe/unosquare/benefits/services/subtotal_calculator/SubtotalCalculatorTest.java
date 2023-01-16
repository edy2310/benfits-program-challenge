package coe.unosquare.benefits.services.subtotal_calculator;

import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubtotalCalculatorTest {

    private static SubtotalCalculator subtotalCalculator;

    @BeforeAll
    static void setUp(){
        subtotalCalculator = new SubtotalCalculatorImpl();
    }

    @Test
    public void getSubtotalSingleProductTest() throws InvalidProductException {
        // Arrange
        String productName = "ProductName";
        double productPrice = 100.0;
        int productType = 2;
        int productTotal = 5;
        Product product = new Product(productName, productPrice, productType);
        Map<Product, Integer> products = Collections.singletonMap(product, productTotal);

        // Act
        double subtotal = subtotalCalculator.getSubtotal(products);

        // Assert
        assertEquals(500, subtotal);
    }

    @Test
    public void getSubtotalMultipleProductTest() throws InvalidProductException {
        // Arrange
        int productType = 2;
        Product product1 = new Product("Product 1", 100.0, productType);
        Product product2 = new Product("Product 2", 200.0, productType);
        Map<Product, Integer> products = new HashMap<>();
        products.put(product1, 2);
        products.put(product2, 3);

        // Act
        double subtotal = subtotalCalculator.getSubtotal(products);

        // Assert
        assertEquals(800, subtotal);
    }

    @Test
    public void getSubtotalThrowInvalidProductTest() {
        // Assert
        assertThrows(InvalidProductException.class, () -> {

            //Arrange
            int productTotal = 1;
            Map<Product, Integer> products = Collections.singletonMap(null, productTotal);

            // Act
            subtotalCalculator.getSubtotal(products);
        });
    }
}
