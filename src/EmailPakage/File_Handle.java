package EmailPakage;

// Index : 200524M
// Name  : Ransara H.P.C.

import java.io.*;
import java.util.Vector;

public class File_Handle {

    private Vector<String[]> RecipientList = new Vector<>();

    public void writeFile(String data) {
        try {
            BufferedWriter text = new BufferedWriter(new FileWriter("client_List.txt",true));

            text.append(data);
            text.newLine();
            text.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Vector<String[]> readFile(){
        File file = new File("client_List.txt");
        try {
            BufferedReader readText = new BufferedReader(new FileReader("client_List.txt"));
            String temp;
            int i=0;
            while((temp = readText.readLine())!= null){
                String[] splitList = temp.split("[,]",0);
                RecipientList.add(splitList);

                i++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return RecipientList;
    }

   public static long sizeOfClientList() {
       File file = new File("client_List.txt");
       long x ;
       x= file.length();
       return x;
   }

}
