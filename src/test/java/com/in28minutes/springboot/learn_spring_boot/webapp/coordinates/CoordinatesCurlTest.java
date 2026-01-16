package com.in28minutes.springboot.learn_spring_boot.webapp.coordinates;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

public class CoordinatesCurlTest {

    private static final Log logger = LogFactory.getLog(CoordinatesCurlTest.class);


    public static void main(String[] args) throws IOException, InterruptedException {
        sendCoordinates();
    }

    public static void sendCoordinates() throws IOException, InterruptedException {

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double x = random.nextDouble() * 100;
            double y = random.nextDouble() * 100;
            double z = random.nextDouble() * 100;

            // Format JSON with 2 decimal places
            String json = String.format("{\"x\":%.2f,\"y\":%.2f,\"z\":%.2f}", x, y, z);
            // Replace comma with dot for JSON format if locale uses comma
            // json = json.replace(",", ".");

            logger.info("Sending: " + json);

            // Assuming the app is running on localhost:8080
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "curl",
                    "-X", "POST",
                    "-H", "Content-Type: application/json",
                    "-d", json,
                    "http://localhost:8080/coordinates"
            );

            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Curl command failed with exit code " + exitCode);
            }

            Thread.sleep(1000); // Wait 1 second between requests
        }
    }
}
