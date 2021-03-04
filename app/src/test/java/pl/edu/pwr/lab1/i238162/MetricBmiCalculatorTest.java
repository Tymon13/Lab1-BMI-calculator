package pl.edu.pwr.lab1.i238162;

import junit.framework.TestCase;

import org.junit.Assert;

public class MetricBmiCalculatorTest extends TestCase {
    public void testValidValueExactly() {
        MetricBmiCalculator uut = new MetricBmiCalculator();
        double bmi = uut.calculate(100, 200);
        Assert.assertEquals(25, bmi, 0.001);
    }

    public void testExtremeValidValues() {
        MetricBmiCalculator uut = new MetricBmiCalculator();
        double lowestBmi = uut.calculate(41, 210.8);
        Assert.assertEquals(9, lowestBmi, 1);

        double highestBmi = uut.calculate(132, 142.2);
        Assert.assertEquals(65, highestBmi, 1);
    }

    public void testNoCrashWhenZeroHeightIsEntered()
    {
        MetricBmiCalculator uut = new MetricBmiCalculator();
        uut.calculate(13, 0);
    }
}