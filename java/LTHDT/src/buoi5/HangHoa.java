package buoi5;

import java.util.Scanner;

public class HangHoa {
	private String mSo,ten,nSxuat;
	public HangHoa() {
		mSo=new String();
		ten=new String();
		nSxuat=new String();
	}
	
	public HangHoa(HangHoa obj) {
		mSo=new String(obj.mSo);
		ten=new String(obj.ten);
		nSxuat=new String(obj.nSxuat);
	}
	
	public void nhap() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Nhap ma so hang hoa: ");mSo=sc.nextLine();
		System.out.println("Nhap ten hang hoa: ");ten=sc.nextLine();
		System.out.println("Nhap nam san xuat: ");nSxuat=sc.nextLine();
	}
	
	public void in() {
		System.out.print("Ma so hang hoa: "+mSo+", ten: "+ten+", nam san xuat: "+nSxuat);
	}
	
	public String toString() {
		return "Ma so hang hoa: "+mSo+", ten: "+ten+", nam san xuat: "+nSxuat;
	}
	
	public String layMaSo() {
		return mSo;
	}
	
	public String layTen() {
		return ten;
	}
	
	public String layNamSanXuat() {
		return nSxuat;
	}
	
}
