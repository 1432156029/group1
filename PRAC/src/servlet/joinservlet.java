package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import modle.User;

/**
 * Servlet implementation class joinservlet
 */
@WebServlet("/joinservlet")
public class joinservlet extends HttpServlet {
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

        //��ȡ�û�ע����Ϣ
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phonenumber = req.getParameter("phonenumber");
        String sex = req.getParameter("sex");

        //ʵ����UserDao����
        UserDao userDao = new UserDao();

        if (userDao.userExist(username) == 1) {
            //ʵ����һ��User����
            User user = new User();
            //���û���������Ը�ֵ
            user.setUsername(username);
            user.setPassword(password);
            user.setSex(sex);
            user.setPhoneenumber(phonenumber);
            user.setEmail(email);
            userDao.saveUser(user);
            req.getRequestDispatcher("joinsuccessful.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("joinunsuccessful.jsp").forward(req, resp);
        }

    }

}
