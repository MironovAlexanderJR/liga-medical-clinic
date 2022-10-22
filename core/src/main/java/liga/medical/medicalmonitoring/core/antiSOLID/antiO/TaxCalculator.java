package liga.medical.medicalmonitoring.core.antiSOLID.antiO;

public class TaxCalculator {

    public double calculate(double income, double deduction, String country) {

        double taxAmount = 0;
        double taxableIncome = income - deduction;

        switch (country) {
            case ("Russia"):
                // TODO
                break;
            case ("Turkmenistan"):
                // TODO
                break;
            case ("USA"):
                // TODO
                break;
        }

        return taxAmount;
    }
}
