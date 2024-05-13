package buoi2;

import java.io.DataInput;

public class sdDate {
    public static void main(String[] args) {
        //Test function construction
        Date construction1 =new Date();
        Date construction2 = new Date(2,2,1998);
        Date construction3 = new Date(construction2);
        System.out.println("Test function construction 1:");
        construction1.print();
        System.out.println("\nTest function construction 2:");
        construction2.print();
        System.out.println("\nTest function construction 3:");
        construction3.print("Function copy: ");

        //Test input and vaild
        Date dateInput =new Date();
        System.out.println("\nTest enter day");
        dateInput.inPut();
        dateInput.print();
        System.out.println("Day "+dateInput);

        //Test nextDay;
        System.out.println("\nTest next day");
        dateInput.nextDay().print();

        //Test AddDay;
        System.out.println("\nTest add 20 day");
        dateInput.nextDay(20).print();
    }
    
}
