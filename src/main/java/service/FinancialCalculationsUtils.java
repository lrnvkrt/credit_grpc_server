package service;

import data.model.FinancialModel;
import org.example.credit_assessment.Decision;
import org.example.credit_assessment.EmploymentStatus;
import org.example.credit_assessment.Purpose;

import java.util.List;

public class FinancialCalculationsUtils {
    public static double calculateDSR(FinancialModel model) {
        return (model.getTotalMonthlyDebtPayment() / (model.getAnnualIncome() / 12)) * 100;
    }

    public static double calculateKFN(FinancialModel model) {
        double loanAmount = model.getAmount();
        double term = model.getTerm();
        double termInYears = term / 12;
        double annualIncome = model.getAnnualIncome();
        return (loanAmount * termInYears) / annualIncome;
    }

    public static double calculateAgeFactor(FinancialModel model) {
        int age = model.getAge();
        if (age <= 25) {
            return 0.8;
        } else if (age <= 45) {
            return 1.0;
        } else if (age <= 60) {
            return 0.9;
        } else {
            return 0.7;
        }
    }

    public static double calculateEmploymentStatusCoefficient(FinancialModel model) {
        EmploymentStatus status = model.getEmploymentStatus();
        return switch (status) {
            case EMPLOYED -> 1.0;
            case SELF_EMPLOYED -> 0.9;
            case UNEMPLOYED -> 0.5;
            default -> 0.0;
        };
    }

    public static double calculatePurposeCoefficient(FinancialModel model) {
        Purpose purpose = model.getPurpose();
        return switch (purpose) {
            case MORTGAGE -> 1.2; // Низкий риск
            case AUTO -> 1.1; // Средний риск
            case CONSUMER -> 0.9; // Высокий риск
            case BUSINESS -> 0.8; // Очень высокий риск
            case EDUCATION -> 0.85; // Высокий риск
            case MEDICAL -> 1.0; // Нейтральный риск
            case REFINANCE -> 1.3; // Очень низкий риск
            case SECURED -> 1.2; // Низкий риск
            case UNSECURED -> 0.7; // Высокий риск
            case OVERDRAFT -> 0.6; // Очень высокий риск
            default -> 0.0;
        };
    }


    public static double calculateCreditScore(FinancialModel model, List<FinancialModel> pastApplications) {
        double dsr = calculateDSR(model);
        double penalty_dsr = dsr > 100 ? 1-(dsr*0.01) : 1/(1+dsr);
        double kfn = calculateKFN(model);
        double penalty_kfn = 0.4-kfn;
        double ageFactor = calculateAgeFactor(model);
        double employmentStatusCoefficient = calculateEmploymentStatusCoefficient(model);
        double approvalRate = calculateApprovalRate(pastApplications);
        double purposeCoefficient = calculatePurposeCoefficient(model);

        return (penalty_dsr * 0.8) + (penalty_kfn * 1.1) +
                (ageFactor * 0.5) + (employmentStatusCoefficient * 0.7) +
                (approvalRate*0.6) + (purposeCoefficient * 0.7);
    }

    public static Decision makeDecision(FinancialModel model, List<FinancialModel> pastApplications) {
        double creditScore = calculateCreditScore(model, pastApplications);
        if (creditScore > 30) {
            return Decision.APPROVED;
        } else {
            return Decision.REJECTED;
        }
    }

    public static double calculateApprovalRate(List<FinancialModel> pastApplications) {
        if (pastApplications.isEmpty()) {
            // Средний коэффициент для клиентов без заявок
            return 50.0; // 50% - условный коэффициент для новых клиентов
        }

        int approvedApplications = 0;
        int totalApplications = pastApplications.size();

        for (FinancialModel model : pastApplications) {
            if (model.getDecision() == Decision.APPROVED) {
                approvedApplications++;
            }
        }

        return ((double) approvedApplications / totalApplications) * 100;
    }
}
