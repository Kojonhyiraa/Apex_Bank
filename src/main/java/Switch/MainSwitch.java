package Switch;


import Model.User;
import Service.UserService;


public class MainSwitch {

    public static void main(String[] args){
        UserService user = new UserService();
        user.entryPoint();

    }
}
