package com.CourseRegClient.Service;

import java.util.Scanner;

import com.CourseRegClient.Entity.StudentEntity;
import com.google.gson.Gson;
import com.sun.jersey.api.client.*;

/**
 * Root resource 
 */
public class StudentClient {

	Scanner sc = new Scanner(System.in);
	StudentEntity s = new StudentEntity();
	Client client = Client.create();
	ClientResponse responseEntity;
	WebResource webResource;
	Gson gson = new Gson();
	
	/**
	 * Method calling HTTP GET requests. The method gets all studnet's information
	 * from the student Table. 
	 * If Response status = 200, data has been successfully read. 
	 * Else appropriate error message is displayed. 
	 */

	public void getAllStudents() {

		webResource = client.resource("http://localhost:8080/CourseReg/v1/students/allstudents");
		responseEntity = webResource.accept("application/json").get(ClientResponse.class);

		if (responseEntity.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
		}

		String output = responseEntity.getEntity(String.class);
		displayStudentList(output);
	}

	/**
	 * Method calling HTTP PUT requests. The method updates the data in student Table. 
	 * If Response status = 200, data has been successfully updated. 
	 * Else appropriate error message is displayed. 
	 */
	public void updateStudent() {

		String[] input = new String[2];
		System.out.println("Enter Student ID :");
		input[0] = sc.next();
		System.out.println("Enter New Last Name :");
		input[1] = sc.next();
		
		String inputStr = "{\"studentID\":\"" + input[0] + "\",\"lastName\":\"" + input[1] + "\"}";

		WebResource webResource = client.resource("http://localhost:8080/CourseReg/v1/students/studentupdate");
		ClientResponse responseEntity = webResource.type("application/json").put(ClientResponse.class, inputStr);

		if (responseEntity.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
		}
		else
		{
			System.out.println("Last name upated successfully!!!");
		}
	}

	private void displayStudentList(String inputStudentList) {

		System.out.println("*********************Student List ****************************\n");
		StudentEntity[] studentList = gson.fromJson(inputStudentList, StudentEntity[].class);

		for (int i = 0; i < studentList.length; i++) {
			System.out.println("\n*******************************************************");
			System.out.println("Student ID    : " + studentList[i].getStudentID());
			System.out.println("First Name    : " + studentList[i].getFirstName());
			System.out.println("Last Name     : " + studentList[i].getLastName());
			System.out.println("Address       : " + studentList[i].getAddress());
			System.out.println("Contact No.   : " + studentList[i].getContactNo());
			System.out.println("Date OF Birth : " + studentList[i].getDateOfBirth());
			System.out.println("Gender        : " + studentList[i].getGender());
		}
	}

	/**
	 * Method calling HTTP POST requests. The method accepts student details from user and inserts it in
	 * student Table. Batch insertion operation is implemented. 
	 * If Response status = 200, data has been successfully updated. 
	 * Else appropriate error message is displayed. 
	 */
	public void batchAdd() {
		System.out.println("Enter Number of students you want to add : ");
		int noOfStudents = sc.nextInt();

		StudentEntity s = new StudentEntity();

		for (int i = 0; i < noOfStudents; i++) {
			String[] input = new String[8];

			System.out.println("Enter Student Details for" + (i + 1) + "student");
			System.out.println("\n*******************************************************");
			System.out.println("Enter First Name: ");
			input[0] = sc.next();
			System.out.println("Enter Last Name: ");
			input[1] = sc.next();
			System.out.println("Enter Date OF Birth : ");
			input[2] = sc.next();
			System.out.println("Enter Gender: ");
			input[3] = sc.next();
			System.out.println("Enter Address: ");
			input[4] = sc.next();
			System.out.println("Enter Contact Number: ");
			input[5] = sc.next();
			System.out.println("Enter Email ID: ");
			input[6] = sc.next();
			System.out.println("Enter Password: ");
			input[7] = sc.next();

			String inputStr = "[{\"firstName\":\"" + input[0] + "\",\"lastName\":\"" + input[1]
					+ "\",\"dateOfBirth\":\"" + input[2] + "\",\"gender\":\"" + input[3] + "\",\"address\":\""
					+ input[4] + "\",\"contactNo\":\"" + input[5] + "\",\"emailID\":\"" + input[6]
					+ "\" ,\"password\":\"" + input[7] + "\"}]";

			webResource = client.resource("http://localhost:8080/CourseReg/v1/students/addBatch");
			ClientResponse responseEntity = webResource.type("application/json").post(ClientResponse.class, inputStr);

			if (responseEntity.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
			} else {
				System.out.println("Student data inserted scucessfully!!!");
			}
		}

	}

	/**
	 * Method calling HTTP DELETE requests. The method accepts student IDs from user 
	 * and deletes those users form student Table. Batch delete operation is implemented. 
	 * If Response status = 200, data has been successfully updated. 
	 * Else appropriate error message is displayed. 
	 */
	public void deleteBatch()
	{
		System.out.println("Enter Number of students you want to delete : ");
		int noOfStudents = sc.nextInt();
		String ids = null;
		for (int i = 0; i < noOfStudents; i++) {
			
			System.out.println("Enter Student ID :");
			if( i ==0 )
			{
				ids = sc.next();
			}
			else
			{
				ids = ids + "," + sc.next();				
			}			
		}
		
		webResource = client.resource("http://localhost:8080/CourseReg/v1/students/deleteBatch/"+ids);
		ClientResponse responseEntity = webResource.type("application/json").delete(ClientResponse.class);

		if (responseEntity.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + responseEntity.getStatus());
		} else {
			System.out.println("Student data deleted successfully!!!");
		}
	}
		
}
