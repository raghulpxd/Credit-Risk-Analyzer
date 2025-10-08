package com.example.creditrisk;

import com.example.creditrisk.data.DataGenerator;
import com.example.creditrisk.model.ModelTrainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.Instances;

@SpringBootApplication
public class App {

    public static void main(String[] args) throws Exception {

        // Check if CLI args are passed
        if (args.length > 0) {
            String cmd = args[0];

            switch (cmd) {
                case "train":
                    System.out.println("Generating synthetic data...");
                    Instances data = DataGenerator.generateData(5000);
                    System.out.println("Training model...");
                    ModelTrainer.trainAndSaveModel(data, "credit_model.model");
                    System.out.println("Model training completed!");
                    return;

                case "run-api":
                    System.out.println("Starting Spring Boot API...");
                    SpringApplication.run(App.class, args);
                    return;

                default:
                    System.out.println("Unknown command. Use: train | run-api");
                    return;
            }
        }

        // Default: start API if no args
        SpringApplication.run(App.class, args);
    }
}
