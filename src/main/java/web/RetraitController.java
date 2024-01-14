package web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Client.Client;
import dao.Compte.CompteDaoImpl;
import dao.Operation.OperationDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/retrait")
public class RetraitController extends HttpServlet {
    CompteDaoImpl compteDao;
    OperationDaoImpl operationDao;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Client client = Auth.getLoggedClient(request);
        request.setAttribute("lstcomptes", new CompteDaoImpl().getAllComptesByClient(client.getCin()));
        request.getRequestDispatcher("views/retrait.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if(request.getParameter("numCompte") != null &&  request.getParameter("solde") != null ){
               int numCompte =  Integer.parseInt(request.getParameter("numCompte"));
               double solde = Double.parseDouble(request.getParameter("solde") );
                compteDao = new CompteDaoImpl();
                if(compteDao.soldeCompte(numCompte) >= solde){
                    operationDao = new OperationDaoImpl();
                    operationDao.retrait(solde,numCompte);
                    //response.setContentType("application/json");
                    response.getWriter().write("retrait effectue avec success !");
                }
                else{
                    response.setStatus(400);
                    response.getWriter().write("le montant que vous avez saisi doit être inférieur au solde de ce compte.");
                }

            }
            else{
                response.setStatus(400);
                response.getWriter().write("numero de compte et solde obligatoire !");

            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid numCompte parameter");
        }
    }
}
