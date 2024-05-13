package buoi5;

import java.util.Scanner;

public class KhachHangVip extends KhachHang {
	private float TleGiam;
	private Date d;
	public KhachHangVip() {
		super();
		TleGiam=0.00f;
		d=new Date();
	}
	
	public KhachHangVip(KhachHangVip obj) {
		super((KhachHang) obj);
		TleGiam=obj.TleGiam;
		d=new Date(obj.d);
	}
	
	public void nhap() {
		super.nhap();
		Scanner sc=new Scanner(System.in);
		System.out.println("Nhap ti le giam: ");TleGiam=sc.nextFloat();
		System.out.println("Nhap ngay tro thanh khach hang vip: ");
		d.nhap();
	}
	
	public void in() {
		super.in();
		System.out.print(", ngay: "+d+ ", ti le giam: "+TleGiam);
	}
	
	public String toString() {
		return super.toString()+", ngay: "+d+ ", ti le giam: "+TleGiam;
	}
	
	public float layTiLeGiam() {
		return TleGiam;
	}
	
}
