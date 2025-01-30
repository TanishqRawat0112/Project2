package com.employee;

import com.employee.SentimentAnalyzer;

public class Main {
    public static void main(String[] args) {
        //Driver Code to test SentimentAnalyzer Class:
        System.out.println("------ Start of Program ------");

        String review = "Ambiance was not good enough. Cool service. Haven't been here in years! Fantastic service and the food was delicious! Definitely will be a frequent flyover! Francisco was very attentive";

        String[][] featureSet = {
                {"ambiance","ambience","atmosphere","decor"},
                {"dessert","ice cream","desert"},
                {"food"},
                {"soup"},
                {"service","management","waiter","waitress","bartender","staff","server"}
        };

        String[] posOpinionWords={"good","fantastic","friendly","great","excellent","amazing","awesome","delicious"};
        String[] negOpinionWords = {"slow","bad","horrible","awful","unprofessional","poor"};


        int[] featureSetAnalysis = SentimentAnalyzer.detectProsAndCons(review,featureSet,posOpinionWords,negOpinionWords);

        System.out.println("The feature Set Analysis is : ");
        for(int i=0;i<featureSetAnalysis.length;i++){
            System.out.println(featureSetAnalysis[i]);
        }

        System.out.println("------ End of Program ------");
    }
}