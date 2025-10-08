package com.example.creditrisk.model;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class Predictor {
    private Classifier model;
    private Instances datasetTemplate;

    public Predictor(String modelPath, Instances template) throws Exception {
        this.model = (Classifier) SerializationHelper.read(modelPath);
        this.datasetTemplate = template;
    }

    public double predictDefault(double age, double income, double loan, double term, double score) throws Exception {
        DenseInstance instance = new DenseInstance(datasetTemplate.numAttributes());
        instance.setDataset(datasetTemplate);
        instance.setValue(0, age);
        instance.setValue(1, income);
        instance.setValue(2, loan);
        instance.setValue(3, term);
        instance.setValue(4, score);
        double pred = model.classifyInstance(instance);
        return pred;
    }
}
