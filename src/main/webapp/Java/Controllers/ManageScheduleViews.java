package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageScheduleViews", urlPatterns = "/ManageScheduleViews")
public class ManageScheduleViews extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String change = request.getParameter("message");

        if(change == null || change.equals("")){

            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManageSerSched.jsp").forward(request,response);
        }else if(change.equals("server")){

            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManageSerSched.jsp").forward(request,response);

        }else if(change.equals("bartender")){

            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManageBarSched.jsp").forward(request,response);

        }else if(change.equals("kitchen")){

            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManageKitSched.jsp").forward(request,response);

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
