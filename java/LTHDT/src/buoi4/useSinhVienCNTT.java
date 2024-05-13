package buoi4;

import java.util.Scanner;

import buoi2.Diem;
import buoi3.Student;

public class useSinhVienCNTT {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Student listStudent[];
		System.out.println("Enter number of students: "); int numberStudent=sc.nextInt();
		listStudent = new Student[numberStudent];
		
		int select=0;
		for(int i=0;i<numberStudent;i++) {
			System.out.println("Student "+(i+1)+" :");
			System.out.println("Enter student (0) or IT student(1)");
			select=sc.nextInt();
			if(select==0) {
				listStudent[i]=new Student();
			}else {
				listStudent[i]=new SinhVienCNTT();
			}
			listStudent[i].input();
			listStudent[i].intputScore();;
		}
		
		
		for(int i=0;i<numberStudent;i++) {
			System.out.println(listStudent[i]);
		}
		
		sc.nextLine();
		System.out.println("Enter email of student, you want to find");
		String emailFind=sc.nextLine();
		int i;
		for(i=0;i<numberStudent;i++) {
			if(listStudent[i].getEmail().equals(emailFind)){
				System.out.println("Account ELCIT: "+listStudent[i].getAccount()+", gpa: "+ listStudent[i].avgScore());
			}
		}
		
		
	}

}
