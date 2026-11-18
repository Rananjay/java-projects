package com.va.week1.assign1.rc;

import java.util.Scanner;

public class MilestoKilometer_rc {
	
	static Scanner sc_rc = new Scanner(System.in);
	

	
	public double toKilometers(Scanner sc_rc) {
		double kilometers, miles = 0;
		System.out.println("Enter the distance in miles: ");
		miles = sc_rc.nextDouble();
		kilometers = miles * 1.609344;
		return kilometers;
		
	}

}