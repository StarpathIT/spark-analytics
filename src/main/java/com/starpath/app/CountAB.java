package com.starpath.app;

/*** SimpleApp.java ***/

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;

public class CountAB {
    public static void main(String[] args) {
        String logFile = "/Users/admin/software/spark-0.9.0-incubating/sample.txt"; // Should be some file on your system
        JavaSparkContext sc = new JavaSparkContext("local", "Spark Analytics",
                "/Users/admin/software/spark-0.9.0-incubating", new String[]{"target/spark-analytics-1.0-SNAPSHOT.jar"});
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("a");
            }
        }).count();

        long numBs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("b");
            }
        }).count();

        System.out.println("Lines with a : " + numAs + ", lines with b : " + numBs);
    }
}