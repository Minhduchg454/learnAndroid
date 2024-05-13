package buoi5;

import java.util.Scanner;


public class Date {
	int day,month,year;
    public Date(){
        day=1;
        month=1;
        year=2024;
    }
    public Date(int day1, int month1,int year1){
        day=day1;
        month=month1;
        year=year1;
    }

    public Date(Date A){
        day=A.day;
        month=A.month;
        year=A.year;
    }

    public void in(){
        System.out.println(day+"/"+month+"/"+year);
    }
    
    public void in(String S){
        System.out.print(S);
        in();
    }

    public String toString(){
        return "("+day+"/"+month+"/"+year+")";
    }


    public void nhap(){
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("Enter day: ");day=sc.nextInt();
            System.out.println("Enter month: ");month=sc.nextInt();
            System.out.println("Enter year: ");year=sc.nextInt();
            if(!valid()) 
                System.out.println("Day not vaild, re enter!!!");
        }while(!valid());
    }

    public boolean valid(){
        boolean check=false;
        int maxDay[]={0,31,28,31,30,31,30,31,31,30,31,30,31};
        if( (month==2 && year%400==0) ||(month==2 &&year%4==0 && year%100!=0))
            maxDay[2]=29;
        if(day>0 && month>0 && year>0 && month<=12 && day<=maxDay[month])
            check=true;
        return check;
    }

    public Date nextDay(){
        Date nextDate= new Date(day, month, year);
        nextDate.day++;
        if(!nextDate.valid()){
            nextDate.day=1;
            nextDate.month++;
            if(!nextDate.valid()){
                nextDate.day=1;
                nextDate.month=1;
                nextDate.year++;
            }
        }
        return nextDate;
    }

    public Date nextDay(int addDay){
        Date resultDate= new Date(day, month, year);
       for(int i=0;i<addDay;i++){
            resultDate=resultDate.nextDay();
       }
        return resultDate;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return day;
    }
    
    public int getYear(){
        return day;
    }
    
    public float TleGiam() {
    	return 0.00f;
    }
}
