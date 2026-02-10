package FileHandlingLearning.demo.Service;

import FileHandlingLearning.demo.DTOs.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class InsuranceFileHandlingService {

    private static final Logger log =
            LoggerFactory.getLogger(InsuranceFileHandlingService.class);

    @Value("${insurance.policy.validity-years}")
    private int policyValidityYears;

    @Value("${insurance.claim.fast-track-limit}")
    private int fastTrackLimit;

    @Value("${insurance.claim.injury-type}")
    private String injuryClaimType;

    @Value("${insurance.fraud.keywords}")
    private List<String> fraudKeywords;

    // ================= PDF READ =================
    public String readPDF(MultipartFile file) throws IOException {

        log.info("Reading uploaded PDF: name={}, size={}",
                file.getOriginalFilename(), file.getSize());

        if (file.isEmpty()) {
            log.warn("Uploaded PDF file is empty");
        }

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            log.error("PDF read failed", e);
            throw e;
        }
    }

    // ================= UTIL =================
    private String extractValue(String line) {
        if (!line.contains(":")) return null;
        return line.split(":", 2)[1].trim();
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    // ================= POLICY =================
    public PolicyInformation handlePolicyInformation(String text) {

        PolicyInformation policy = new PolicyInformation();

        for (String line : text.split("\n")) {
            line = line.trim();
            try {
                if (line.startsWith("Policy Number:")) {
                    policy.setPolicyNumber(extractValue(line));
                }
                else if (line.startsWith("Policyholder Name:")) {
                    policy.setPolicyHolderName(extractValue(line));
                }
                else if (line.startsWith("Policy Start Date:")) {
                    LocalDate start = LocalDate.parse(extractValue(line));
                    policy.setPolicyStartDate(start);
                    policy.setPolicyExpireDate(start.plusYears(policyValidityYears));
                }
            } catch (Exception e) {
                log.warn("Policy parsing failed for line: {}", line);
            }
        }
        return policy;
    }

    // ================= INCIDENT =================
    public IncidentInformation handleIncidentInformation(String text) {

        IncidentInformation incident = new IncidentInformation();

        for (String line : text.split("\n")) {
            line = line.trim();
            try {
                if (line.startsWith("Incident Occur Date:")) {
                    incident.setIncidentOccurData(LocalDate.parse(extractValue(line)));
                }
                else if (line.startsWith("Incident Occur Time:")) {
                    incident.setIncidentOccurTime(LocalTime.parse(extractValue(line)));
                }
                else if (line.startsWith("Incident Location:")) {
                    incident.setIncidentOccuredLocation(extractValue(line));
                }
                else if (line.startsWith("Description:")) {
                    incident.setDescription(extractValue(line));
                }
            } catch (Exception e) {
                log.warn("Incident parsing failed for line: {}", line);
            }
        }
        return incident;
    }

    // ================= INVOLVED PARTIES =================
    public InvolvedParties handleInvolvedParties(String text) {

        Complaint complaint = new Complaint();
        List<ThirdParties> thirdParties = new ArrayList<>();
        ThirdParties current = null;

        for (String line : text.split("\n")) {
            line = line.trim();
            try {
                if (line.startsWith("Claimant Name:")) {
                    complaint.setComplaintName(extractValue(line));
                }
                else if (line.startsWith("Claimant Contact:")) {
                    complaint.setComplaintContactNumber(extractValue(line));
                }
                else if (line.startsWith("Third Party Name:")) {
                    current = new ThirdParties();
                    current.setComplaintName(extractValue(line));
                }
                else if (line.startsWith("Third Party Contact:") && current != null) {
                    current.setComplaintContactNumber(extractValue(line));
                    thirdParties.add(current);
                    current = null;
                }
            } catch (Exception e) {
                log.warn("Involved party parsing failed for line: {}", line);
            }
        }

        return new InvolvedParties(complaint, thirdParties);
    }

    // ================= ASSIST =================
    public AssistDetails handleAssistDetails(String text) {

        AssistDetails assist = new AssistDetails();

        for (String line : text.split("\n")) {
            line = line.trim();
            try {
                if (line.startsWith("Asset Type:")) {
                    assist.setAssetType(extractValue(line));
                }
                else if (line.startsWith("Asset ID:")) {
                    assist.setAssesId(extractValue(line));
                }
                else if (line.startsWith("Estimated Damage:")) {
                    assist.setEstimatedDamage(
                            Integer.parseInt(extractValue(line))
                    );
                }
            } catch (Exception e) {
                log.warn("Assist parsing failed for line: {}", line);
            }
        }
        return assist;
    }

    // ================= MANDATORY =================
    public MandatoryFiled handleMandatoryFiled(String text) {

        MandatoryFiled mandatory = new MandatoryFiled();

        for (String line : text.split("\n")) {
            line = line.trim();
            try {
                if (line.startsWith("Claim Type:")) {
                    mandatory.setClaimType(extractValue(line));
                }
                else if (line.startsWith("Initial Estimate:")) {
                    mandatory.setInitialEstimate(
                            Integer.parseInt(extractValue(line))
                    );
                }
                else if (line.startsWith("Attachments:")) {
                    mandatory.setAttachments(extractValue(line));
                }
            } catch (Exception e) {
                log.warn("Mandatory parsing failed for line: {}", line);
            }
        }
        return mandatory;
    }

    // ================= FINAL PROCESS =================
    public ClaimProcessingResponse processClaim(String text) {

        log.info("Starting claim processing");

        PolicyInformation policy = handlePolicyInformation(text);
        IncidentInformation incident = handleIncidentInformation(text);
        InvolvedParties involved = handleInvolvedParties(text);
        AssistDetails assist = handleAssistDetails(text);
        MandatoryFiled mandatory = handleMandatoryFiled(text);

        Map<String, Object> extracted = new HashMap<>();
        extracted.put("policyInformation", policy);
        extracted.put("incidentInformation", incident);
        extracted.put("involvedParties", involved);
        extracted.put("assistDetails", assist);
        extracted.put("mandatoryFields", mandatory);

        List<String> missing = new ArrayList<>();

        if (isBlank(policy.getPolicyNumber())) missing.add("Policy Number");
        if (isBlank(policy.getPolicyHolderName())) missing.add("Policyholder Name");
        if (policy.getPolicyStartDate() == null) missing.add("Policy Start Date");

        if (isBlank(mandatory.getClaimType())) missing.add("Claim Type");
        if (mandatory.getInitialEstimate() == null) missing.add("Initial Estimate");
        if (isBlank(mandatory.getAttachments())) missing.add("Attachments");

        if (isBlank(assist.getAssetType())) missing.add("Asset Type");
        if (isBlank(assist.getAssesId())) missing.add("Asset ID");
        if (assist.getEstimatedDamage() == null) missing.add("Estimated Damage");

        if (incident.getIncidentOccurData() == null) missing.add("Incident Date");
        if (incident.getIncidentOccurTime() == null) missing.add("Incident Time");
        if (isBlank(incident.getIncidentOccuredLocation())) missing.add("Incident Location");
        if (isBlank(incident.getDescription())) missing.add("Incident Description");

        ClaimProcessingResponse response = new ClaimProcessingResponse();
        response.setExtractedFields(extracted);
        response.setMissingFields(missing);

        log.info("Missing fields: {}", missing);

        // ---------- ROUTING ----------
        if (policy.getPolicyExpireDate() != null &&
                LocalDate.now().isAfter(policy.getPolicyExpireDate())) {

            response.setRecommendedRoute("REJECTED");
            response.setReasoning("Policy has expired.");
            return response;
        }

        if (!missing.isEmpty()) {
            response.setRecommendedRoute("MANUAL_REVIEW");
            response.setReasoning("Missing mandatory fields: " + missing);
            return response;
        }

        if (incident.getDescription() != null) {
            for (String keyword : fraudKeywords) {
                if (incident.getDescription()
                        .toLowerCase()
                        .contains(keyword.toLowerCase())) {

                    response.setRecommendedRoute("INVESTIGATION");
                    response.setReasoning("Suspicious keywords found in description.");
                    return response;
                }
            }
        }

        if (injuryClaimType.equalsIgnoreCase(mandatory.getClaimType())) {
            response.setRecommendedRoute("SPECIALIST_QUEUE");
            response.setReasoning("Injury claim requires specialist handling.");
            return response;
        }

        if (assist.getEstimatedDamage() != null &&
                assist.getEstimatedDamage() < fastTrackLimit) {

            response.setRecommendedRoute("FAST_TRACK");
            response.setReasoning("Low estimated damage.");
            return response;
        }

        response.setRecommendedRoute("STANDARD_REVIEW");
        response.setReasoning("Claim passed all checks.");
        return response;
    }
}
