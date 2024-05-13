package buoi3;

import java.util.Scanner;

public class Gach {
    private String id,color;
    private int quantity,length,horizontal;
    private long price;

    public Gach(){
        id="";
        color="";
        quantity=0;
        length=0;
        horizontal=0;
        price=0;
    }

    public Gach(String id1, String color1, int quantity1,int length1,int horizontal1, long price1){
        id=id1;
        color=color1;
        quantity=quantity1;
        length=length1;
        horizontal=horizontal1;
        price=price1;
    }

    public Gach(Gach obj){
        id=obj.id;
        color=obj.color;
        quantity=obj.quantity;
        length=obj.length;
        horizontal=obj.horizontal;
        price=obj.price;
    }

    public void input(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter id: "); id=sc.nextLine();
        System.out.println("Enter color: "); color=sc.nextLine();
        System.out.println("Enter quantity: "); quantity=sc.nextInt();
        System.out.println("Enter lenght (cm): "); length=sc.nextInt();
        System.out.println("Enter horizontal (cm): "); horizontal=sc.nextInt();
        System.out.println("Enter price: "); price=sc.nextLong();
        sc.close();
    }

    public void print(){
        System.out.println("id: "+id+", color: "+color+", quantity: "+quantity+", lenght: "+length+" cm, horizontal: "+horizontal+" cm, price: "+price);
    }
    public String toString(){
        return "id: "+id+", color: "+color+", quantity: "+quantity+", lenght: "+length+" cm, horizontal: "+horizontal+" cm, price: "+price;
    }

    public float retailPrice(){
        return ((float)price/quantity)*1.02f;
    }

    public float maxTileArea (){
        return (float)(((horizontal/100.0)*(length/100.0))*(float)quantity);
    }

    public float expense (){
        return price/maxTileArea();
    }

    public int quantityBox (int D, int N){
        int numberLenght=(int)(D/(length/100.0));
        int numberHorizontal=(int)(N/(horizontal/100.0));
        int numberTile=numberHorizontal*numberLenght;
        return numberTile/quantity;
    }

    public String getId(){
        return id;
    }

    public String getColor(){
        return color;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getLenght(){
        return length;
    }

    public int getHorizontal(){
        return horizontal;
    }

    public long getPrice(){
        return price;
    }


}
