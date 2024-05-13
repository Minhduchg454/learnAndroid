package buoi5;

import java.util.Scanner;

public class ChiTiet {
	private int Sluong;
	private long dGia;
	private HangHoa h;
	
	public ChiTiet() {
		Sluong=0;
		dGia=0;
		h=new HangHoa();
	}
	
	public ChiTiet(ChiTiet obj) {
		Sluong=obj.Sluong;
		dGia=obj.dGia;
		h=new HangHoa(obj.h);
	}
	
	public void nhap() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Nhap thong tin hang hoa: "); h.nhap();
		System.out.println("Nhap so luong: "); Sluong=sc.nextInt();
		System.out.println("Nhap don gia: "); dGia=sc.nextLong();
	}
	
	public void in() {
		h.in();
		System.out.print(", so luong: "+Sluong+", don gia: "+dGia);
	}
	
	public String toString() {
		return h+", so luong: "+Sluong+", don gia: "+dGia;
	}
	
	public long tongTien() {
		return Sluong*dGia;
	}
}
