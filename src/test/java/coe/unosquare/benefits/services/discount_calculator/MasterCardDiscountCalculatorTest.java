package coe.unosquare.benefits.services.discount_calculator;

import coe.unosquare.benefits.common.consts.DecimalDiscount;
import coe.unosquare.benefits.models.Product;
import coe.unosquare.benefits.util.ProductGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterCardDiscountCalculatorTest {

    private static DiscountCalculator masterCardDiscountCalculator;

    @BeforeAll
    static void setUp(){
        masterCardDiscountCalculator = new MasterCardDiscountCalculator();
    }

    @Test
    void getDiscountMoreThan100Test(){
        // Arrange
        Map<Product, Integer> productsMoreThan100 = ProductGenerator.generateProducts(150.0);

        // Act
        double discountForMoreThan100 = masterCardDiscountCalculator.getDiscount(productsMoreThan100);

        // Assert
        assertEquals(DecimalDiscount.DISCOUNT_17, discountForMoreThan100);
    }

    @Test
    void getDiscountMoreThan75Test(){
        // Arrange
        Map<Product, Integer> productsMoreThan75 = ProductGenerator.generateProducts(80.0);

        // Act
        double discountForMoreThan75 = masterCardDiscountCalculator.getDiscount(productsMoreThan75);

        // Assert
        assertEquals(DecimalDiscount.DISCOUNT_12, discountForMoreThan75);
    }

    @Test
    void getDiscountMoreThan0Test(){
        // Arrange
        Map<Product, Integer> productsMoreThan0 = ProductGenerator.generateProducts(10.0);

        // Act
        double discountForMoreThan0 = masterCardDiscountCalculator.getDiscount(productsMoreThan0);

        // Assert
        assertEquals(DecimalDiscount.DISCOUNT_08, discountForMoreThan0);
    }
}
