package buoi3;


import java.util.List;


public class sdGach {

    public static final String data ="C:/Users/minhd/OneDrive - ctu.edu.vn/00_SoftwareCourseInOneDrive/2023-2024/00HocKi1/Lap trinh huong doi tuong/JaVaOnTap/LTHDT/src/gach.txt";
    public static final String COMMA=",";

    public static void main(String[] args) {
        
        List<String> listLine = IoUnit.readFile(data);
        int n=listLine.size();
        
        Gach []ds;
        ds=new Gach[n];
        int i=0;
        for (String line : listLine) {
        	
        	String[] parts = line.split(COMMA);

            String id = parts[0];
            String color = parts[1];
            int length = Integer.parseInt(parts[2]);
            int horizontal = Integer.parseInt(parts[3]);
            int quantity = Integer.parseInt(parts[4]);
            long price = Integer.parseInt(parts[5]);
            
            ds[i]=new Gach(id, color, quantity, length, horizontal, price);
            i++;
        }
        
      
        System.out.println("types of bricks: ");    
        for(i=0;i<n;i++){
            System.out.print((i+1)+" : ");
            ds[i].print();
        }

        System.out.println("\nBrick min expense: ");
        float minExpense=Float.MAX_VALUE;
        for(i=0;i<n;i++){
            if(minExpense>ds[i].expense()){
                minExpense=ds[i].expense();
            }
        }
        
        for(i=0;i<n;i++){
            if(minExpense==ds[i].expense()){
                ds[i].print();
                System.out.println("");
            }
        }
        
        
        
    }
}

 
      /*
    	 //Output
        Gach Brick1= new Gach("pv01","blue", 20, 20, 50, 100000);
        Gach Brick2= new Gach("pv02","red", 10, 20, 50, 500000);
        Gach Brick3= new Gach("pv03","gray", 5, 30, 20, 10000);
        
        List <Gach> GachList=new ArrayList<>();
        GachList.add(Brick1);
        GachList.add(Brick2);
        GachList.add(Brick3);
        
        String line=null;
        for (Gach e: GachList){
            line=e.getId()+COMMA+e.getColor()+COMMA+e.getQuantity()+COMMA+e.getLenght()+COMMA+e.getHorizontal()+COMMA+e.getPrice();
            IoUnit.writeFile(data, line);
        }
    	
        */