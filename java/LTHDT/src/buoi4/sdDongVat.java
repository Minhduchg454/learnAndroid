package buoi4;

import java.util.Scanner;

public class sdDongVat {
	public static void main (String ags[]) {
		Bo b=new Bo();
		b.keu();
	
		Dvat ds[];
		Scanner sc =new Scanner(System.in);
		System.out.println("Nhap so con vat: "); int n=sc.nextInt();
		ds=new Dvat[n];
		int c;
		for(int i=0;i<n;i++) {
			System.out.println("Nhap dong vat: Bo(0), De(1), Heo(2)");
			c=sc.nextInt();
			if(c==0) {
				ds[i]=new Bo();
			}else if(c==1){
				ds[i]=new De();
			}else{
				ds[i]=new Heo();
			}
			ds[i].input();
		}
		
		System.out.println("Danh sach cac con vat vua nhap la: ");
		for(int i=0;i<n;i++) {
			ds[i].print();
			ds[i].keu();
		}
	
	
	}
	
	
}
