package com.example.crimson.crimson.Controller.Benefit;

public class BenefitSilverDecorator extends BenefitDecorator {

    public BenefitSilverDecorator(BenefitBase base)
    {
        super(base);
    }

    @Override
    public String generateCoupon()
    {
        return "Movie-2"+","+generateCouponAPI.generateCoupon();
    }
}
