package pl.edu.pwr.lab1.i238162;

public class MetricBmiCalculator implements IBmiCalculator {
    @Override
    public double calculate(double massInKg, double heightInCm) {
        double heightInM = heightInCm / 100;
        return massInKg / (heightInM * heightInM);
    }
}
