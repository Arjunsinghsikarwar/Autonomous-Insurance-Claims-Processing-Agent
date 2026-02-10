package FileHandlingLearning.demo.DTOs;

import java.util.List;
import java.util.Map;

public class ClaimProcessingResponse {

        private Map<String, Object> extractedFields;
        private List<String> missingFields;
        private String recommendedRoute;
        private String reasoning;

    public ClaimProcessingResponse() {
    }

    public ClaimProcessingResponse(Map<String, Object> extractedFields, List<String> missingFields, String reasoning, String recommendedRoute) {
        this.extractedFields = extractedFields;
        this.missingFields = missingFields;
        this.reasoning = reasoning;
        this.recommendedRoute = recommendedRoute;
    }

    public Map<String, Object> getExtractedFields() {
        return extractedFields;
    }

    public void setExtractedFields(Map<String, Object> extractedFields) {
        this.extractedFields = extractedFields;
    }

    public List<String> getMissingFields() {
        return missingFields;
    }

    public void setMissingFields(List<String> missingFields) {
        this.missingFields = missingFields;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    public String getRecommendedRoute() {
        return recommendedRoute;
    }

    public void setRecommendedRoute(String recommendedRoute) {
        this.recommendedRoute = recommendedRoute;
    }
}


