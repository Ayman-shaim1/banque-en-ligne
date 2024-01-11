package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Client.Client;
import dao.Compte.CompteDaoImpl;
import dao.Operation.Operation;
import dao.Operation.OperationDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/operationsByCompte")
public class OperationsByCompteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Operation> operations = null;
        Client client = Auth.getLoggedClient(request);
        try {

            if(request.getParameter("numCompte") != null) {
                int numCompte = Integer.parseInt(request.getParameter("numCompte"));
                operations = new OperationDaoImpl().getOperationByCompte(numCompte);
            }
            else
                operations = new OperationDaoImpl().getOperationsByClient(client.getCin());

            // Convert the list to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOperations = objectMapper.writeValueAsString(operations);

            response.setContentType("application/json");

            response.getWriter().write(jsonOperations);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid numCompte parameter");
        }
    }
}
