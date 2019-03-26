package Controllers;

import Persistance.DBOperation;
import temp.DayTemplate;
import temp.ShiftTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;

@WebServlet(name = "ManagerSettings", urlPatterns = "/ManagerSettings")
public class ManagerSettings extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String update = request.getParameter("update");
        String delete = request.getParameter("delete");
        String add = request.getParameter("add");
        String saveOps = request.getParameter("save");
        DBOperation db = new DBOperation();
        HttpSession session = request.getSession();


        if(saveOps != null){

            boolean notSame = false;

            String monS = request.getParameter("MonS");
            String monE = request.getParameter("MonE");
            String tueS = request.getParameter("TueS");
            String tueE = request.getParameter("TueE");
            String wedS = request.getParameter("WedS");
            String wedE = request.getParameter("WedE");
            String thurS = request.getParameter("ThurS");
            String thurE = request.getParameter("ThurE");
            String friS = request.getParameter("FriS");
            String friE = request.getParameter("FriE");
            String saturS = request.getParameter("SaturS");
            String saturE = request.getParameter("SaturE");
            String sunS = request.getParameter("SunS");
            String sunE = request.getParameter("SunE");

            if((monS != null && monE != null) || (monS.equals("") && monE.equals(""))){

                LocalTime st = LocalTime.parse(monS);
                LocalTime et = LocalTime.parse(monE);

                notSame = notTheSameDay(st,et);
                db.addDayTemplate("Monday",monS,monE,notSame);
            }else if((tueS != null && tueE != null) || (tueS.equals("") && tueE.equals(""))){

                LocalTime st = LocalTime.parse(tueS);
                LocalTime et = LocalTime.parse(tueE);

                notSame = notTheSameDay(st,et);
                db.addDayTemplate("Tuesday",monS,monE,notSame);
            }else if((wedS != null && wedE != null) || (wedS.equals("") && wedE.equals(""))){

                LocalTime st = LocalTime.parse(wedS);
                LocalTime et = LocalTime.parse(wedE);

                notSame = notTheSameDay(st,et);
                db.addDayTemplate("Wednesday",monS,monE,notSame);
            }else if((thurS != null && thurE != null) || (thurS.equals("") && thurE.equals(""))){

                LocalTime st = LocalTime.parse(thurS);
                LocalTime et = LocalTime.parse(thurE);

                notSame = notTheSameDay(st,et);
                db.addDayTemplate("Thursday",monS,monE,notSame);
            }else if((friS != null && friE != null) || (friS.equals("") && friE.equals(""))){

                LocalTime st = LocalTime.parse(friS);
                LocalTime et = LocalTime.parse(friE);

                notSame = notTheSameDay(st,et);
                db.addDayTemplate("Friday",monS,monE,notSame);
            }else if((saturS != null && saturE != null) || (saturS.equals("") && saturE.equals(""))){

                LocalTime st = LocalTime.parse(saturS);
                LocalTime et = LocalTime.parse(saturE);

                notSame = notTheSameDay(st,et);
                db.addDayTemplate("Saturday",monS,monE,notSame);
            }else if((sunS != null && sunE != null) || (sunS.equals("") && sunE.equals(""))){

                LocalTime st = LocalTime.parse(sunS);
                LocalTime et = LocalTime.parse(sunE);

                notSame = notTheSameDay(st,et);
                db.addDayTemplate("Sunday",monS,monE,notSame);
            }

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

        }else if(delete!=null) {

            temp.ShiftTemplate st = new temp.ShiftTemplate(Integer.parseInt(request.getParameter("shiftId")));
            db.deleteShiftTemplate(st);

            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));
            request.setAttribute("shiftMessage", "Shift has been deleted");
            delete = null;
            getServletContext().getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerSetting.jsp").forward(request, response);

        }else if(add!=null) {

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

    public boolean notTheSameDay(LocalTime s, LocalTime e){
        boolean check = false;

        if(s.compareTo(e)>=0){
            check = true;
        }

        return check;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
