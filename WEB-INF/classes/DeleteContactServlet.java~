package Servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import Tookit.OperDataBase;
import Tookit.PostData;

public class AddContactServlet extends HttpServlet{
    PrintWriter out = null;
    PostData postData = new PostData();
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
        out = response.getWriter();
        /*
         * 获取参数
         * */
        String userId = request.getParameter("userId");
        String contactname = request.getParameter("contactname");
        /*
         * 查询数据库
         * */
        String sql = "select * from User where username=?";
        OperDataBase.init("FunnyChat",sql);
        OperDataBase.addSQLData(1,contactname);
        ResultSet rs =OperDataBase.exeGetDataSQL();
        /*
         * 查询不到则返回错误代码,否则加入
         * */
	    String contactId = null;
        try{
            if(rs.next()==false){
            //返回错误代码 return;
            out.print("{\"code\":404}");return;
            }else{
    	    contactId=rs.getString("userid");
	    }
        }catch(Exception e){
            out.print("{\"code\":404}");return;
        }finally{
            OperDataBase.closeConn(); //关闭数据库
        }

        /*
         *查询是否已经添加
         * */
	sql = "select * from Contact where userId=? and contactId =?";
        OperDataBase.init("FunnyChat",sql);
        OperDataBase.addSQLData(1,userId);
	OperDataBase.addSQLData(2,contactId);
        rs =OperDataBase.exeGetDataSQL();
        /*
         * 查询到了则返回错误代码,否则加入
         * */
        try{
            if(rs.next()){
            //返回错误代码 return;
            out.print("{\"code\":404}");return;
            }
        }catch(Exception e){
            out.print("{\"code\":404}");return;
        }finally{
            OperDataBase.closeConn(); //关闭数据库
        }
	/*
	 *自己不能添加自己为好友
	 */
	if(userId.equals(contactId)){
	    out.print("{\"code\":404}");return;
	}
        /*
         * 更新数据库
         * */
        sql ="insert into Contact(userId,contactId,contactname)values(?,?,?)";
        OperDataBase.init("FunnyChat",sql);
        OperDataBase.addSQLData(1,userId);
        OperDataBase.addSQLData(2,contactId);
    	OperDataBase.addSQLData(3,contactname);
        try{
            if(OperDataBase.exeSQL() == -2){
                out.print("{\"code\":404}");return;
            }else{
	        	out.print("{\"code\":200}");return;
	    }
        }catch(Exception e){
            out.print("{\"code\":404}");return;
        }finally{
            OperDataBase.closeConn();
        }
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        doPost(request,response);
    }
}
