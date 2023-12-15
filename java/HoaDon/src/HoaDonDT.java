import java.util.Scanner;

public class HoaDonDT extends HoaDon {
    private String chky;
    private String maThue;
    private float thue;

    public HoaDonDT (){
        super();
        chky = new String();
        maThue = new String();
        thue = 0.00f;
    }

    public HoaDonDT (HoaDonDT dt){
        super((HoaDon) dt);
        chky = new String(dt.chky);
        maThue = new String(dt.maThue);
        thue = dt.thue;
    }

    public void nhap (){
        Scanner sc = new Scanner(System.in);
        super.nhap();
        System.out.println("Nhap chu ky: ");
        chky = sc.nextLine();
        System.out.println("Nhap ma thue: ");
        maThue = sc.nextLine();
        System.out.println("Nhap thue suat VAT %(0,00): ");
        thue = sc.nextFloat();
    }

    public void in (){
        super.in();
        System.out.println(" +Chu ky: "+chky+", ma thue: "+maThue+", thue suat: "+thue);
    }

    public String toString (){
        String S=super.toString();
        S+=" +Chu ky: "+chky+", ma thue: "+maThue+", thue suat: "+thue;
        return S;
    }

    public float layThueSuat (){
        return thue;
    }

    

    public static void main(String[] args) {
        //TEST
        /*
            HoaDonDT hd1 = new HoaDonDT();
            System.out.println("Nhap thong tin hoa don 1: ");
            hd1.nhap();
            System.out.println("\nThong tin hoa don 1: ");
            hd1.in();
         */
        Scanner sc = new Scanner (System.in);
        System.out.println("Nhap so hoa don: :");
        int n = sc.nextInt();
        HoaDon ds [] = new HoaDon[n];
        int s = 0;
        for (int i=0; i<n;i++){
            System.out.println("\nNhap hoa don (0) hoac hoa don dien tu (1) thu "+(i+1)+" : ");
            s = sc.nextInt();
            if (s==0){
                ds[i] = new HoaDon();
            }else{
                ds[i] = new HoaDonDT();
            }
            ds[i].nhap();
        }

        System.out.println("\nDanh sach hoa don vua nhap: ");
        for (int i=0; i<n; i++){
             System.out.println("\nHoa don thu "+(i+1)+" : ");
             ds[i].in();
        }

        //Tong
        double tong = 0;
        for (int i=0; i<n;i++){
            tong += ds[i].thanhTien() + ds[i].thanhTien()*ds[i].layThueSuat();
        }
        
        System.out.println("\nTong doanh thu la : "+tong+" VND");

        
        //Tong tien phai tra theo nam
        double tongTienTheoNam = 0;
        
    
        

         
       
    }
}
