package com.CourseReg.Service;

import java.util.*;
import java.util.regex.Pattern;

public class ValidateInput {
	 Scanner scanner = new Scanner(System.in);
	
	 
 public int getValidIntInput(){
	    int field = 0;
	    boolean isNumber;
	    do{
	    	if(scanner.hasNextInt()){
	    		field = scanner.nextInt();
	    		if(field <=0){
	    			isNumber = invalidInput();
	    		}else {
	    			isNumber = true;}
	    		}else{
				isNumber = invalidInput();
				scanner.next();
			}
		}while(!(isNumber));
		return field;
	}
 public String getValidStringInput(){
	    String field1 = null;
	    boolean isString;
	    do{
	    	if(scanner.hasNext()){
	    		field1 = scanner.nextLine();
	    		Pattern regex=Pattern.compile("[^-A-Za-z0-9 ]");
	    		if (field1.matches(".*" + regex + ".*")) {
	    			isString = invalidInput();
	    		}else {
	    		 isString = true;}
			}else{
				isString = invalidInput();
				scanner.nextLine();
			}
	     }while(!(isString));
		return field1;
	}
 public boolean invalidInput(){
	    System.out.println("Enter Valid Input :");
	    boolean invalidInp = false;
		return invalidInp;
	}
 
}
			