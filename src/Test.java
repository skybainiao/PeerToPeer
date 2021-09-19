import Server.JDBC;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {

        JDBC jdbc = new JDBC();

        //jdbc.addUser("Sonny");
        //jdbc.sendMessage("Chen","Jax","nihao");
        //System.out.println(jdbc.getAllUsers());

        ArrayList<String> strings = new ArrayList<>();
        ResultSet rs = jdbc.getFriends("Jax");
        while (rs.next()){
            String name = rs.getString("friendName");
            strings.add(name);
        }

        System.out.println(strings);



    }
}
