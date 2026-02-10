package FileHandlingLearning.demo.Controller;

import FileHandlingLearning.demo.DTOs.ClaimProcessingResponse;
import FileHandlingLearning.demo.Service.InsuranceFileHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.*;

@RestController
public class InsuranceFileHandling {

    private static final Logger log =
            LoggerFactory.getLogger(InsuranceFileHandling.class);

    @Autowired
    private InsuranceFileHandlingService insuranceFileHandlingService;

    @PostMapping("/upload")
    public ResponseEntity<ClaimProcessingResponse> insurancePDFHandling(
            @RequestParam("file") MultipartFile file) throws IOException {


        log.info("Received insurance claim upload request");

        ClaimProcessingResponse response =
                insuranceFileHandlingService.processClaim(
                        insuranceFileHandlingService.readPDF(file)
                );

        log.info("Claim processing completed with route={}",
                response.getRecommendedRoute());

        return ResponseEntity.ok(response);
    }
}

