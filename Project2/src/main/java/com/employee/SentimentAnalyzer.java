package com.employee;

public class SentimentAnalyzer {
    public static int[] detectProsAndCons(
            String originalReview,
            String[][] featureSet,
            String[] posOpinionWords,
            String[] negOpinionWords
    ) {
        int[] featureOpinions = new int[featureSet.length];
        for (int i = 0; i < featureOpinions.length; i++) {
            featureOpinions[i] = 0;
        }

        String review = originalReview.toLowerCase();
        System.out.println("New review : "+review);

        for(int i=0;i<featureSet.length;i++) {
            //Check if sentiment analysis is done for that category of feature.
            //If not done, Calculate the opinion for the feature:
            for(int j=0;j<featureSet[i].length;j++) {
                if(featureOpinions[i]!=0)break;
                if(review.contains(featureSet[i][j])) {
                    System.out.println("Review has feature : "+featureSet[i][j]);
                    featureOpinions[i] = getOpinionOnFeature(review,featureSet[i][j],posOpinionWords,negOpinionWords);
                }
            }
        }


        return featureOpinions;
    }

    private static int getOpinionOnFeature(
            String review,
            String feature,
            String[] posOpinionWords,
            String[] negOpinionWords
    ) {
        int opinion = 0;
        //Do implementation:
        if(review.contains(feature+" was ")) {
            System.out.println("Review has : "+feature+" was ");
            opinion = checkForWasPattern(review,feature,posOpinionWords,negOpinionWords);
        }
        if(opinion==0) {
            opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);
        }

        return opinion;
    }

    private static int checkForWasPattern(
            String review,
            String feature,
            String[] posOpinionWords,
            String[] negOpinionWords
    ){
        String pattern = feature + " was ";
        //implementation:
        int index = 0;
        int startIndex = 0;
        while(startIndex < review.length()) {
            index = review.indexOf(pattern, startIndex);
//            System.out.println("Index of "+pattern+" : "+index);
            if(index == -1){
                break;
            }
            startIndex = index+pattern.length();
//            System.out.println("StartIndex : "+startIndex);
//            int opinionIndex = 0;
            for(int i=0; i<posOpinionWords.length; i++) {
                if(review.indexOf(posOpinionWords[i], startIndex) == startIndex) {
                    return 1;
                }
            }
            for(int i=0; i<negOpinionWords.length; i++) {
                if(review.indexOf(negOpinionWords[i], startIndex) == startIndex) {
                    return -1;
                }
            }

        }
        return 0;
    }

    private static int checkForOpinionFirstPattern(
            String review,
            String feature,
            String[] posOpinionWords,
            String[] negOpinionWords
    ){
//        int opinion = 0;

        //implementation:
        String pattern = feature;
        int index = 0;
        int startIndex = 0;
        while(startIndex < review.length()){
            index = review.indexOf(pattern, startIndex);
            System.out.println("Index of "+pattern+" is : "+index);
            if(index == -1)break;

            for(int i=0; i<posOpinionWords.length; i++) {

                if(review.indexOf(posOpinionWords[i], startIndex) + posOpinionWords[i].length() + 1 == index) {
                    return 1;
                }
            }

            for(int i=0; i<negOpinionWords.length; i++) {
                if(review.indexOf(negOpinionWords[i],startIndex) + negOpinionWords[i].length() + 1 == index) {
                    return -1;
                }
            }

            startIndex = index+pattern.length();

        }

        return 0;
    }
}
