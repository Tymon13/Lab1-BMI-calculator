package pl.edu.pwr.lab1.i238162;

import android.content.Context;

public class MetricBmiCalculator implements IBmiCalculator {
    @Override
    public double calculate(double massInKg, double heightInCm) {
        double heightInM = heightInCm / 100;
        return massInKg / (heightInM * heightInM);
    }

    @Override
    public String getMassUnit(Context c) {
        return c.getString(R.string.mass_unit_metric);
    }

    @Override
    public String getHeightUnit(Context c) {
        return c.getString(R.string.height_unit_metric);
    }
}
