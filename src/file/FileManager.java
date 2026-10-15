package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Minh_Khang
 */
public class FileManager {
    private static final String FILE_PATH ="data/Person.txt";
    // em thiet ke de doc du lieu
    public static ArrayList<String> readFile() throws FileNotFoundException, IOException{
    ArrayList<String> lines= new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line; // thac mac
            while ((line = br.readLine())!=null){ 
                lines.add(line);
            }
        }catch (IOException e){System.out.println("Read Error: "+e.getMessage());}
    return lines;
    }
 public static void appendFile(String data) {

        try (BufferedWriter bw =                        //Try buffer la ham ghi tam tren ram, nhom tham khao tren mang
                new BufferedWriter(new FileWriter(FILE_PATH, true))) { 
                                    // ghi moi trong file path, neu dung la du kien moi thi thuc hien
            bw.write(data);
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}
