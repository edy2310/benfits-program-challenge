package coe.unosquare.benefits.services.discount_calculator;

import coe.unosquare.benefits.common.consts.DecimalDiscount;
import coe.unosquare.benefits.models.Product;
import coe.unosquare.benefits.util.ProductGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisaDiscountCalculatorTest {

    private static DiscountCalculator visaDiscountCalculator;

    @BeforeAll
    static void setUp(){
        visaDiscountCalculator = new VisaDiscountCalculator();
    }

    @Test
    public void getDiscountMoreThan10Test(){
        // Arrange
        Map<Product, Integer> productsMoreThan10 = ProductGenerator.generateProducts(15);

        // Act
        double discountForMoreThan10 = visaDiscountCalculator.getDiscount(productsMoreThan10);

        // Assert
        assertEquals(DecimalDiscount.DISCOUNT_15, discountForMoreThan10);
    }

    @Test
    public void getDiscountMoreThan7Test(){
        // Arrange
        Map<Product, Integer> productsMoreThan7 = ProductGenerator.generateProducts(8);

        // Act
        double discountForMoreThan7 = visaDiscountCalculator.getDiscount(productsMoreThan7);

        // Assert
        assertEquals(DecimalDiscount.DISCOUNT_10, discountForMoreThan7);
    }

    @Test
    public void getDiscountMoreThan0Test(){
        // Arrange
        Map<Product, Integer> productsMoreThan0 = ProductGenerator.generateProducts(4);

        // Act
        double discountForMoreThan0 = visaDiscountCalculator.getDiscount(productsMoreThan0);

        // Assert
        assertEquals(DecimalDiscount.DISCOUNT_05, discountForMoreThan0);
    }
}
