package com.va.week1.assign1.rc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CAD_to_USD_rc {

    public BigDecimal convertToUSD(Scanner sc) throws IOException {

        System.out.print("Enter amount in CAD: ");
        BigDecimal cad = sc.nextBigDecimal();

        if (cad.signum() < 0) {
            throw new IllegalArgumentException("CAD amount cannot be negative.");
        }

        String urlText =
            "https://www.bankofcanada.ca/valet/observations/FXCADUSD/csv?recent=1";

        HttpURLConnection connection =
            (HttpURLConnection) URI.create(urlText).toURL().openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        try {
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(
                    "Rate service returned HTTP " + connection.getResponseCode()
                );
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                        connection.getInputStream(),
                        StandardCharsets.UTF_8))) {

                String line;
                boolean inObservations = false;

                while ((line = reader.readLine()) != null) {

                    if (line.equals("\"OBSERVATIONS\"")) {
                        inObservations = true;
                        continue;
                    }

                    if (inObservations && line.matches(
                            "\"\\d{4}-\\d{2}-\\d{2}\",\"[0-9]+(\\.[0-9]+)?\"")) {

                        // A data row looks like: "2026-09-23","0.7101"
                        String[] parts = line.replace("\"", "").split(",");

                        String rateDate = parts[0];
                        BigDecimal rate = new BigDecimal(parts[1]);

                        BigDecimal usd = cad.multiply(rate)
                                            .setScale(2, RoundingMode.HALF_UP);

                        System.out.println("Rate date: " + rateDate);
                        System.out.println("Rate used: 1 CAD = " + rate + " USD");

                        return usd;
                    }
                }
            }

            throw new IOException("No CAD/USD observation found in the response.");

        } finally {
            connection.disconnect();
        }
    }
}