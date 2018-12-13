package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import modle.User;
import utils.DataBaseUtil;

/**
 * Servlet implementation class changepasswordservlet
 */
@WebServlet("/changepasswordservlet")
public class changepasswordservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doPost(req, resp);
	    }

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	resp.setContentType("text/html;charset=utf-8");
	        req.setCharacterEncoding("utf-8");

	        String phonenumber = req.getParameter("phonenumber");
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        Connection conn = DataBaseUtil.getConn();
	        UserDao userDao = new UserDao();
	        //���������ѯ�û�
	        User user  = userDao.changpsw(username, phonenumber);
	        //�ж�user�Ƿ�Ϊ��
	        if (user != null) {


	    	        try {
		    	        String sql = "UPDATE USERS SET password=? where username=?";
	                PreparedStatement ps = conn.prepareStatement(sql);
	                ps.setString(1, password);
	                ps.setString(2, username);

	                //ִ�и��²���
	                ps.executeUpdate();

	                //�ͷ���Դ
	                ps.close();
	
		            }catch (SQLException e) 
	    	        {
	                    e.printStackTrace();
	    	        }
	            //ת������������ҳ��

		            req.getRequestDispatcher("changepswsuccessful.jsp").forward(req, resp);
	            }else{
		            //��¼ʧ��
		            req.getRequestDispatcher("changepswunsuccessful.jsp").forward(req, resp);}
	    }
}
	            /**
	             response.sendRedirect(url)��ת��ָ����URL��ַ������һ���µ�request������Ҫ���ݲ���ֻ����url��Ӳ�
	             �����磺
	             url?id=1.
	             request.getRequestDispatcher(url).forward(request,response)��ֱ�ӽ�����ת����ָ��URL�����Ը�����
	             �ܹ�ֱ�ӻ����һ����������ݣ�Ҳ����˵��������ת����request����ʼ�մ��ڣ��������´�������
	             sendRedirect()���½�request����������һ��request�е����ݻᶪʧ��
	             */

	    

