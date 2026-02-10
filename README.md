Overview

This project is a Spring Boot REST application that accepts an insurance claim PDF, extracts required fields using Apache PDFBox, validates mandatory information, and determines the claim processing route based on configurable business rules.
The system is designed to:
Read structured insurance PDFs
Extract policy, incident, assist, and claimant details
Detect missing mandatory fields
Apply rule-based decision logic
Return a structured JSON response

Tech Stack :
Java 21
Spring Boot
Apache PDFBox
SLF4J + Logback (logging)
Maven

Features : 
Upload insurance PDF via REST API
Text extraction using Apache PDFBox
Modular field extraction logic
Config-driven business rules
Missing field detection
Claim routing logic
Structured JSON response
Application-level logging

API Endpoint: 
Upload Insurance PDF
POST /upload
Request


Response
Extracted claim data
Missing mandatory fields (if any)

Request

multipart/form-data

Key: file (PDF)

Response

Extracted claim data

Missing mandatory fields (if any)
Recommended claim route

Reasoning :
Policy Number:
Policyholder Name:
Policy Start Date:
Incident Occur Date:
Incident Occur Time:
Incident Location:
Description:
Claim Type:
Initial Estimate:
Attachments:
Asset Type:
Asset ID:
Estimated Damage:

Claim Routing Rules : 
Expired policy → REJECTED
Missing mandatory fields → MANUAL_REVIEW
Fraud keywords found → INVESTIGATION
Injury claim → SPECIALIST_QUEUE
Low estimated damage → FAST_TRACK
Otherwise → STANDARD_REVIEW

Configuration (application.properties)
insurance.policy.validity-years=2
insurance.claim.fast-track-limit=25000
insurance.claim.injury-type=injury
insurance.fraud.keywords=fraud,staged,inconsistent

Logging
Uses Spring Boot default SLF4J + Logback logging for request flow and processing steps.

Recommended claim route

Reasoning
