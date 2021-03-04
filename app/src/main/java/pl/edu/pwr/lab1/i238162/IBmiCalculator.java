package pl.edu.pwr.lab1.i238162;

import android.content.Context;

public interface IBmiCalculator {
    double calculate(double mass, double height);
    String getMassUnit(Context c);
    String getHeightUnit(Context c);
}
