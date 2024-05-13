package buoi2;

import java.util.Scanner;

public class Diem {
    int x,y;
    private static int count =0;
    public Diem(){
        x=0;
        y=0;
        count++;
    }

    public Diem(int x1, int y1){
        x=x1;
        y=y1;
        count++;
    }
    
    public Diem(Diem d1){
        x=d1.x;
        y=d1.y;
        count++;
    }
    
    
    public Diem(int n){
        x=y=n;
        count++;
    }
    public void input(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter x: "); x=sc.nextInt();
        System.out.println("Enter y: "); y=sc.nextInt();
    }
    public String toString() {
    	return "( "+x+" , "+y+" )";
    }

    public void print(){
        System.out.print("( "+x+" , "+y+" )");
    }

    public void print(String S){
        System.out.println(S);
        print();
    }

    public void doDoi(int dx, int dy){
        x+=dx;
        y+=dy;
    }

    public int giaTriX(){
        return x;
    } 

    public int giaTriY(){
        return y;
    }

    public float khoangCach(){
        return (float)Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }

    public float khoangCach(Diem B){
        return (float)Math.sqrt(Math.pow(B.x-x, 2)+Math.pow(B.y-y, 2));
    }
    
    public void tTien(int dx, int dy) {
    	x+=dx;
    	y+=dy;
    }
    
    public int getCount() {
    	return count;
    }
}
