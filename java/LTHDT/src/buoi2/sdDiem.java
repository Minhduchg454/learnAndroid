package buoi2;

public class sdDiem {
    public static void main(String[] args) {
        System.out.println("Diem A");
        Diem A=new Diem(3, 4);
        A.print();
       
        System.out.println("Diem B");
        Diem B=new Diem();
        B.input();
        B.print();

        System.out.println("Diem C");
        Diem C=new Diem(-B.giaTriX(), -B.giaTriY());
        C.print();

        System.out.println("Khoang cach tu diem B den tam 0: "+ B.khoangCach());
        System.out.println("Khoang cach tu diem A den diemB: "+ A.khoangCach(B));
    }
}
