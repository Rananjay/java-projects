package com.va.week1.assign1.rc;
import java.util.Scanner;

public class Gradecalculation_rc {


		public void GradeCalc(Scanner sc) {
			
		/*
		 * Logic for grade calculation...	
		 */
			
			int marks[] = new int[6];
			int i;
			float total = 0, avg;

		
				for (i = 0; i < 6; i++) {

					System.out.print("Enter Marks of Subject" + (i + 1) + ":");
					marks[i] = sc.nextInt();
					total = total + marks[i];
				}
				


			// Calculating average here
			avg = total / 6;
			System.out.println("Average Marks: " + avg);

			System.out.print("The student Grade is: ");
			if (avg >= 90) {
				System.out.print("A ");
				System.out.print("Student Successfully Passed the Course and Received Grade A"); 
				
			} 
			
			else if (avg >= 80) {
				System.out.print("B ");
				System.out.print("Student Successfully Passed the Course and Received Grade B");
			}
			

			else if (avg >= 70) {
				System.out.println("C ");
				System.out.println("Student Successfully Passed the Course and Received Grade C");
			}
			else if (avg >= 60) {
				System.out.println("D ");
				System.out.println("Student Successfully Passed the Course and Received Grade D");
			}

			else {
				System.out.println("F ");
				System.out.println(" Student DID not Pass the Course and Received Grade F");
			}


			
		}
		
	}