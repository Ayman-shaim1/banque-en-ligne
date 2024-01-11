package web;

import dao.Client.Client;
import dao.Client.ClientDaoImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class Auth {

     public static boolean checkLoginStatus(HttpServletRequest request) {
        // Check if the "user" cookie is present
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user"))
                    return true;
            }
        }
        return false;
    }
    static  public Client getLoggedClient(HttpServletRequest request){
        // Check if the "user" cookie is present
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user"))
                    return  new ClientDaoImpl().
                            getClient(cookie.getValue());

            }
        }
        return null;
    }
}
