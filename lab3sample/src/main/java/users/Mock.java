package users;

import java.util.ArrayList;
import java.util.List;

public class Mock {
    private List<User> userList = new ArrayList<>();

    public Mock(){
        this.userList.add(new User(0, "Roman", 40));
        this.userList.add(new User(1,"Olena",24));
        this.userList.add(new User(2,"Stephan",19));
    }
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}