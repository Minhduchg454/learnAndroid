package buoi3;

import java.util.List;
import java.util.Scanner;

import buoi2.Date;

public class UseStudent {
	public static final String data ="C:/Users/minhd/OneDrive - ctu.edu.vn/00_SoftwareCourseInOneDrive/2023-2024/00HocKi1/Lap trinh huong doi tuong/JaVaOnTap/LTHDT/src/student.txt";
    public static final String COMMA=",";
    
    public static void sortAlphabet(Student ds[], int n) {
    	for (int i=0;i<n-1;i++) {
			for (int j=n-1;j>=i+1;j--) {
				if (ds[j-1].name().compareTo(ds[j].name())>0) {
					Student t=ds[i];
					ds[i]=ds[j];
					ds[j]=t;
				}
			}
		}
    }
    
    
	public static void main(String[] args) {
		/*
		Student test=new Student();
		System.out.println("Enter student: ");
		test.input();
		test.intputScore();
		System.out.println(test);
		System.out.println("AVG: "+test.avgScore());
		*/
		Scanner sc=new Scanner(System.in);
		Student ds[];
		List <String> listStudent =IoUnit.readFile(data);
		int n=listStudent.size();
		ds=new Student[n];
		for(int i=0;i<n;i++) {
			String[] parts = listStudent.get(i).split(COMMA);
			String id=parts[0];
			String fullName=parts[1];
			int day=Integer.parseInt(parts[2]);
			int month=Integer.parseInt(parts[3]);
			int year=Integer.parseInt(parts[4]);
			Date birthDate=new Date(day,month,year);
			
			int numberCourse1=Integer.parseInt(parts[5]);
			
			ds[i]=new Student(id,fullName,birthDate,0);
			
			for(int j=6;j<=5+numberCourse1*2;j+=2) {
				String nameCourse=parts[j];
				String score=parts[j+1];
				ds[i].addCourse(nameCourse, score);
			}
		}
		
		System.out.println("List of students:");
		for(int i=0;i<n;i++) {
			System.out.println((i+1)+" "+ds[i]+", avg: "+ds[i].avgScore());
		}
		
		
		System.out.println("\nList of students sort Alphabet:");
		UseStudent.sortAlphabet(ds, n);
		for(int i=0;i<n;i++) {
			System.out.println((i+1)+" "+ds[i]+", avg: "+ds[i].avgScore());
		}
		
		
		System.out.println("\nList of students with warning");
		for(int i=0;i<n;i++) {
			if(ds[i].avgScore()<1.00)
				System.out.println((i+1)+" "+ds[i]+", avg: "+ds[i].avgScore());
		}
		
		float maxAVG=0.00f;
		for(int i=0;i<n;i++) {
			if(ds[i].avgScore()>maxAVG)
				maxAVG=ds[i].avgScore();
		}
		System.out.println("\nList of students with max AVG score");
		for(int i=0;i<n;i++) {
			if(ds[i].avgScore()==maxAVG)
				System.out.println((i+1)+" "+ds[i]+", avg: "+ds[i].avgScore());
		}
		
		
	
		
		

	
		System.out.println("\nEnter ID student to delete cours: ");
		String IDindex=sc.nextLine();
		int index=-1;
		for(int i=0;i<n;i++) {
			if(ds[i].getID().equals(IDindex))
				index=i;
		}
		
		if(index<=n && index>=0) {
			System.out.println("Enter name course to delete: ");
			String name=sc.nextLine();
			ds[index].deleteCourse(name);
			ds[index].print();
		}else {
			System.out.println("ID student is incorrect!!!");
		}
		
		
	
		System.out.println("\nEnter ID student to add cours: ");
		IDindex=sc.nextLine();
		index=-1;
		for(int i=0;i<n;i++) {
			if(ds[i].getID().equals(IDindex))
				index=i;
		}
		
		if(index<=n && index>=0) {
			System.out.println("Enter name course to add: ");
			String nameCourse1=sc.nextLine();
			System.out.println("Enter score: ");
			String score1=sc.nextLine();
			ds[index].addCourse(nameCourse1, score1);
			ds[index].print();
		}else {
			System.out.println("ID student is incorrect!!!");
		}
		
		return;
	}
}
