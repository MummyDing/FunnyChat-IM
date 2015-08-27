package Tookit;
import java.sql.*;
public class OperDataBase{

    static Connection con = null;
    static PreparedStatement psmt;
   public static void init(String db,String sql){
        openConn(db);
        getPreparedStatment(sql);
    }
   public static void openConn(String database){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,"root","root");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void closeConn(){
        try{
            psmt.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void  getPreparedStatment(String sql){
        try{
        psmt = (PreparedStatement)con.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void addSQLData(int id,String value){
        try{
            psmt.setString(id,value);      
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //增&改&删
   public  static int  exeSQL(){
        int i = -2;
        try{
           i= psmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return i;
    }       
   //查
    public static ResultSet exeGetDataSQL(){
        ResultSet rs = null;
        try{
           rs= psmt.executeQuery();
        }catch(Exception e){        
           e.printStackTrace();
        }
        return rs;
    }
}
