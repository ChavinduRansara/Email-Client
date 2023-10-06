package EmailPakage;

// Index : 200524M
// Name  : Ransara H.P.C.

import java.util.Vector;

public class BirthDay {
    static void printBirthdayList(String date){
        File_Handle fl =new File_Handle();
        Vector<String[]> resipiantList = fl.readFile();
        int len = resipiantList.size();

        boolean hasBirthday = false;

        for(int i=0;i<len;i++){
            int slen = resipiantList.get(i).length;

            if(resipiantList.get(i)[slen-1].equals(date)){
                System.out.println("Name : "+resipiantList.get(i)[0]);
                hasBirthday = true;
            }
        }
        if(!hasBirthday){
            System.out.println("There hasn't any birthday on "+date +".");
        }
    }

}
