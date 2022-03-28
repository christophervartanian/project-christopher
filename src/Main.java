package src;
//import API in Java
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.net.URL;
import org.xml.sax.SAXException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException{
    
        System.out.println("Hello World");
        
        //create a new scanner object
        Scanner sc = new Scanner(new File("data/H_Temp.csv"));
        sc.useDelimiter(","); 
        while (sc.hasNext())
        {
            System.out.println(sc.next());
        }
        sc.close();

    }
}

