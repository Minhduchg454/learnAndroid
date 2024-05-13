package buoi4;

import java.util.Scanner;

public abstract class Dvat {
	private String giong,mau;
	private float canNang;
	
	public abstract void keu();
	
	public Dvat() {
		giong=new String();
		mau=new String();
		canNang=0.00f;
	}
	
	public Dvat(Dvat obj) {
		giong=new String(obj.giong);
		mau=new String(obj.mau);
		canNang=obj.canNang;
	}
	
	
	public void input() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Nhap giong: ");
		giong=sc.nextLine();
		System.out.println("Nhap mau: ");
		mau=sc.nextLine();
		System.out.println("Nhap can nang: ");
		canNang=sc.nextFloat();
	}
	
	public void print() {
		System.out.println("Giong: "+giong+", mau: "+mau+", can nang: "+canNang);
	}
}
