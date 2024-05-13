package buoi1;

public class sdTongHaiSo {

	public static void main(String[] args) {
		CacPhepTinh t=new CacPhepTinh();
		int n=t.sum1(2, 3);//Ham khong tinh goi tu doi tuong
		int n2=CacPhepTinh.sum2(2, 3);//ham tinh goi tu lop
		double n3=t.sum1(2, 3.5);
		int n4=t.sum1(1, 2, 3);
		System.out.println(n);
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(n4);
		
		int []Array;
		Array = new int[5]; //cap phat bo nho moi su dung
		for (int i=0;i<5;i++) {
			Array[i]=2*i;
		}
		
		for (int i=0;i<5;i++) {
			System.out.print(Array[i]+" ");
		}
		
		
		
		
		
		
		
	}

}
