package buoi5;

import java.util.Scanner;

public class KhachHang {
	private String cccd,hten;
	private int nSinh;
	
	public KhachHang() {
		cccd=new String();
		hten=new String();
		nSinh=2024;
	}
	
	public KhachHang(KhachHang obj) {
		cccd=obj.cccd;
		hten=obj.hten;
		nSinh=obj.nSinh;
	}
	
	public void nhap() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Nhap cccd: "); cccd=sc.nextLine();
		System.out.println("Nhap ho ten: ");hten=sc.nextLine();
		System.out.println("Nhap nam sinh: ");nSinh=sc.nextInt();
	}
	
	public void in() {
		System.out.print("Cccd: "+cccd+", ho ten: "+hten+", nam sinh: "+nSinh);
	}
	
	public String toString() {
		return "Cccd: "+cccd+", ho ten: "+hten+", nam sinh: "+nSinh;
	}
	
	public String getCCCD() {
		return cccd;
	}
	
	public float layTiLeGiam() {
		return 0.00f;
	}
	
	public String getHoTen() {
		return hten;
	}
	
	public int getNamSinh() {
		return nSinh;
	}
	
	
}
