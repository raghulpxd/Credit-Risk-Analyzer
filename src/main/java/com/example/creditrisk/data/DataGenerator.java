
package com.example.creditrisk.data;

import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.FastVector;
import java.util.ArrayList;
import java.util.Random;

public class DataGenerator {
    public static Instances generateData(int n) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("age"));
        attributes.add(new Attribute("income"));
        attributes.add(new Attribute("loan_amount"));
        attributes.add(new Attribute("term"));
        attributes.add(new Attribute("credit_score"));

        ArrayList<String> classVals = new ArrayList<>();
        classVals.add("no_default");
        classVals.add("default");
        attributes.add(new Attribute("default", classVals));

        Instances data = new Instances("LoanApplications", attributes, n);
        data.setClassIndex(data.numAttributes() - 1);

        Random rand = new Random(42);
        for (int i = 0; i < n; i++) {
            double age = 18 + rand.nextInt(57);
            double income = Math.max(5000, rand.nextGaussian()*20000 + 50000);
            double loan = Math.max(500, rand.nextGaussian()*5000 + 10000);
            double term = new double[]{12,24,36,48,60}[rand.nextInt(5)];
            double score = Math.min(850, Math.max(300, rand.nextGaussian()*70 + 650));

            double lti = loan / income;
            double riskProb = 0.3*(700 - score)/400 + 0.7*(lti);

            DenseInstance instance = new DenseInstance(data.numAttributes());
            instance.setValue(attributes.get(0), age);
            instance.setValue(attributes.get(1), income);
            instance.setValue(attributes.get(2), loan);
            instance.setValue(attributes.get(3), term);
            instance.setValue(attributes.get(4), score);
            instance.setValue(attributes.get(5), rand.nextDouble() < riskProb ? "default" : "no_default");

            data.add(instance);
        }
        return data;
    }
}

