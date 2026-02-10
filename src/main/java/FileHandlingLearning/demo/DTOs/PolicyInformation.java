package FileHandlingLearning.demo.DTOs;

import java.time.LocalDate;

public class PolicyInformation {
    private String policyNumber;
    private String policyHolderName;
    private LocalDate policyStartDate;
    private LocalDate policyExpireDate;

    public PolicyInformation() {
    }

    public PolicyInformation(LocalDate policyExpireDate, String policyHolderName, String policyNumber, LocalDate policyStartDate) {
        this.policyExpireDate = policyExpireDate;
        this.policyHolderName = policyHolderName;
        this.policyNumber = policyNumber;
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyExpireDate() {
        return policyExpireDate;
    }

    public void setPolicyExpireDate(LocalDate policyExpireDate) {
        this.policyExpireDate = policyExpireDate;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public LocalDate getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }
}
