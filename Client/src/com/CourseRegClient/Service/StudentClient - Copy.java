package com.CourseRegClient.Service;


import java.util.Scanner;

import com.CourseRegClient.Entity.StudentEntity;
import com.google.gson.Gson;
import com.sun.jersey.api.client.*;


public class StudentClient {

	Scanner sc = new Scanner(System.in);
	StudentEntity s = new StudentEntity();
	Client client =  Client.create();
	ClientResponse responseEntity;
	WebResource webResource;
	Gson gson = new Gson();

	public void getAllStudents(){


		webResource = client.resource("http://localhost:8080/CourseReg/v1/students/allstudents");
		responseEntity = webResource.accept("application/json").get(ClientResponse.class);

		if (responseEntity.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
		}

		String output = responseEntity.getEntity(String.class);
		displayStudentList(output);
	}


	public void updateStudent() {

		System.out.println("Enter Student ID, New LastName : ");
		String newVal = sc.nextLine();
		String[] input = newVal.split(",");

		//String input = "{\"studentID\":\"45\",\"firstName\":\"Vikas\",\"lastName\":\"khadsare\",\"dateOfBirth\":\"2010-01-04\",\"gender\":\"female\",\"contactNo\":\"4088397292\",\"address\":\"XYZ\",\"password\":\"pwd\"}";

		String inputStr = "{\"studentID\":\"" + input[0] + "\",\"lastName\":\"" + input[1] +  "\"}";

		WebResource webResource = client.resource("http://localhost:8080/CourseReg/v1/students/studentupdate");
		ClientResponse responseEntity = webResource
				.type("application/json")
				.put(ClientResponse.class,inputStr);

		if (responseEntity.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
		}


		//String input = "{\"studentID\":\"45\",\"firstName\":\"Vikas\",\"lastName\":\"khadsare\",\"dateOfBirth\":\"2010-01-04\",\"gender\":\"female\",\"contactNo\":\"4088397292\",\"address\":\"XYZ\",\"password\":\"pwd\"}";

	}

	private void displayStudentList(String inputStudentList){

		System.out.println("*********************Student List ****************************\n");
		StudentEntity[] studentList = gson.fromJson(inputStudentList, StudentEntity[].class);

		for (int i = 0; i < studentList.length; i++) {
			System.out.println("\n*******************************************************");
			System.out.println("Student ID    : " +studentList[i].getStudentID());
			System.out.println("First Name    : " +studentList[i].getFirstName());
			System.out.println("Last Name     : " +studentList[i].getLastName());
			System.out.println("Address       : " +studentList[i].getAddress());
			System.out.println("Contact No.   : " +studentList[i].getContactNo());
			System.out.println("Date OF Birth : " +studentList[i].getDateOfBirth());
			System.out.println("Gender        : " +studentList[i].getGender());		
		}
	}

	public void batchAdd()
	{
		System.out.println("Enter Number of students you want to add : ");
		int noOfStudents = sc.nextInt();
		
		StudentEntity s = new StudentEntity();

		for(int i = 0; i<noOfStudents; i++)
		{
		String[] input = new String[8];
		
		System.out.println("Enter Student Details for" + (i+1) + "student" );
			System.out.println("\n*******************************************************");
			System.out.println("Enter First Name: " );
			input[0] = sc.next();
			s.setLastName(input[0]);
			System.out.println("Enter Last Name: " );
			input[1] = sc.next();
			s.setLastName(input[1]);
			System.out.println("Enter Date OF Birth : " );
			input[2] = sc.next();
			s.setLastName(input[2]);
			System.out.println("Enter Gender: " );	
			input[3] = sc.next();
			s.setLastName(input[3]);
		    System.out.println("Enter Address: " );
			input[4] = sc.next();
			s.setLastName(input[4]);
			System.out.println("Enter Contact Number: " );
			input[5] = sc.next();
			s.setLastName(input[5]);
			System.out.println("Enter Email ID: " );
			input[6] = sc.next();
			s.setLastName(input[6]);
			System.out.println("Enter Password: " );
			input[7] = sc.next();
			s.setLastName(input[7]);


		String inputStr = "[{\"firstName\":\"" + input[0] + "\",\"lastName\":\"" + input[1] + "\",\"dateOfBirth\":\"" + input[2] + "\",\"gender\":\"" + input[3] + "\",\"address\":\"" + input[4] + "\",\"contactNo\":\"" + input[5] + "\",\"emailID\":\"" + input[6] + "\" ,\"password\":\"" + input[7] + "\"}]";

		//String inputStr = "{\"firstName\":\"" + input[0] + "\",\"lastName\":\"" + input[1] + "\",\"dateOfBirth\":\"" + input[2] + "\",\"gender\":\"" + input[3] + "\",\"address\":\"" + input[4] + "\",\"contactNo\":\"" + input[5] + "\",\"emailID\":\"" + input[6] + "\" ,\"password\":\"" + input[7] + "\"}";

/*		s.setFirstName("a");
		s.setLastName("b");
		s.setAddress("c");
		s.setContactNo("32535");
		s.setGender("g");
		s.setStudentID(1);
		s.setPassword("a");
		s.setEmailID("a");
		s.setDateOfBirth("1986-05-03");
		s.setRel("");
		s.setLink("");
*/
	//	String inputStr = "[{\"emailID\":\"sdgh\",\"password\":\"sdgh\",\"studentID\":\"45\",\"firstName\":\"Vikas\",\"lastName\":\"khadsare\",\"dateOfBirth\":\"2010-01-04\",\"gender\":\"female\",\"contactNo\":\"4088397292\",\"address\":\"XYZ\"}]";
		webResource = client.resource("http://localhost:8080/CourseReg/v1/students/addBatch");
		ClientResponse responseEntity = webResource
				.type("application/json")
				.post(ClientResponse.class,inputStr);

		if (responseEntity.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
		}
		}

	}

	/*private void displayUpdateMenu(){

			int choice = 0;
			do {
				System.out.println("\n");
				System.out.println("1.First Name");
				System.out.println("2.Last Name");
				System.out.println("3.Address");
				System.out.println("4.Contact Number");
				System.out.println("5.Exit Menu");
				System.out.println("Please enter your choice:");

				choice = sc.nextInt();

				switch (choice) {
				case 1:
					//LoginOperations();
					break;

				case 2:
				//	StudentOperations();
					break;

				case 3:
					//InstructorOperations();
					break;

				case 4:
				//	adminOperations();
					break;

				case 5:
					return;

				default:
					return;
				}
			} while (choice != 5);
		}
	 */
}

