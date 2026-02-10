package FileHandlingLearning.demo.DTOs;

import java.util.List;

public class InvolvedParties {

    private Complaint complaintInfo;
    private List<ThirdParties> thirdPartiesList;

    public InvolvedParties() {}

    public InvolvedParties(Complaint complaintInfo, List<ThirdParties> thirdPartiesList) {
        this.complaintInfo = complaintInfo;
        this.thirdPartiesList = thirdPartiesList;
    }

    public Complaint getComplaintInfo() {
        return complaintInfo;
    }

    public void setComplaintInfo(Complaint complaintInfo) {
        this.complaintInfo = complaintInfo;
    }

    public List<ThirdParties> getThirdPartiesList() {
        return thirdPartiesList;
    }

    public void setThirdPartiesList(List<ThirdParties> thirdPartiesList) {
        this.thirdPartiesList = thirdPartiesList;
    }
}
