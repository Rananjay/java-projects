package com.va.week1.assign1.rc;
import java.util.Scanner;

public class Litres_to_gallons_rc {

    Scanner sc_rc = new Scanner(System.in);

    public double toGallons(Scanner sc) {

        double litres;
        double gallons;

        System.out.println("Enter the amount in litres: ");

        litres = sc_rc.nextDouble();

        gallons = litres * 0.264172;

        return gallons;
    }
}