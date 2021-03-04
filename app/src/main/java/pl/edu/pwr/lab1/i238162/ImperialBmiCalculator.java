package pl.edu.pwr.lab1.i238162;

public class ImperialBmiCalculator implements IBmiCalculator {
    @Override
    public double calculate(double massInLb, double heightInInch) {
        return 703 * massInLb / (heightInInch * heightInInch);
    }
}
