package web;

import dao.Client.Client;
import dao.Compte.CompteDaoImpl;
import dao.Operation.OperationDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/virement")
public class VirementController extends HttpServlet {
    CompteDaoImpl compteDao;
    OperationDaoImpl operationDao;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Client client = Auth.getLoggedClient(request);
        request.setAttribute("lstcomptes", new CompteDaoImpl().getAllComptesByClient(client.getCin()));
        request.getRequestDispatcher("views/virement.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
