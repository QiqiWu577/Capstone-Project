package Controllers;

import Model.DayTemplate;
import Model.ShiftTemplate;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ManagerSettings", urlPatterns = "/ManagerSettings")
public class ManagerSettings extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String update = request.getParameter("update");
        String delete = request.getParameter("delete");
        String add = request.getParameter("add");
        DBOperation db = new DBOperation();
        HttpSession session = request.getSession();

        if(update!=null) {

            temp.ShiftTemplate st = new temp.ShiftTemplate();
            temp.DayTemplate dt = new temp.DayTemplate();

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

        }

        if(delete!=null) {

            temp.ShiftTemplate st = new temp.ShiftTemplate(Integer.parseInt(request.getParameter("shiftId")));
            db.deleteShiftTemplate(st);

            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));
            request.setAttribute("shiftMessage", "Shift has been deleted");
            delete = null;
            getServletContext().getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerSetting.jsp").forward(request, response);

        }

        if(add!=null) {

            temp.ShiftTemplate st = new temp.ShiftTemplate();
            temp.DayTemplate dt = new temp.DayTemplate();

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
//            st.setShiftId(Integer.parseInt(request.getParameter("shiftId")));
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

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
