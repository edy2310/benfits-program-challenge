package coe.unosquare.benefits.services.discount_calculator;

import coe.unosquare.benefits.common.consts.DecimalDiscount;
import coe.unosquare.benefits.models.Product;

import java.util.Map;

/**
 * The Visa Payment service implementation
 */
public class VisaDiscountCalculator implements DiscountCalculator{

    /**
     * Gets the discount for Visa payment type
     *
     * @param products to calculate the discount
     * @return the decimal discount as a double for Visa type
     */
    public double getDiscount(Map<Product, Integer> products){
        double discount;

        int productCount = products.values()
                .stream()
                .reduce(0, (totalProductCount, quantity) -> totalProductCount += quantity);

        if (productCount >= 10) {
            discount = DecimalDiscount.DISCOUNT_15;
        } else if (productCount >= 7) {
            discount = DecimalDiscount.DISCOUNT_10;
        } else {
            discount = DecimalDiscount.DISCOUNT_05;
        }

        return discount;
    }
}
