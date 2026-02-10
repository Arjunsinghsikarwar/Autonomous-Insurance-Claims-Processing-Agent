package FileHandlingLearning.demo.DTOs;

public class MandatoryFiled {

    private String claimType;
    private Integer initialEstimate;
    private String attachments;

    public MandatoryFiled(String claimType, Integer initialEstimate , String attachments) {
        this.claimType = claimType;
        this.initialEstimate = initialEstimate;
        this.attachments = attachments;
    }

    public MandatoryFiled() {
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Integer getInitialEstimate() {
        return initialEstimate;
    }

    public void setInitialEstimate(Integer initialEstimate) {
        this.initialEstimate = initialEstimate;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }
}
