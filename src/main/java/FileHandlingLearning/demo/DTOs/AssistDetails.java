package FileHandlingLearning.demo.DTOs;

public class AssistDetails {
    private String assetType;
    private String assesId;
    private Integer estimatedDamage;

    public AssistDetails() {
    }

    public AssistDetails(String assesId, String assetType, Integer estimatedDamage) {
        this.assesId = assesId;
        this.assetType = assetType;
        this.estimatedDamage = estimatedDamage;
    }

    public String getAssesId() {
        return assesId;
    }

    public void setAssesId(String assesId) {
        this.assesId = assesId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Integer getEstimatedDamage() {
        return estimatedDamage;
    }

    public void setEstimatedDamage(Integer estimatedDamage) {
        this.estimatedDamage = estimatedDamage;
    }
}
