package com.example.crimson.crimson.Controller.Dues;

public class BenefitGoldDecorator extends BenefitDecorator {

    public BenefitGoldDecorator(BenefitBase base)
    {
        super(base);
    }

    @Override
    public String generateCoupon()
    {
        return "Dinner-2"+","+generateCouponAPI.generateCoupon();
    }
}