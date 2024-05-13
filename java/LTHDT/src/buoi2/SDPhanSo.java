package buoi2;

import java.util.Scanner;

public class SDPhanSo {
    
    
    public void bubbleSort (PhanSo Array[], int n){
        int i,j;
        for(i=0;i<n-1;i++){
            for(j=n-1;j>=i+1;j--){
                if(Array[j].giaTri()<Array[j-1].giaTri()){
                    PhanSo temp=Array[j];
                    Array[j]=Array[j-1];
                    Array[j-1]=temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        /*
        
        
        PhanSo a=new PhanSo(3, 7);
        PhanSo b=new PhanSo(4, 9);
        System.out.print("Fraction a: ");
        a.print();
        System.out.print("Fraction b: ");
        b.print();

        PhanSo x=new PhanSo();
        PhanSo y=new PhanSo();
        System.out.println("Fraction x: ");
        x.input();
        x.print();

        System.out.println("Fraction y: ");
        y.input();
        y.print();

        System.out.println("Inverse fraction  x: ");
        x.giaTriNghichDao().print();

        System.out.println("Sum fraction  x and y: ");
        x.cong(y).print();
         */

        Scanner sc=new Scanner(System.in);
        PhanSo []listpPhanSos;
        System.out.println("Enter number of element fraction in list ");
        int n=sc.nextInt();
        listpPhanSos =new PhanSo[n];
        for(int i=0;i<n;i++){
            System.out.println("Fraction "+(i+1)+" : ");
            listpPhanSos[i]=new PhanSo();
            listpPhanSos[i].input();
        };

        
        System.out.println("Element fraction in array:");
        for(int i=0;i<n;i++){
            listpPhanSos[i].print();
        }

        PhanSo maxFraction=new PhanSo(0,1);
        PhanSo sumFraction=new PhanSo(0,1);
        for(int i=0;i<n;i++){
            sumFraction=sumFraction.cong(listpPhanSos[i]);

            if(listpPhanSos[i].lonHon(maxFraction))
                maxFraction=listpPhanSos[i];
        
         }

      

        System.out.print("\nIn array, max fraction is: ");
        maxFraction.print();
        System.out.print("and sum is: ");
        sumFraction.shortCut().print();


        System.out.println("\nSort array ascending:");
        SDPhanSo T=new SDPhanSo();
        T.bubbleSort(listpPhanSos, n);

        for(int i=0;i<n;i++){
            listpPhanSos[i].print();
        }


        

    }
}
