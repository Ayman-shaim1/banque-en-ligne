package web;

import dao.Client.Client;
import dao.Client.ClientDaoImpl;
import dao.Compte.Compte;
import dao.Compte.CompteDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.Metier;

import java.io.IOException;
import java.util.List;

@WebServlet("/solde")
public class SoldeController  extends HttpServlet {
    ClientDaoImpl dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = Auth.getLoggedClient(request);
        double totalSoldes = new Metier().totalSoldeComptes(client.getCin());
        List<Compte> lstcomptes = new CompteDaoImpl().getAllComptesByClient(client.getCin());

        request.setAttribute("totalSoldes", totalSoldes);
        request.setAttribute("lstcomptes", lstcomptes);

        request.getRequestDispatcher("views/solde.jsp").forward(request, response);

        response.getWriter().write(String.valueOf(totalSoldes));
    }
}
