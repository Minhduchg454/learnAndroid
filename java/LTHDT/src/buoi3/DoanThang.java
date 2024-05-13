package buoi3;
import buoi2.Diem;


public class DoanThang {
	private Diem d1,d2;
	public DoanThang() {
		d1=new Diem();
		d2=new Diem();
	}
	public DoanThang(Diem d11, Diem d22) {
		d1=new Diem(d11);
		d2=new Diem(d22);
	}
	
	public DoanThang (DoanThang dt1) {
		d1=new Diem(dt1.d1);
		d2=new Diem(dt1.d2);
	}
	
	public void print() {
		System.out.println("["+d1+","+d2+"]");
	}
	
	public String toString () {
		return "["+d1+","+d2+"]";
	}
	
	public void input () {
		System.out.println("Enter point D1: ");
		d1.input();
		System.out.println("Enter point D2: ");
		d2.input();
	}
	
	public void translation (int dx, int dy) {
		d1.doDoi(dx, dy);
		d2.doDoi(dx, dy);
	}
	
	public float khoangCach () {
		return d1.khoangCach(d2);
	}
	
	public Diem getPointD1(){ 
		return d1;
	}

	public Diem getPointD2() {	
		return d2;
	}
	
	public void shallowCopy(DoanThang obj) {
		d1=obj.d1;
		d2=obj.d2;
	}
	
	public void deepCopy (DoanThang obj) {
		d1=new Diem(obj.d1);
		d2=new Diem(obj.d2);
	}
	

}
