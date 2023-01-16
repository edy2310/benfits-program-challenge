package coe.unosquare.benefits.services.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import coe.unosquare.benefits.common.consts.DecimalDiscount;
import coe.unosquare.benefits.common.consts.PaymentType;
import coe.unosquare.benefits.common.exceptions.InvalidPaymentTypeException;
import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;
import coe.unosquare.benefits.services.discount_calculator.DiscountCalculator;
import coe.unosquare.benefits.services.discount_calculator.MasterCardDiscountCalculator;
import coe.unosquare.benefits.services.discount_calculator.VisaDiscountCalculator;
import coe.unosquare.benefits.services.subtotal_calculator.SubtotalCalculator;
import coe.unosquare.benefits.services.subtotal_calculator.SubtotalCalculatorImpl;
import coe.unosquare.benefits.util.ProductGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PaymentServiceTest {

    private static DiscountCalculator visaDiscountCalculator;

    private static DiscountCalculator masterCardDiscountCalculator;

    private static SubtotalCalculator subtotalCalculator;

    private static PaymentServiceImpl paymentService;

    private static Map<Product, Integer> products;

    private static double finalAmount;

    private static double finalSubtotal;


    @BeforeAll
    static void setUp(){
        visaDiscountCalculator = mock(VisaDiscountCalculator.class);
        masterCardDiscountCalculator = mock(MasterCardDiscountCalculator.class);
        subtotalCalculator = mock(SubtotalCalculatorImpl.class);
        paymentService = new PaymentServiceImpl(visaDiscountCalculator, masterCardDiscountCalculator, subtotalCalculator);

        products = ProductGenerator.generateProducts(15);
        finalSubtotal = 100;
        finalAmount = 90;
    }

    @Test
    public void payVisaTest() throws InvalidPaymentTypeException, InvalidProductException {
        // Arrange
        when(visaDiscountCalculator.getDiscount(anyMap())).thenReturn(DecimalDiscount.DISCOUNT_10);
        when(subtotalCalculator.getSubtotal(anyMap())).thenReturn(finalSubtotal);

        // Act
        double result = paymentService.pay(PaymentType.VISA, products);

        // Assert
        assertEquals(finalAmount, result);
    }

    @Test
    public void payMasterCardTest() throws InvalidPaymentTypeException, InvalidProductException {
        // Arrange
        when(masterCardDiscountCalculator.getDiscount(anyMap())).thenReturn(DecimalDiscount.DISCOUNT_10);
        when(subtotalCalculator.getSubtotal(anyMap())).thenReturn(finalSubtotal);

        // Act
        double result = paymentService.pay(PaymentType.MASTER_CARD, products);

        // Assert
        assertEquals(finalAmount, result);
    }

    @Test
    public void payThrowInvalidPaymentType(){
        // Assert
        assertThrows(InvalidPaymentTypeException.class, () -> {

            //Arrange
            String invalidPaymentType = "Invalid";

            // Act
            paymentService.pay(invalidPaymentType, products);
        });
    }
}
