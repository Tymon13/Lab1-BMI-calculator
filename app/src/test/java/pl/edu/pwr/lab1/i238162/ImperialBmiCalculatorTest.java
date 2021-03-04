package pl.edu.pwr.lab1.i238162;

import junit.framework.TestCase;

import org.junit.Assert;

public class ImperialBmiCalculatorTest extends TestCase {
    public void testValidValueExactly() {
        ImperialBmiCalculator uut = new ImperialBmiCalculator();
        double bmi = uut.calculate(150, 70);
        Assert.assertEquals(21.52, bmi, 0.001);
    }

    public void testExtremeValidValues() {
        ImperialBmiCalculator uut = new ImperialBmiCalculator();
        double lowestBmi = uut.calculate(90, 83);
        Assert.assertEquals(9, lowestBmi, 1);

        double highestBmi = uut.calculate(290, 56);
        Assert.assertEquals(65, highestBmi, 1);
    }

    public void testNoCrashWhenZeroHeightIsEntered() {
        ImperialBmiCalculator uut = new ImperialBmiCalculator();
        uut.calculate(13, 0);
    }
}

