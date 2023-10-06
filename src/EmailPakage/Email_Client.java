package EmailPakage;

// Index : 200524M
// Name  : Ransara H.P.C.

import java.util.Scanner;

public class Email_Client {

    public static void main(String[] args) {

        System.out.println("Email client has been started");
        System.out.println("");

        File_Handle file = new File_Handle();

        if(File_Handle.sizeOfClientList()==0){

            Scanner input1 = new Scanner(System.in);
            System.out.println("Enter the Recipient Details: \n"
                    + "If Official clients -> <name>,<email>,<designation> \n"
                    + "If Official friends -> <name>,<email>,<designation>,<birth_day-YYYY/MM/DD> \n"
                    + "If Personal recipients -> <name>,<nick-name>,<email>,<designation>,<birth_day-YYYY/MM/DD>");
            String recipientDetails = input1.nextLine();

            file.writeFile(recipientDetails);
        }

        SerializeDeserialize serializeDeserialize = new SerializeDeserialize();

        AutoBirthdayMail.bithDayMailSend(file.readFile());

        System.out.println("");

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");
        int option = input2.nextInt();

        if(option>6 | option<1){
            System.out.println("Invalid Input!");
        }

        System.out.println("");

        switch(option){
            case 1:
                Scanner input3 = new Scanner(System.in);
                System.out.println("Enter the Recipient Details: \n"
                        + "If Official clients -> <name>,<email>,<designation> \n"
                        + "If Official friends -> <name>,<email>,<designation>,<birth_day-YYYY/MM/DD> \n"
                        + "If Personal recipients -> <name>,<nick-name>,<email>,<designation>,<birth_day-YYYY/MM/DD>");
                String recipientDetails = input3.nextLine();

                file.writeFile(recipientDetails);

                String[] splitList = recipientDetails.split("[,]",0);

                AutoBirthdayMail.bithDayMailSend(splitList);

                break;

            case 2:
                Scanner input4 = new Scanner(System.in);
                System.out.println("Enter the details according to following order : Recipient email,Subject,Content");
                String emailDetails = input4.nextLine();

                String[] splittedDetailsList = emailDetails.split("[,]",0);

                String recipientEmail = splittedDetailsList[0];
                String subject = splittedDetailsList[1];
                String content = splittedDetailsList[2];

                SendEmailTLS mail = new SendEmailTLS(recipientEmail,subject,content);
                System.out.println("Email sending...");
                mail.send();
                System.out.println("Done.");

                serializeDeserialize.serialize(mail);

                break;

            case 3:
                Scanner input5 = new Scanner(System.in);
                System.out.println("Enter the Date according to the following order: YYYY/MM/DD");
                String date1 = input5.nextLine();

                BirthDay.printBirthdayList(date1);

                break;

            case 4:
                Scanner input6 = new Scanner(System.in);
                System.out.println("Enter the Date according to the following order: YYYY/MM/DD");
                String date2 = input6.nextLine();

                serializeDeserialize.print(date2);

                break;

            case 5:
                System.out.println(Recipient.count);
                break;
        }
    }
}

