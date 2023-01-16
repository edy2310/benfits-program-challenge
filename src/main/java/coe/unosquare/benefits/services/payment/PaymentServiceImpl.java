package coe.unosquare.benefits.services.payment;

import coe.unosquare.benefits.common.consts.PaymentType;
import coe.unosquare.benefits.common.exceptions.InvalidPaymentTypeException;
import coe.unosquare.benefits.common.exceptions.InvalidProductException;
import coe.unosquare.benefits.models.Product;
import coe.unosquare.benefits.services.discount_calculator.DiscountCalculator;
import coe.unosquare.benefits.services.discount_calculator.MasterCardDiscountCalculator;
import coe.unosquare.benefits.services.discount_calculator.VisaDiscountCalculator;
import coe.unosquare.benefits.services.subtotal_calculator.SubtotalCalculator;
import coe.unosquare.benefits.services.subtotal_calculator.SubtotalCalculatorImpl;

import java.util.Map;

/**
 * The Payment service implementation
 */
public class PaymentServiceImpl implements PaymentService {

    /** The Visa discount calculator. **/
    private final DiscountCalculator visaDiscountCalculator;

    /** The Master Card discount calculator. **/
    private final DiscountCalculator masterCardDiscountCalculator;

    /** The Subtotal calculator. **/
    private final SubtotalCalculator subtotalCalculator;

    /**
     * Instantiates a new Payment Service.
     */
    public PaymentServiceImpl(){
        this.visaDiscountCalculator = new VisaDiscountCalculator();
        this.masterCardDiscountCalculator = new MasterCardDiscountCalculator();
        this.subtotalCalculator = new SubtotalCalculatorImpl();
    }

    /**
     * Instantiates a new Payment Service.
     *
     * @param visaDiscountCalculator  the Visa discount calculator
     * @param masterCardDiscountCalculator the Master Card discount calculator
     * @param subtotalCalculator  the Subtotal calculator
     */
    public PaymentServiceImpl(
            DiscountCalculator visaDiscountCalculator,
            DiscountCalculator masterCardDiscountCalculator,
            SubtotalCalculator subtotalCalculator){
        this.visaDiscountCalculator = visaDiscountCalculator;
        this.masterCardDiscountCalculator = masterCardDiscountCalculator;
        this.subtotalCalculator = subtotalCalculator;
    }


    /**
     * Calculates the total of an order after the discount
     *
     * @param paymentType the payment type
     * @param products the products in the order
     * @return the total as a double
     */
    public Double pay(String paymentType, Map<Product, Integer> products)
            throws InvalidPaymentTypeException, InvalidProductException {

        double discount;

        if (paymentType.equals(PaymentType.VISA)) {
            discount = visaDiscountCalculator.getDiscount(products);
        } else if (paymentType.equals(PaymentType.MASTER_CARD)) {
            discount = masterCardDiscountCalculator.getDiscount(products);
        } else {
            throw new InvalidPaymentTypeException("Invalid payment type: " + paymentType);
        }

        double subtotal = subtotalCalculator.getSubtotal(products);
        return subtotal - subtotal * discount;
    }
}
