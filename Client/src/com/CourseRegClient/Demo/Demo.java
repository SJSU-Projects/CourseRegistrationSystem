package com.CourseRegClient.Demo;

import java.util.Scanner;

import com.CourseRegClient.Service.*;

public class Demo {

	static StudentClient student = new StudentClient();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		displayStudentMenu();
	}

	private static void displayStudentMenu() {

		int choice = 0;
		do {
			System.out.println("\n******************Welcome Student****************");
			System.out.println("1.Display All Students");
			System.out.println("2.Add New Student");
			System.out.println("3.Update Existing Student Information");
			System.out.println("4.Delete Student Information");
			System.out.println("5.Exit Menu");
			System.out.println("Please enter your choice:");

			choice = sc.nextInt();

			switch (choice) {

			case 1:
				student.getAllStudents();
				break;

			case 2:
				student.batchAdd();
				break;

			case 3:
				student.updateStudent();
				break;

			case 4:
				student.deleteBatch();
				break;

			case 5:
				return;

			default:
				return;
			}
		} while (choice != 5);
	}
}
