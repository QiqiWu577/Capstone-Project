package Controllers;

import Model.*;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Jason Sy, Qiqi Wu
 */
@WebServlet(name = "ManagerSettings", urlPatterns = "/ManagerSettings")
public class ManagerSettings extends HttpServlet {
    /**
     * Controller to process requests for the Manager Settings page
     *
     * If param is updateDayTemp, change template for that day of the week with new opening or new close time
     * If update, change the shift with new details submitted
     * If add, add new shift template with user requested parameters
     * If delete, remove the selected shift template from system
     *
     * @param request parameter submitted from the jsp page
     * @param response system response to requests
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String update = request.getParameter("update");
        String delete = request.getParameter("delete");
        String add = request.getParameter("add");
        String updateDayTemp = request.getParameter("updateDayTemp");
        DBOperation db = new DBOperation();
        HttpSession session = request.getSession();


        if(updateDayTemp != null){

            String dayOfWeek = request.getParameter("dayOfWeek");
            String openH = request.getParameter("openH");
            String closeH = request.getParameter("closeH");

            db.updateDayTemplate(dayOfWeek,openH,closeH);
            session.setAttribute("message","success");
            response.sendRedirect("ManagerServices?page=settings");

        }else if(update!=null) {

            ShiftTemplate st = new ShiftTemplate();
            DayTemplate dt = new DayTemplate();

            dt.setDayOfWeek(request.getParameter("dayOfWeek"));
            st.setType(request.getParameter("shiftType").charAt(0));
            st.setShiftId(Integer.parseInt(request.getParameter("shiftId")));
            st.setName(request.getParameter("newName"));
            st.setStartTime(request.getParameter("newStart"));
            st.setEndTime(request.getParameter("newEnd"));
            st.setMinNoEmp(Integer.parseInt(request.getParameter("newMinEmp")));
            st.setMaxNoEmp(Integer.parseInt(request.getParameter("newMaxEmp")));
            st.setDayOfWeek(dt);

            db.updateShiftTemplate(st);

            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));
            request.setAttribute("shiftMessage", "Shift has been updated");
            update = null;
            getServletContext().getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerSetting.jsp").forward(request, response);

        }else if(add!=null) {

            ShiftTemplate st = new ShiftTemplate();
            DayTemplate dt = new DayTemplate();

            char type='X';
            String inType = request.getParameter("type");
            if(inType.matches("Front End")) {
                type='S';
            }
            else if(inType.matches("Bar")) {
                type='B';
            }
            else if(inType.matches("Kitchen")) {
                type='K';
            }

            int min = Integer.parseInt(request.getParameter("min"));
            int max = Integer.parseInt(request.getParameter("max"));

            dt.setDayOfWeek(request.getParameter("dayOfWeek"));
            st.setType(type);
            st.setName(request.getParameter("name"));
            st.setStartTime(request.getParameter("start"));
            st.setEndTime(request.getParameter("end"));
            st.setMinNoEmp(min);
            st.setMaxNoEmp(max);
            st.setDayOfWeek(dt);

            db.addShiftTemplate(st);

            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));
            request.setAttribute("shiftMessage", "Shift has been added");
            add = null;
            getServletContext().getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerSetting.jsp").forward(request, response);

        }else if(delete!=null) {

            ShiftTemplate st = new ShiftTemplate(Integer.parseInt(request.getParameter("shiftId")));
            db.deleteShiftTemplate(st);

            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));
            request.setAttribute("shiftMessage", "Shift has been deleted");
            delete = null;
            getServletContext().getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerSetting.jsp").forward(request, response);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
