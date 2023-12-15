import java.util.Scanner;

public class HoaDon {
        private String hten;
        private int soKW, dmuc;
        private float dgia;
        private String ngay;

        public HoaDon (){
            hten = new String();
            soKW = 0;
            dgia = 0.00f;
            dmuc = 0;
            ngay = new String ();
        }

        public HoaDon (HoaDon hd){
            hten = new String(hd.hten);
            soKW = hd.soKW;
            dgia = hd.dgia;
            dmuc = hd.dmuc;
            ngay = new String (hd.ngay);
        }

        public void nhap (){
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap ten khach hang: ");
            hten = sc.nextLine();
            System.out.println("Nhap so KW dien: ");
            soKW = sc.nextInt();
            System.out.println("Nhap don gia: ");
            dgia = sc.nextFloat();
            System.out.println("Nhap dinh muc: ");
            dmuc = sc.nextInt();
            sc.nextLine();
            System.out.println("Nhap ngay thang nam lap (dd-mm-yyyy): ");
            ngay = sc.nextLine();
        }


        public void in(){
            System.out.println("Ten khach hang: "+hten+", so KW dien: "+soKW+", don gia: "+dgia+", dinh muc: "+dmuc+", ngay lap: "+ngay);
        }

        public String toString(){
            return "Ten khach hang: "+hten+", so KW dien: "+soKW+", don gia: "+dgia+", dinh muc: "+dmuc+", ngay lap: "+ngay;
        }

        public float layThueSuat (){
            return 0;
        }

        public double thanhTien (){
            double thanhTien = 0.0f;
            if ( soKW <= dmuc){
                thanhTien = soKW * (double)dgia;
            } else{
                thanhTien = (double)soKW*dgia*dmuc + (soKW -dmuc)*(double)dgia*2.5;
            }
            return thanhTien;
        }

        

    public static void main(String[] args) {
        HoaDon hd1 =new HoaDon();
        System.out.println("\nNhap thong tin hoa don 1: ");
        hd1.nhap();;
        System.out.println("\nThong tin hoa don 1 vua nhap la: ");
        hd1.in();
        HoaDon hd2 =new HoaDon(hd1);
        System.out.println("Thong tin hoa don 2 sao chep: ");
        hd2.in();
    }
}