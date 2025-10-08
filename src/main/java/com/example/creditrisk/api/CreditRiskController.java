package com.example.creditrisk.api;

import com.example.creditrisk.model.Predictor;
import com.example.creditrisk.data.DataGenerator;
import org.springframework.web.bind.annotation.*;

import weka.core.Instances;

@RestController
@RequestMapping("/api")
public class CreditRiskController {

    private Predictor predictor;

    public CreditRiskController() throws Exception {
        Instances template = DataGenerator.generateData(1);
        predictor = new Predictor("credit_model.model", template);
    }

    @PostMapping("/score")
    public double score(@RequestBody LoanApplication app) throws Exception {
        return predictor.predictDefault(app.age, app.income, app.loanAmount, app.term, app.creditScore);
    }

    static class LoanApplication {
        public double age;
        public double income;
        public double loanAmount;
        public double term;
        public double creditScore;
    }
}
