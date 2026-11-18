package com.va.week1.assign1.rc;
import java.util.Scanner;
import java.io.IOException;
import java.math.BigDecimal;



public class DriverMainClass_rc {


	public static void main(String[] args) {
		
		
	// Created Objects for 4 classes in the same package to be called based on the user option
		
		Gradecalculation_rc gc_rc = new Gradecalculation_rc();
		MilestoKilometer_rc mtk_rc = new MilestoKilometer_rc();
		Litres_to_gallons_rc ltg_rc = new Litres_to_gallons_rc();
		CAD_to_USD_rc ctu_rc = new CAD_to_USD_rc();
		
		
// create objects for all the classes..  and then call the method using switch case.. 

	System.out.println("Enter the choice for operation:"
			+ "1.-Gradecacluation,"
			+" 2-Miles_to_Kms,"
			+" 3-Litres_to_Gallons,"
			+" 4-CAD_to_USD_conversion");
	
	try (Scanner sc = new Scanner(System.in)) {
		int choice=sc.nextInt();
		
		    switch (choice) {
		      case 1:
		    	System.out.println("Doing Grade Calculation.. ");
		    	gc_rc.GradeCalc(sc);
		        break;
		      case 2:
		        System.out.println("Doing Miles to Kilometer Conversion");
		        double kilometers = mtk_rc.toKilometers(sc);
		        System.out.println("Distance in Kilometers: "+kilometers);
		        // call using object.method to initialize the method...
		        break;
		      case 3:
		    	  System.out.println("Doing Litres to Gallons");
		    	  double gallons = ltg_rc.toGallons(sc);
		    	  System.out.println("Amount in gallons: "+gallons);
		    	  break;
		      case 4:
		    	  
		    	  System.out.println("Doing CAD to USD conversion");

		    	    try {
		    	        BigDecimal usd = ctu_rc.convertToUSD(sc);
		    	        System.out.println("Amount in USD: $" + usd);
		    	    } catch (IOException e) {
		    	        System.out.println("Could not get the latest rate: " + e.getMessage());
		    	    } catch (IllegalArgumentException e) {
		    	        System.out.println(e.getMessage());
		    	    }
		    	  break;
		    	  
		      default:

	                System.out.println("Invalid choice.");

	                break;
		        
		      
		    }
	}
		
		
		
	
		
	}

}