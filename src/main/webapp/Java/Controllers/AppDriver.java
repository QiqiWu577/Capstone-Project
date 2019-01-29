package Controllers;

import Model.Employee;

import java.io.*;
import java.util.ArrayList;

public class AppDriver {



    private static String filename ="./src/main/webapp/res/employees.txt";

    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            ArrayList<Employee> empList = new ArrayList<Employee>();
            while(line != null) {

                String[] employeeData = line.split(";");
                empList.add(new Employee(Integer.parseInt(employeeData[0]),employeeData[1],employeeData[2],employeeData[3],employeeData[4],
                        Integer.parseInt(employeeData[5]),Boolean.parseBoolean(employeeData[6]),Boolean.parseBoolean(employeeData[7]),employeeData[8],employeeData[9],null,null));
                line = reader.readLine();
            }

            for(Employee e: empList) {
                System.out.println(e.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
