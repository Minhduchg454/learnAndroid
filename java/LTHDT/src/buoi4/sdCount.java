package buoi4;

import buoi2.Diem;

public class sdCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Diem D1=new Diem();
		Diem D2=new Diem(D1);
		Diem D3=new Diem(6,8);
		Diem D4=D1;
		
		System.out.println("So lan goi ham tao doi tuong: "+D4.getCount());
	}

}
