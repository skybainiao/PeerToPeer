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

        String sql="insert into PTPChat.Message(senderName,receiveName,chatMessage)\n" + "values(?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, senderName);
        preparedStatement.setString(2, receiveName);
        preparedStatement.setString(3,text);

        return preparedStatement.executeUpdate();
    }


    public ResultSet getMessage(String senderName,String receiveName) throws SQLException {
        String sql="select chatMessages from PTPChat.Message where senderName = ? and receiverName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,senderName);
        preparedStatement.setString(2,receiveName);

        return preparedStatement.executeQuery();
    }


    public ResultSet getFriends(String username) throws SQLException {
        String sql="select friendName from PTPChat.Friend where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);

        return preparedStatement.executeQuery();
    }


    public int addUser(String username) throws SQLException {
        String sql="insert into PTPChat.PTPUsr(username,receiveMessageNum) values (?,0)";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1, username);

        return preparedStatement.executeUpdate();
    }


    public int increase(String username) throws SQLException {
        String sql="update PTPUser set receiveMessageNum = receiveMessageNum+1 where username = ?";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,username);

        return preparedStatement.executeUpdate();
    }


}
