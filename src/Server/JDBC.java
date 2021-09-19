package Server;

import java.sql.*;

public class JDBC {

    private static String driver = "org.postgresql.Driver";
    private static String url ="jdbc:postgresql://localhost:5432/postgres";
    private static String user = "postgres";
    private static String password = "dsajio";
    private Connection connection;

    public JDBC()throws Exception{
        Class.forName(driver);
        connection = DriverManager.getConnection(url,user,password);
        System.out.println("Connected to database : "+connection.getCatalog());
    }


    public int sendMessage(String senderName,String receiveName,String text) throws SQLException {

        String sql="insert into PTPChat.Message(senderName,receiveName,chatMessages)\n" + "values(?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, senderName);
        preparedStatement.setString(2, receiveName);
        preparedStatement.setString(3,text);

        return preparedStatement.executeUpdate();
    }


    public ResultSet getMessage(String senderName,String receiveName) throws SQLException {
        String sql="select chatMessages\n" + "from PTPChat.Message\n" +
                "where senderName = ? and receiveName = ?\n" +
                "or senderName = ? and receiveName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,senderName);
        preparedStatement.setString(2,receiveName);
        preparedStatement.setString(3,receiveName);
        preparedStatement.setString(4,senderName);

        return preparedStatement.executeQuery();
    }


    public ResultSet getFriends(String username) throws SQLException {
        String sql="select friendName from PTPChat.Friend where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);

        return preparedStatement.executeQuery();
    }


    public int addUser(String username) throws SQLException {
        String sql="insert into PTPChat.PTPUser(username,receiveMessageNum) values (?,0);";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, username);

        return preparedStatement.executeUpdate();
    }


    public ResultSet getAllUsers() throws SQLException {
        String sql="select username\n" + "from PTPChat.PTPUser";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        return preparedStatement.executeQuery();
    }


    public int increase(String username) throws SQLException {
        String sql="update PTPChat.PTPUser set receiveMessageNum = receiveMessageNum+1 where username = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,username);

        return preparedStatement.executeUpdate();
    }


    public ResultSet getUnreadNum(String username) throws SQLException {
        String sql="select receiveMessageNum\n" + "from PTPChat.PTPUser\n" + "where username = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,username);

        return preparedStatement.executeQuery();
    }



}
