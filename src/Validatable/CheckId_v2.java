/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validatable;

import static Validatable.CheckCode_v2.checkNumber;
import java.util.Scanner;

/**
 *
 * @author GIA HUY
 */
public class CheckId_v2 {
        private static final Scanner sc = new Scanner(System.in);

    public static String checkId(String type) {
        while (true) {
            //System.out.print("Enter Code Prefix (WK/VS/CT/SO/SM): ");
            String id = "";
            String number = "";

            //   String prefix = input.toUpperCase().trim();
            // Phân loại theo tiền tố
            switch (type.toUpperCase().trim()) {
                case "PERSON": {
                    String prefix = "P";
                    number = checkNumber(prefix);
                    id = prefix.toUpperCase().trim() + number;
                    return id;
                }

                case "ZONE": {
                    String prefix = "Z";
                    number = checkNumber(prefix);
                    id = prefix.toUpperCase().trim() + number;
                    return id;
                }

                case "RESTRICTEDZONE": {
                    String prefix = "RZ";
                    number = checkNumber(prefix);
                    id = prefix.toUpperCase().trim() + number;
                    return id;
                }
                
                case "INCIDENT": {
                    String prefix = "IN";
                    number = checkNumber(prefix);
                    id = prefix.toUpperCase().trim() + number;
                    return id;
                }


                default:
                    System.out.println("[Error]: Invalid Id Prefix! Must be one of: P, Z, RZ. Try again.\n");
            }
        }
    }
    }

 
