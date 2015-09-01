package Servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import Tookit.OperDataBase;
public class LoginServlet extends HttpServlet{
    PrintWriter out = null;
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
        out = response.getWriter();
       /*
        * 获取参数
        * */ 
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       /*
        * 查询数据库
        * */
       String sql = "select * from User where username=? and password=?";
       OperDataBase.init("FunnyChat",sql);
       OperDataBase.addSQLData(1,username);
       OperDataBase.addSQLData(2,password);
       ResultSet rs = OperDataBase.exeGetDataSQL();
       /*
        * 查询到了 则返回token 否则返回错误码
        * */
       try{
       if(rs.next()){
           out.print(rs.getString("token"));
       }else{
           out.print("404");
       }
       }catch(Exception e){
           out.print("404");
       }
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
        doPost(request,response);
    }

}
