package buoi3;

import java.util.Scanner;



import buoi2.Date;

public class Student {
	private String ID,fullName;
	private Date birthDate;
	private int numberCourse;
	private String [] nameCourse;
	private String [] score;
	
	public Student() {
		ID="";
		fullName="";
		birthDate=new Date();
		numberCourse=0;
		nameCourse=new String[60];
		score=new String[60];
	}
	
	public Student(String ID1,String fullName1,Date birtDate1, int numberCourse1) {
		ID=new String(ID1);
		fullName=new String(fullName1);
		birthDate=new Date(birtDate1);
		numberCourse=numberCourse1;
		nameCourse=new String[60];
		score= new String[60];
	}
	
	
	public Student(Student obj) {
		ID=new String(obj.ID);
		fullName=new String(obj.fullName);
		birthDate=new Date(obj.birthDate);
		numberCourse=obj.numberCourse;
		nameCourse=new String[60];
		score= new String[60];
		for(int i=0;i<numberCourse;i++) {
			nameCourse[i]=new String(obj.nameCourse[i]);
			score[i]=new String(obj.score[i]);
		}
	}
	
	public void print () {
		System.out.print("["+ID+", "+fullName+", "+birthDate+", "+numberCourse+", score course: {");
		for(int i=0;i<numberCourse;i++) {
			System.out.print(nameCourse[i]+": "+score[i]);
			if(i+1<numberCourse)
				System.out.print(", ");
		}
		System.out.print("}]");
	}
	public String toString() {
		String S="["+ID+", "+fullName+", "+birthDate+", "+numberCourse+", score course: {";
		for(int i=0;i<numberCourse;i++) {
			S+=nameCourse[i]+": "+score[i];
			if(i+1<numberCourse)
				S+=", ";
		}
		S+="}]";
		return S;
	}
	
	public void input () {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter id: "); ID=sc.nextLine();
		System.out.println("Enter full name: "); fullName=sc.nextLine();
		System.out.println("Enter birth date: ");birthDate.inPut();
	}
	
	public void intputScore() {
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("Enter number course: "); numberCourse=sc.nextInt();
		}while(numberCourse>=60);
		sc.nextLine();
		for(int i=0;i<numberCourse;i++) {
			System.out.println("Name course "+(i+1)+" : ");
			nameCourse[i]=sc.nextLine();
			System.out.println("Score course "+(i+1)+" : ");
			score[i]=sc.nextLine();
			
		}
	}
	
	public void addCourse(String nameCourse1, String score1) {
		if(numberCourse<=nameCourse.length) {
			nameCourse[numberCourse]=nameCourse1;
			score[numberCourse]=score1;
			numberCourse++;
		}else {
			System.out.println("Can't add course because array max!!!");
		}
	}
	
	
	public float avgScore() {
		float avg=0.00f;
		for(int i=0;i<numberCourse;i++) {
			if(score[i].equals("A")) {
				avg+=4.00f;
			}else if(score[i].equals("B+")) {
				avg+=3.50f;
			}else if(score[i].equals("B")) {
				avg+=3.00f;
			}else if(score[i].equals("C+")) {
				avg+=2.50f;
			}else if(score[i].equals("C")) {
				avg+=2.00f;
			}else if(score[i].equals("D+")) {
				avg+=1.50f;
			}else if(score[i].equals("D")) {
				avg+=1.00f;
			}else {
				avg+=0.00f;
			}
		}
		return avg/numberCourse;
	}
	
	
	public void deleteCourse(String name) {
		int index=-1;
		for(int i=0;i<numberCourse;i++) {
			if(nameCourse[i].equals(name)) {
				index=i;
				break;
			}	
		}
		if(index==-1) {
			System.out.println("No course!!!");
			return;
		}
			
		
		for(int i=index;i<numberCourse-1;i++) {
			nameCourse[i]=nameCourse[i+1];
			score[i]=score[i+1];
		}
		numberCourse--;
	}
	
	public String getID() {
		return ID;
	}
	
	public String name() {
		String temp=new String(fullName);
		temp.trim();
		int indexBlank=temp.lastIndexOf(" ");
		String name=temp.substring(indexBlank+1);
		return name;
	}
	
	public String getEmail() {
		return "";
	}
	
	public String getAccount() {
		return "";
	}
}
