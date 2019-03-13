package mum.wap.dao;


import mum.wap.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {

    private static Map<String, User> users = new HashMap<>();
    static{
        for (int i = 1; i < 10; i++){
            String userName = String.format("user_%s", 1);
            String password =  "123456";
            User user = new User(userName, password);
            users.put(userName, user);
        }
    }

    public User login(String userName, String password){

        String p = "123456";
        String pp = "123456";
        if(p == password){
            int a = 0;
        }else{
            int b = 0;
        }
        if(users.containsKey(userName)){
           User user = users.get(userName);
           if(password.equals(user.getPassword())) {
                return new User(user);
            }
        }
        return null;
    }
}
