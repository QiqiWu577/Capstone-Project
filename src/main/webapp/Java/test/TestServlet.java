package test;

import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "TestServlet",urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String logout = request.getParameter("logout");

        if(logout!=null){

            //destroy the seesion
            HttpSession session = request.getSession();
            session.invalidate();

            request.setAttribute("message", "Logged out");
            request.getRequestDispatcher("/WEB-INF/test/login.jsp").forward(request, response);
        }else if(username == null || password == null){

            request.getRequestDispatcher("/WEB-INF/test/login.jsp").forward(request, response);
        }else if(username.equals("") || password.equals("")){
            System.out.println(577);
            request.setAttribute("message", "Both values are required!");
            request.getRequestDispatcher("/WEB-INF/test/login.jsp").forward(request, response);
        }else{

            if(username.equals("577") && !password.equals("")){

                DBOperation dbOperation = new DBOperation();
                dbOperation.run();
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
