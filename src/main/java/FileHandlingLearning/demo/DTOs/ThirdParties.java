package FileHandlingLearning.demo.DTOs;

import org.springframework.stereotype.Component;

@Component
public class ThirdParties {
    private String complaintName;
    private String complaintContactNumber;

    public ThirdParties() {
    }

    public ThirdParties(String complaintContactNumber, String complaintName) {
        this.complaintContactNumber = complaintContactNumber;
        this.complaintName = complaintName;
    }


    public String getComplaintContactNumber() {
        return complaintContactNumber;
    }

    public void setComplaintContactNumber(String complaintContactNumber) {
        this.complaintContactNumber = complaintContactNumber;
    }

    public String getComplaintName() {
        return complaintName;
    }

    public void setComplaintName(String complaintName) {
        this.complaintName = complaintName;
    }
}
