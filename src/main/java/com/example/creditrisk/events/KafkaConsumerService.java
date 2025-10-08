package com.example.creditrisk.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.creditrisk.api.CreditRiskController.LoanApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {
    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "loan-applications", groupId = "credit-group")
    public void consume(String message) throws Exception {
        LoanApplication app = mapper.readValue(message, LoanApplication.class);
        System.out.println("Received application: " + app.age + ", score predicted here...");
        // Call predictor.predictDefault(...) here
    }
}
