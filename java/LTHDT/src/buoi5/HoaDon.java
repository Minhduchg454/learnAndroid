package buoi5;

import java.util.Scanner;

public class HoaDon {
	private String mSo,nVien,tDe;
	private KhachHang k;
	private ChiTiet c[];
	private Date d;
	
	public HoaDon() {
		mSo=new String();
		nVien=new String();
		tDe=new String();
		k=new KhachHang();
		c=new ChiTiet[20];
		d=new Date();
	}
	
	public HoaDon(HoaDon obj) {
		mSo=new String(obj.mSo);
		nVien=new String(obj.nVien);
		tDe=new String(obj.tDe);
		k=new KhachHang(obj.k);
		for(int i=0;i<c.length;i++) {
			c[i]=new ChiTiet(obj.c[i]);
		}
		d=new Date(obj.d);
	}
	
	public void nhap() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Nhap tieu de hoa don: ");tDe=sc.nextLine();
		System.out.println("Nhap ma so nhan vien: ");mSo=sc.nextLine();
		System.out.println("Nhap ten nhan vien: ");nVien=sc.nextLine();
		System.out.println("Nhap thong tin khach hang (0) hoac khach hang VIP (1)");
		int z=sc.nextInt();
		if(z==0) {
			k=new KhachHang();
		}else {
			k=new KhachHangVip();
		}
		k.nhap();
		
		System.out.println("Nhap ngay lap hoa don: ");d.nhap();
		System.out.println("Nhap so luong hang hoa: ");
		int n=sc.nextInt();
		for(int i=0;i<n;i++) {
			System.out.println("Nhap hang hoa thu "+(i+1)+" : ");
			c[i]=new ChiTiet();
			c[i].nhap();
		}
	}
		
	public void in() {
			System.out.println("Tieu de: "+tDe+"\nTen nhan vien: "+nVien+", ma so nhan vien: "+mSo
					+"\nKhach hang: "+k+"\nDanh sach hang hoa: ");
			for(int i=0;i<c.length;i++) {
				if(c[i]==null) {
					break;
				}else {
					System.out.println("  "+(i+1)+": "+c[i]);
				}
				
			}
			System.out.println("Thanh Tien: "+tong());
	}
	public String toString() {
		String s="Tieu de: "+tDe+"\nTen nhan vien: "+nVien+", ma so nhan vien: "+mSo
				+"\nKhach hang: "+k+"\nDanh sach hang hoa: ";
		for(int i=0;i<c.length;i++) {
			if(c[i]==null) {
				break;
			}else {
			s+="  "+(i+1)+": "+c[i];
			}
		}
		s+="\nThanh Tien: "+tong();
		return s;
	}
	
	public long tong() {
		long tong=0;
		for(int i=0;i<c.length;i++) {
			if(c[i]==null) {
				break;
			}else {
			tong+=c[i].tongTien();
			}
		}
		return (long)((1-k.layTiLeGiam())*tong);
	}
}
