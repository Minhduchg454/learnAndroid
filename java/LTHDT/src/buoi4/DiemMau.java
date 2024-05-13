package buoi4;

import java.util.Scanner;

import buoi2.Diem;

public class DiemMau extends Diem {
	String mau;
	public DiemMau() {
		super();
		mau=new String();
	}
	
	public DiemMau(int x1,int y1, String m) {
		super(x1,y1);
		mau=new String(m);
	}
	
	public DiemMau(DiemMau obj) {
		super((Diem) obj);
		mau=new String(obj.mau);
	}
	
	public void input () {
		super.input();
		Scanner sc= new Scanner (System.in);
		System.out.println("Enter color : "); mau=sc.nextLine();
	}
	
	public void print() {
		super.print();
		System.out.print(", with color: "+ mau);
	}
	
	public String toString() {
		return super.toString()+", with color: "+ mau;
	}
	
	public void addColor(String color) {
		mau=new String(color);
	}
	
	
}


