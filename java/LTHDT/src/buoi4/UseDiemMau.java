package buoi4;

import java.util.Scanner;

import buoi2.Diem;

public class UseDiemMau {

	public static void main(String[] args) {
		
		/*
		DiemMau A= new DiemMau(5, 10, "white");
		System.out.println("Point A: "+A);
	
		DiemMau B= new DiemMau();
		System.out.println("Enter point B: ");
		B.input();
		System.out.println("Point B: "+B);
		System.out.println("Translation B to (10,8)");
		B.tTien(10, 8);
		B.addColor("yellow");
		B.print();
		*/
		
		/*
		 Cha con the tham chieu den con nhung khong truy cap duoc vao cac thanh phan cua con
		 Con co the truy cap cac thanh phan cua cha tru private,  nhung khong tham chieu den cha 
		 */
		
		Scanner sc= new Scanner(System.in);
		Diem []list;
		System.out.println("Enter number element of list: ");
		int numberList=sc.nextInt();
		list =new Diem[numberList];
		int select=0;
		for(int i=0;i<numberList;i++) {
			System.out.println("Point "+(i+1)+" :");
			System.out.println("Enter point (0) or colorPoint(1)");
			select=sc.nextInt();
			if(select==0) {
				list[i]=new Diem();
			}else {
				list[i]=new DiemMau();
			}
			list[i].input();
		}
		
		
		System.out.println("\nList of point: ");
		for(int i=0;i<numberList;i++) {
			System.out.println(list[i]);
		}
		
		
		
	}
}
