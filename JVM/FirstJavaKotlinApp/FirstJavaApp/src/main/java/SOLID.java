public class SOLID {

    public static void main(String[] args) {
        SalaryCalculator hrSalaryCalculator = new HRSalaryAndBonusCalculator(new HRSalaryCalculator(), new BonusCalculator());
        SalaryCalculator engSalaryCalculator = new EngSalaryAndBonusCalculator(new EngSalaryCalculator(), new BonusCalculator());


    }

    public interface SalaryCalculator {
        public double calculate();
    }

    public static class BonusCalculator implements SalaryCalculator {

        @Override
        public double calculate() {
            return 1.2;
        }
    }

    public static class HRSalaryCalculator implements SalaryCalculator {

        @Override
        public double calculate() {
            return 1000;
        }
    }

    public static class EngSalaryCalculator implements SalaryCalculator {

        @Override
        public double calculate() {
            return 2000;
        }
    }

    public static class HRSalaryAndBonusCalculator implements SalaryCalculator {
        SalaryCalculator salaryCalculator;
        SalaryCalculator bonusCalculator;


        public HRSalaryAndBonusCalculator(SalaryCalculator salaryCalculator, SalaryCalculator bonusCalculator) {
            this.salaryCalculator = salaryCalculator;
            this.bonusCalculator = bonusCalculator;
        }

        @Override
        public double calculate() {
            return salaryCalculator.calculate() * bonusCalculator.calculate();
        }
    }

    public static class EngSalaryAndBonusCalculator implements SalaryCalculator {
        SalaryCalculator salaryCalculator;
        SalaryCalculator bonusCalculator;


        public EngSalaryAndBonusCalculator(SalaryCalculator salaryCalculator, SalaryCalculator bonusCalculator) {
            this.salaryCalculator = salaryCalculator;
            this.bonusCalculator = bonusCalculator;
        }

        @Override
        public double calculate() {
            return salaryCalculator.calculate() * bonusCalculator.calculate();
        }
    }
}
