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
        try {
            if(request.getParameter("numCompteExp") != null && request.getParameter("numCompteDest") != null &&  request.getParameter("solde") != null ){
                int numCompteExp =  Integer.parseInt(request.getParameter("numCompteExp"));
                int numCompteDest =  Integer.parseInt(request.getParameter("numCompteDest"));

                double solde = Double.parseDouble(request.getParameter("solde") );
                compteDao = new CompteDaoImpl();
                if(compteDao.soldeCompte(numCompteExp) >= solde){
                    operationDao = new OperationDaoImpl();
                    operationDao.virement(solde,numCompteExp,numCompteDest);
                    response.getWriter().write("virement effectue avec success !");
                }
                else{
                    response.setStatus(400);
                    response.getWriter().write("le montant que vous avez saisi doit être inférieur au solde de ce compte.");
                }

            }
            else{
                response.setStatus(400);
                response.getWriter().write("numero de compte expéditeur, destinataire et solde obligatoire !");

            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid numCompte parameter");
        }
    }


}
