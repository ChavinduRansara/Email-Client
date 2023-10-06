package EmailPakage;

// Index : 200524M
// Name  : Ransara H.P.C.

public class Recipient {
    private String name;
    private String email;
    private String designation;
    private String nick_name;
    private String birth_day;
    private String state;

    static int count = 0;
    Recipient(String[] list) {
        name = list[0];
        if (list.length == 3) {
            email = list[1];
            designation = list[2];
            state = "Official";
        } else if (list.length == 4) {
            email = list[1];
            designation = list[2];
            birth_day = list[3];
            state = "Official_Friend";
        } else {
            nick_name = list[1];
            email = list[2];
            designation = list[3];
            birth_day = list[4];
            state = "Personal";
        }
        count++;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNick_name() {
        return nick_name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public String getState() {
        return state;
    }
}