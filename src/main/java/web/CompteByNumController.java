package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Compte.Compte;
import dao.Compte.CompteDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/compteByNum")
public class CompteByNumController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Compte compte;
        try {
            if(request.getParameter("numCompte") != null) {
                int numCompte = Integer.parseInt(request.getParameter("numCompte"));
                compte = new CompteDaoImpl().getCompte(numCompte);
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonOperations = objectMapper.writeValueAsString(compte);
                response.setContentType("application/json");
                response.getWriter().write(jsonOperations);
            }


        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid numCompte parameter");
        }
    }
}
