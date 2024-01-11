package web;
import dao.Client.ClientDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    ClientDaoImpl dao;
    public void init() {
       dao = new ClientDaoImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cin = request.getParameter("cin");
        String password = request.getParameter("password");
        boolean isValidUser = authenticateUser(cin, password);
        if (isValidUser) {
            Cookie userCookie = new Cookie("user", cin);
            userCookie.setMaxAge(30 * 60);
            userCookie.setPath("/");
            response.addCookie(userCookie);
            System.out.println("User authenticated successfully. Setting cookie...");
            // Redirect to a success page or perform further actions
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("err", "email ou mot de passe incorrect !");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        boolean isLoggedIn =  Auth.checkLoginStatus(httpRequest);
        if (isLoggedIn)
            request.getRequestDispatcher("/index.jsp").forward(request, response);
         else
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);

    }

    private boolean authenticateUser(String cin, String password) {
        return dao.findClient(cin,password);
    }

}
