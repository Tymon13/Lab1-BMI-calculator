package pl.edu.pwr.lab1.i238162;

import android.content.Context;

public class ImperialBmiCalculator implements IBmiCalculator {
    @Override
    public double calculate(double massInLb, double heightInInch) {
        return 703 * massInLb / (heightInInch * heightInInch);
    }

    @Override
    public String getMassUnit(Context c) {
        return c.getString(R.string.mass_unit_imperial);
    }

    @Override
    public String getHeightUnit(Context c) {
        return c.getString(R.string.height_unit_imperial);
    }
}
