package data.model;

import org.example.credit_assessment.CreditCheckRequest;
import org.example.credit_assessment.Decision;
import org.example.credit_assessment.EmploymentStatus;
import org.example.credit_assessment.Purpose;

import java.time.LocalDateTime;

public class FinancialModel {
    private String cif;
    private double totalMonthlyDebtPayment;
    private double annualIncome;
    private int term;
    private int age;
    private EmploymentStatus employmentStatus;
    private String referenceId;
    private double amount;
    private Purpose purpose;
    private Decision decision;
    private LocalDateTime createdAt;

    public FinancialModel(CreditCheckRequest request) {
        this.cif = request.getCif();
        this.totalMonthlyDebtPayment = request.getTotalMonthlyDebtPayment();
        this.annualIncome = request.getAnnualIncome();
        this.term = request.getTerm();
        this.age = request.getAge();
        this.employmentStatus = request.getEmploymentStatus();
        this.referenceId = request.getReferenceId();
        this.amount = request.getAmount();
        this.purpose = request.getPurpose();
    }

    public FinancialModel(String cif, double totalMonthlyDebtPayment, double annualIncome, int term, int age, EmploymentStatus employmentStatus, String referenceId, double amount, Purpose purpose, Decision decision, LocalDateTime createdAt) {
        this.cif = cif;
        this.totalMonthlyDebtPayment = totalMonthlyDebtPayment;
        this.annualIncome = annualIncome;
        this.term = term;
        this.age = age;
        this.employmentStatus = employmentStatus;
        this.referenceId = referenceId;
        this.amount = amount;
        this.purpose = purpose;
        this.decision = decision;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public double getTotalMonthlyDebtPayment() {
        return totalMonthlyDebtPayment;
    }

    public void setTotalMonthlyDebtPayment(double totalMonthlyDebtPayment) {
        this.totalMonthlyDebtPayment = totalMonthlyDebtPayment;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
}


