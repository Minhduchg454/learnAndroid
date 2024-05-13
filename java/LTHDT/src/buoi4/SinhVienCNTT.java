package buoi4;

import java.util.Scanner;

import buoi2.Date;
import buoi3.Student;

public class SinhVienCNTT extends Student {
	String taiKhoan,matKhau,email;
	public SinhVienCNTT () {
		super();
		taiKhoan=new String();
		matKhau=new String();
		email=new String();
	}
	
	public SinhVienCNTT (SinhVienCNTT obj) {
		super((Student) obj);
		taiKhoan=new String(obj.taiKhoan);
		matKhau=new String(obj.matKhau);
		email=new String(obj.email);
	}
	
	public void input () {
		super.input();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter account: "); taiKhoan=sc.nextLine();
		System.out.println("Enter password: "); matKhau=sc.nextLine();
		System.out.println("Enter email: "); email=sc.nextLine();
		//super.intputScore();
	}
	
	public void print() {
		super.print();
		System.out.print(" with account: "+taiKhoan+", email: "+email);
	}
	
	public String toString () {
		return super.toString()+" with account: "+taiKhoan+", email: "+email;
	}
	
	public String getAccount() {
		return taiKhoan;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void changePassword(String newPassword) {
		matKhau=new String(newPassword);
	}
	
	
}
