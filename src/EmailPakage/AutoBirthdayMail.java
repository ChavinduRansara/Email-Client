package EmailPakage;

// Index : 200524M
// Name  : Ransara H.P.C.

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class AutoBirthdayMail {
    private static Vector<Recipient> recipientsObjects = new Vector<>();
    private static SerializeDeserialize serializeDeserialize = new SerializeDeserialize();
    private static LocalDate now = LocalDate.now();
    private static String dt = now.format(DateTimeFormatter.ofPattern("MM/dd"));

    public static void bithDayMailSend(Vector<String[]> resList){

        for (int i=0;i<resList.size();i++){
            Recipient recipient = new Recipient(resList.get(i));
            recipientsObjects.add(recipient);

            sendBirthDayMail(recipient);
        }

    }

    public static void bithDayMailSend(String[] strings){

        Recipient recipient = new Recipient(strings);

        sendBirthDayMail(recipient);

    }

    private static void sendBirthDayMail(Recipient res){
        if(res.getBirth_day()!=null){

            if(res.getState().equals("Official_Friend") && res.getBirth_day().substring(5,10).equals(dt)){
                SendEmailTLS mail = new SendEmailTLS(res.getEmail(),"Birthday Wish","Wish You a Happy Birthday ! \n\n H.P.C. Ransara");

                if(!serializeDeserialize.checkSentmails(mail)){

                    System.out.println("Birthday wish sending...");
                    mail.send();
                    System.out.println("Done.");

                    serializeDeserialize.serialize(mail);
                }

            }

            if(res.getState().equals("Personal") && res.getBirth_day().substring(5,10).equals(dt)){
                SendEmailTLS mail = new SendEmailTLS(res.getEmail(),"Birthday Wish","Happy Birthday "+res.getNick_name()+ "! . I hope your day is as amazing and special as you are. \n\n Chavindu");

                if(!serializeDeserialize.checkSentmails(mail)){

                    System.out.println("Birthday wish sending...");
                    mail.send();
                    System.out.println("Done.");

                    serializeDeserialize.serialize(mail);
                }

            }

        }
    }

}
