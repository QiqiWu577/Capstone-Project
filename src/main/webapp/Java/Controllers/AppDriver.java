package Controllers;

import Model.Employee;

import java.io.*;
import java.util.ArrayList;

public class AppDriver {


    private static String filename ="";

    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            ArrayList<Employee> empList = new ArrayList<Employee>();
            int counter = 0;
            while(line != null) {

                String[] employeeData = line.split(",");
                empList.add(new Employee(counter++,employeeData[0],employeeData[1],employeeData[2],employeeData[3],
                        1,false,true,"",employeeData[4],null,null));
                line = reader.readLine();
            }

            for(Employee e: empList) {
                System.out.println(e.getFirstname());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
