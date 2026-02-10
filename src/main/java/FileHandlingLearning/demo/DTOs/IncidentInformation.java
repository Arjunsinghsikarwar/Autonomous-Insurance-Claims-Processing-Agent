package FileHandlingLearning.demo.DTOs;

import java.time.LocalDate;
import java.time.LocalTime;

public class IncidentInformation {

    private LocalDate incidentOccurDate;
    private LocalTime incidentOccurTime;
    private String incidentOccurLocation;
    private String Description;

    public IncidentInformation() {
    }

    public IncidentInformation(String description, LocalDate incidentOccurDate, String incidentOccurLocation, LocalTime incidentOccurTime) {
        Description = description;
        this.incidentOccurDate = incidentOccurDate;
        this.incidentOccurLocation = incidentOccurLocation;
        this.incidentOccurTime = incidentOccurTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDate getIncidentOccurData() {
        return incidentOccurDate;
    }

    public void setIncidentOccurData(LocalDate incidentOccurDate) {
        this.incidentOccurDate = incidentOccurDate;
    }

    public String getIncidentOccuredLocation() {
        return incidentOccurLocation;
    }

    public void setIncidentOccuredLocation(String incidentOccurLocation) {
        this.incidentOccurLocation = incidentOccurLocation;
    }

    public LocalTime getIncidentOccurTime() {
        return incidentOccurTime;
    }

    public void setIncidentOccurTime(LocalTime incidentOccurTime) {
        this.incidentOccurTime = incidentOccurTime;
    }
}
