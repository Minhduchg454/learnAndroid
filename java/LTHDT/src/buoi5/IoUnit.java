package buoi5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IoUnit {
   
    public static void writeFile (String pathFile, String line){
        try{
            FileWriter file =new FileWriter(pathFile, true);
            BufferedWriter br= new BufferedWriter(file);
            br.write(line);
            br.newLine();

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }



    public static  List<String> readFile(String patchFile){
        List<String> listLine = new ArrayList<>();
        try{
            FileReader fileReader =new FileReader(patchFile);
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            while((line= br.readLine()) !=null){
                listLine.add(line);
            }
            br.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return listLine;
    }    
}
