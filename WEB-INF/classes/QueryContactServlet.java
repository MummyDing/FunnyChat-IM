package Servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import Tookit.OperDataBase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class QueryContactServlet extends HttpServlet{
    PrintWriter out = null;
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
        out = response.getWriter();
       /*
        * 获取参数
        * */ 
       String username = request.getParameter("userId");
       /*
        * 查询数据库
        * */
       String sql = "select * from Contact where userId=?";
       OperDataBase.init("FunnyChat",sql);
       OperDataBase.addSQLData(1,username);
       ResultSet rs = OperDataBase.exeGetDataSQL();
       /*
        * 构造json数据返回
        * */
       JSONArray jsonArray = new JSONArray();
       try{
       while(rs.next()){
           JSONObject jsonObject = new JSONObject();
           jsonObject.put("contactId",rs.getString("contactId"));
           jsonObject.put("contactname",rs.getString("contactname"));
	       jsonArray.add(jsonObject);
       }
       }catch(Exception e){
       }finally{
           out.print(jsonArray);
       }
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
        doPost(request,response);
    }

}
