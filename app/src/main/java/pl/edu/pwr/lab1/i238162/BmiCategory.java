package pl.edu.pwr.lab1.i238162;

import android.content.Context;
import android.content.res.Resources;

public enum BmiCategory {
    VERY_SEVERELY_UNDERWEIGHT,
    SEVERELY_UNDERWEIGHT,
    UNDERWEIGHT,
    NORMAL,
    OVERWEIGHT,
    MODERATELY_OBESE,
    SEVERELY_OBESE,
    VERY_SEVERELY_OBESE;

    public String getLabel(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(this.name(), "string", context.getPackageName());
        return res.getString(resId);
    }

    public int getColour(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(this.name(), "color", context.getPackageName());
        return res.getColor(resId, null);
    }

    public static BmiCategory valueOfBmi(double bmi) {
        if (bmi < 15) {
            return VERY_SEVERELY_UNDERWEIGHT;
        } else if (bmi < 16) {
            return SEVERELY_UNDERWEIGHT;
        } else if (bmi < 18.5) {
            return UNDERWEIGHT;
        } else if (bmi < 25) {
            return NORMAL;
        } else if (bmi < 30) {
            return OVERWEIGHT;
        } else if (bmi < 35) {
            return MODERATELY_OBESE;
        } else if (bmi < 40) {
            return SEVERELY_OBESE;
        } else {
            return VERY_SEVERELY_OBESE;
        }
    }
}
