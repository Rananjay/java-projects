package com.va.week3.lab2.RananjayC_F2026;

import java.security.SecureRandom;

public class DiceGame_lab2_rc {

	public static void main(String[] args) {
		
		SecureRandom randomNumbers = new SecureRandom();

	      int Rolldice1 = randomNumbers.nextInt(6)+1;// count of 1s rolled
	      int Rolldice2 = randomNumbers.nextInt(6)+1; // count of 2s rolled
	      int Rolldice3 = randomNumbers.nextInt(6)+1; // count of 3s rolled
	      int Rolldice4 = randomNumbers.nextInt(6)+1; // count of 4s rolled
	      
	      int SumofDice = Rolldice1+Rolldice2+Rolldice3+Rolldice4;
	      
	      if(SumofDice==7||SumofDice==11||SumofDice==15||SumofDice==21) {
	    	  System.out.println("User Wins from the sum of 4 dice");
	    	  
	    	  
	      }else if(SumofDice==10||SumofDice==12||SumofDice==19||SumofDice==20||SumofDice==22||SumofDice==23||SumofDice==24) {
	    	  
	    	  System.out.println("User Looses from the sum of 4 dice");
	    	  
	      }
	      else if(SumofDice==13) {
	    	  System.out.println("User Looses from the sum of 4 dice sum is 13");
	      }
	      
	      else {
	    	  
	    	  int goalnum = SumofDice;
	    	  
	    	  System.out.println("Your Goal number is : "+goalnum);
	    	  System.out.println("Roll the dice again!");
	    	  
	    	  Rolldice1 = randomNumbers.nextInt(6)+1;
	    	  Rolldice2 = randomNumbers.nextInt(6)+1;
	    	  Rolldice3 = randomNumbers.nextInt(6)+1;
	    	  Rolldice4 = randomNumbers.nextInt(6)+1;
	    	  
	    	  SumofDice = Rolldice1+Rolldice2+Rolldice3+Rolldice4;
	    	  
	    	  System.out.println();
	    	  System.out.println("Second Roll of Dice"); 
	    	  System.out.println("Roll of Dice 1 : "+Rolldice1); 
	    	  System.out.println("Roll of Dice 2 : "+Rolldice2); 
	    	  System.out.println("Roll of Dice 3 : "+Rolldice3);
	    	  System.out.println("Roll of Dice 4 : "+Rolldice4);
	    	  System.out.println("Total sum of Roll dice 2nd time: "+SumofDice);
	    	  
	    	  if (SumofDice == goalnum) {
	    		  System.out.println("Your sum of Dice: "+SumofDice);
	    		  System.out.println("Your Goal number: "+goalnum);
	    		  System.out.println("Your Sum of Dice matches the Goal number! you WIN");
	    	  }
	    	  else {
	    		  System.out.println("Your sum of Dice: "+SumofDice);
	    		  System.out.println("Your Goal number: "+goalnum);
	    		  System.out.println("Your Sum of Dice did not Match with Goal number! you LOOSE!");
	    	  }
	    	  
	    	  
	    	  

	      }
	      
	      
		
	}
	
}
