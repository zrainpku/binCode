
import com.db.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.google.zxing.WriterException;

/**
 * Servlet implementation class gif
 */
@WebServlet("/gif")
public class gif extends HttpServlet {
	private String recName;    //接收表单提交过来的账户
	 private String recID;     //密码
	 private String viName; 
	 private String viID; 
	 private String time; 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gif() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			   
//			   String name=request.getParameter("myname");
//			   String id=request.getParameter("myid");
//			   BinCode text=new BinCode(name,id);
//			   System.out.println(request.getParameter("mydemo"));
					
//		   System.out.println("Served at: "+request.getContextPath());
//		   request.getRequestDispatcher("/").forward(request,response);
		
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		recName=request.getParameter("recname");
		recID=request.getParameter("recid");
		viName=request.getParameter("viname");
		viID=request.getParameter("viid");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		time=df.format(new Date());
		response.setCharacterEncoding("utf-8");
		/*System.out.println("recname:"+recName+"\n"+
				"recid:"+recID+"\n"+
				"viname:"+viName+"\n"+
				"viid:"+viID+"\n");*/
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().write("<h2>"+recName+ "@"+recID+"成功邀请了"+viName+ "@" +viID+"加入eduroam！ </h2>"+"\n");
		request.setCharacterEncoding("utf-8");
		insertDate() ;
		
		
		
		PrintWriter out=response.getWriter();
		List <HashMap<String,String>> ans=new ArrayList<HashMap<String,String>>();
		try {
			ans=showTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator it=ans.iterator();
		out.println("<html> <table border=\"2\" bgcolor=\"#fedcbd\"> ");
		
		out.println("<tr>");
		out.println("<th>"); out.println("序号"); out.println("</th>");
		out.println("<th>"); out.println("邀请人"); out.println("</th>");
		out.println("<th>"); out.println("邀请人的eduroamID"); out.println("</th>");
		out.println("<th>"); out.println("访问者的姓名"); out.println("</th>");
		out.println("<th>"); out.println("访问者的eduroamID"); out.println("</th>");
		out.println("<th>"); out.println("申请时间"); out.println("</th>");
		out.println("</tr>");
		
		while(it.hasNext()){
			HashMap<String,String> obj=(HashMap<String, String>) it.next();
			out.println("<tr>");
			out.println("<td>"); out.println(obj.get("i")); out.println("</td>");
		out.println("<td>"); out.println(obj.get("a")); out.println("</td>");
		out.println("<td>"); out.println(obj.get("b")); out.println("</td>");
		out.println("<td>"); out.println(obj.get("c")); out.println("</td>");
		out.println("<td>"); out.println(obj.get("d")); out.println("</td>");
		out.println("<td>"); out.println(obj.get("t")); out.println("</td>");
		out.println("</tr>");
		}
		out.println("</table> </html>");
		
	}
	
	public  List <HashMap<String,String>>  showTable() throws SQLException{
	
		  Connection con = null;  //一个连接对象
		  con = (Connection) DB.getcon(con);   //得到一个连接
         
        List <HashMap<String,String>> columns = new ArrayList<HashMap<String,String>>();
         
        try{
            Statement stmt = con.createStatement();
             
            String sql=  "select  * from visite; ";
             
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("i", rs.getString("id"));
                map.put("a", rs.getString("recName"));
                map.put("b", rs.getString("recID"));
                map.put("c", rs.getString("viName"));
                map.put("d", rs.getString("viID"));
                map.put("t", rs.getString("time"));
                columns.add(map);
               
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally{
            con.close();
        }
  return columns;
    }
 
     

	
	
	private void insertDate() {
		  Connection con = null;  //一个连接对象
		  con = (Connection) DB.getcon(con);   //得到一个连接
		  PreparedStatement ps = null;    //用于插入数据
		  //sql语句，向表user里面，插入name和pass的值
		 
		  String sql = "insert into visite(recName,recID,viName,viID,time) values(?,?,?,?,?)";   
		  ps = (PreparedStatement) DB.getpsta(con, sql);
		  try {
		   ps.setString(1, recName);
		   ps.setString(2, recID);
		   ps.setString(3, viName);
		   ps.setString(4, viID);
		   ps.setString(5, time);
		   
		   ps.executeUpdate();
		   ps.close();
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
