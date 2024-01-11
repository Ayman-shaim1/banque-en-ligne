package web;

import dao.Client.ClientDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ClientController extends HttpServlet {
    ClientDaoImpl dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao = new ClientDaoImpl();
        request.setAttribute("clients", dao.getAllClients());
        request.getRequestDispatcher("views/clientsList.jsp").forward(request, response);
    }
}
