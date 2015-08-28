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

public class CreateGroupServlet extends HttpServlet{
    PrintWriter out = null;
    String url = "https://api.cn.ronghub.com/group/create.json";
    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
    PostData postData = new PostData();
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
        out = response.getWriter();
        /*
         * 获取参数
         * */
        String userId = request.getParameter("userId");
        String groupname = request.getParameter("groupname");
        String groupId = request.getParameter("groupId");
        /*
         * 查询数据库
         * */
        String sql = "select * from Groups where groupId=?";
        OperDataBase.init("FunnyChat",sql);
        OperDataBase.addSQLData(1,groupId);
        ResultSet rs = OperDataBase.exeGetDataSQL();
        /*
         * 查询到了则不能创建返回错误码 否则创建
         * */
        try{
        if(rs.next()){
            out.print("{\"code\":404}"); return;
        }
        }catch(Exception e){
            out.print("{\"code\":404}"); return;
        }finally{
            OperDataBase.closeConn(); //关闭数据库        
        }
        /*
         * 没有查询到则创建
         * */
        //加入到Groups
        sql = "insert into Groups(groupname,groupId)values(?,?)";
        OperDataBase.init("FunnyChat",sql);
        OperDataBase.addSQLData(1,groupname);
        OperDataBase.addSQLData(2,groupId);
        try{
            if(OperDataBase.exeSQL() == -2){
                out.print("{\"code\":404}"); return;
            }
        }catch(Exception e){
            out.print("{\"code\":404}"); return;
        }finally{
            OperDataBase.closeConn(); //关闭数据库
        }
        //加入到GroupRS
        sql ="insert into GroupRS(userId,groupId,type)values(?,?,?)";
        OperDataBase.init("FunnyChat",sql);
        OperDataBase.addSQLData(1,userId);
        OperDataBase.addSQLData(2,groupId);
        OperDataBase.addSQLData(3,"owner");
        try{
            if(OperDataBase.exeSQL() == -2){
                out.print("{\"code\":404}"); return;
            }
        }catch(Exception e){
            out.print("{\"code\":404}"); return;
        }finally{
            OperDataBase.closeConn(); //关闭数据库
        }
        /*
         * 创建成功  向融云服务器发送建群请求
         * */
        nameValuePair.add(new BasicNameValuePair("userId",userId));
        nameValuePair.add(new BasicNameValuePair("groupId",groupId));
        nameValuePair.add(new BasicNameValuePair("groupName",groupname));
        String res = postData.getData(url,nameValuePair);
        out.print(res);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        doPost(request,response);
    }

}
