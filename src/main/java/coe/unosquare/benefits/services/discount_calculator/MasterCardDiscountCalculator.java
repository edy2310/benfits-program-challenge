package coe.unosquare.benefits.services.discount_calculator;

import coe.unosquare.benefits.common.consts.DecimalDiscount;
import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The MasterCard Payment service implementation
 */
public class MasterCardDiscountCalculator implements DiscountCalculator{

    /**
     * Gets the discount for Master Card payment type
     *
     * @param products to calculate the discount
     * @return the decimal discount as a double for Master Card type
     */
    public double getDiscount(Map<Product, Integer> products){
        double discount;

        double totalAmount = products.entrySet()
                .stream()
                .mapToDouble(product -> product.getKey().getPrice() * product.getValue())
                .sum();

        if (totalAmount >= 100) {
            discount = DecimalDiscount.DISCOUNT_17;
        } else if (totalAmount >= 75) {
            discount = DecimalDiscount.DISCOUNT_12;
        } else {
            discount = DecimalDiscount.DISCOUNT_08;
        }
        return discount;
    }
}
