package buoi3;
import buoi2.Diem;

public class SdDoanThang {

	public static void main(String[] args) {
		Diem A=new Diem(2,5);
		Diem B=new Diem (20,30);
		DoanThang AB=new DoanThang(A,B);
		System.out.println("Line AB: ");
		AB.print();
		System.out.println("\nTranslation AB to (5,3): ");
		AB.translation(5, 3);
		AB.print();
		DoanThang CD=new DoanThang();
		System.out.println("\nEnter line AB: ");
		CD.input();
		CD.print();
		System.out.println("Length AB: "+CD.khoangCach());
		
		System.out.println("\nTest DE deep copy from CD: ");
		DoanThang DE=new DoanThang();
		DE.deepCopy(CD);
		DE.print();
		System.out.println("Translation CD to (5,3): ");
		CD.translation(5, 3);
		System.out.println("Printf line DE");
		DE.print();
		
		
		System.out.println("\nTest EF shallow copy from CD: ");
		DoanThang EF=new DoanThang();
		EF.shallowCopy(CD);
		EF.print();
		System.out.println("Translation CD to (5,3): ");
		CD.translation(5, 3);
		System.out.println("Printf line EF");
		EF.print();
	}

}
