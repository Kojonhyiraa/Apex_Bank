package Switch;


import Service.UserService;


public class MainSwitch {

    public static void main(String[] args) throws Exception {
        UserService user = new UserService();
        user.register();
    }
}
