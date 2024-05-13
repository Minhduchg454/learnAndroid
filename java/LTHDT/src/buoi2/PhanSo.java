package buoi2;

import java.util.Scanner;

public class PhanSo {
    int numerator; //tu so
    int denominator; //mau so
    public PhanSo(){
        numerator=0;
        denominator=1;
    }
    public PhanSo(int numerator1, int denominator1){
        numerator=numerator1;
        denominator=denominator1;
    }

    public PhanSo(PhanSo A){
        numerator=A.numerator;
        denominator=A.denominator;
    }

    public void input(){
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Enter numerator"); numerator=sc.nextInt();
            System.out.println("Enter denominator"); denominator=sc.nextInt();
            if(denominator==0)
                System.out.println("Fraction not valid!!, re enter");
        }while(denominator==0);
    }

    public void print(){
        if(numerator==0){
            System.out.println(0+" ");
        }else if(denominator==1){
            System.out.print(numerator+" ");
        }else{
            System.out.print(numerator+"/"+denominator+" ");
        }
    }

    public void NghichDao(){
        int temp=numerator;
        numerator=denominator;
        denominator=temp;
    }

    public PhanSo giaTriNghichDao(){
        PhanSo inverseFraction =new PhanSo();
        inverseFraction.numerator=denominator;
        inverseFraction.denominator=numerator;
        return inverseFraction;
    }

    public float giaTri(){
        return (float)numerator/denominator;
    }

    public boolean lonHon(PhanSo A){
        return giaTri()>A.giaTri() ? true : false;
    }

    public PhanSo cong(PhanSo a){
        PhanSo result =new PhanSo();
        result.numerator=(numerator*a.denominator)+(a.numerator*denominator);
        result.denominator=denominator*a.denominator;
        return result;
    }


    public int GCD () {
		int a=numerator;
		int b=denominator;
		
		if (b>a) {
			int t=a;
			a=b;
			b=t;
		}
		while (b!=0) {
			int r = a%b;
			a=b;
			b=r;
		}
		return a;
	}

    public PhanSo shortCut () {
		int n=GCD();
		PhanSo C= new PhanSo();
		C.numerator=numerator/n;
		C.denominator=denominator/n;
		return C;
	}


    
}
