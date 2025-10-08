package com.example.creditrisk.model;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class ModelTrainer {
    public static void trainAndSaveModel(Instances data, String path) throws Exception {
        RandomForest model = new RandomForest();
        model.buildClassifier(data);
        SerializationHelper.write(path, model);
        System.out.println("Model saved to " + path);
    }
}
