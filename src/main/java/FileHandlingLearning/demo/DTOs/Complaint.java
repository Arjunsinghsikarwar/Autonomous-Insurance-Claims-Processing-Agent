package FileHandlingLearning.demo.DTOs;


public class Complaint {

    private String complaintName;
    private String complaintContactNumber;

    public Complaint() {
    }

    public Complaint(String complaintContactNumber, String complaintName) {
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
