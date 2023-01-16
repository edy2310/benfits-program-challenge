package coe.unosquare.benefits.services.printer;

import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrintServiceTest {

    private static final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    private static PrintService printService;

    @BeforeAll
    static void setStreams() {
        printService = new PrintServiceImpl();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void printProductsTest() throws InvalidProductException {
        // Arrange
        String productName = "ProductName";
        double productPrice = 100.0;
        int productType = 2;
        int productTotal = 1;
        Product product = new Product(productName, productPrice, productType);
        Map<Product, Integer> products = Collections.singletonMap(product, productTotal);
        String messageExpected = String.format(
                "Product:{%s,%s,%s},Quantity:%s,Total:%s", productName, productPrice, productType, productTotal, productPrice);

        // Act
        printService.printProducts(products);

        // Assert
        assertEquals(messageExpected, out.toString());
    }

    @Test
    public void printProductsThrowInvalidProductTest() {
        // Assert
        assertThrows(InvalidProductException.class, () -> {

            //Arrange
            int productTotal = 1;
            Map<Product, Integer> products = Collections.singletonMap(null, productTotal);

            // Act
            printService.printProducts(products);
        });
    }
}
