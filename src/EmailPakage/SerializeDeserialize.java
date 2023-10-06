package EmailPakage;

// Index : 200524M
// Name  : Ransara H.P.C.

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SerializeDeserialize {
    private LocalDate now = LocalDate.now();
    private String dt = now.format(DateTimeFormatter.ofPattern("MM/d/YYYY"));

    private ArrayList<SendEmailTLS> sentMailList = new ArrayList<>();
    public void serialize(SendEmailTLS mail)  {
        try {
            FileOutputStream fout = new FileOutputStream("savedMailObjects.ser",true);
            ObjectOutputStream obout =  new ObjectOutputStream(fout);

            obout.writeObject(mail);
            obout.close();
            fout.close();

        }catch (IOException e){
            System.out.println(e);
        }

    }

    public ArrayList<SendEmailTLS> deserialize() {

        try {
            FileInputStream fin = new FileInputStream("savedMailObjects.ser");
            ObjectInputStream objin = new ObjectInputStream(fin);
            SendEmailTLS email = (SendEmailTLS) objin.readObject();

            sentMailList.add(email);

            while (email != null){
                try {
                    objin = new ObjectInputStream(fin);
                    email = (SendEmailTLS) objin.readObject();
                    sentMailList.add(email);
                }catch (EOFException e) {
                        break;
                    }

            }
            objin.close();
            fin.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return sentMailList;
    }

    public void print(String date){
        SerializeDeserialize serializeDeserialize =new SerializeDeserialize();
        ArrayList<SendEmailTLS> sentMailListTemp = serializeDeserialize.deserialize();
        int len = sentMailListTemp.size();
        boolean isSend = false;

        for(int i=0;i<len;i++){
            if(sentMailListTemp.get(i).getDate().equals(date)){
                System.out.println("Recipient Email : "+sentMailListTemp.get(i).getTo() +"\n"
                                          +"Subject : "+sentMailListTemp.get(i).getSubject() +"\n"
                                          +"Body : "+sentMailListTemp.get(i).getBody() +"\n"
                                          +"Date : "+sentMailListTemp.get(i).getDate() +"\n"
                                          +"Time : "+sentMailListTemp.get(i).getTime());
                        System.out.println("");

                        isSend = true;
            }
        }
        if(!isSend){
            System.out.println("An email wasn't sent on "+date);
        }
    }

    public boolean checkSentmails(SendEmailTLS email){
        boolean isSent = false;
        SerializeDeserialize serializeDeserialize =new SerializeDeserialize();
        ArrayList<SendEmailTLS> sentMailListTemp = serializeDeserialize.deserialize();
        int len = sentMailListTemp.size();
        for(int i=0;i<len;i++){
            if(sentMailListTemp.get(i).getTo().equals(email.getTo()) & sentMailListTemp.get(i).getSubject().equals(email.getSubject())){

                String yearback= String.valueOf(LocalDate.parse(dt, DateTimeFormatter.ofPattern ( "M/d/uuuu" )).plusYears( -1 ));
                String y = yearback.replace('-','/');


                if(sentMailListTemp.get(i).getDate().equals(dt)){
                    isSent = false;
                }
                else {
                    isSent = true;
                }

            }
        }
        return isSent;
    }

}


