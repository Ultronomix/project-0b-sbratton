package com.p0a.cameramanbrayton.workers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import org.postgresql.Driver;

class Worker{
    private final int workerNumber; // Worker Number will begin with 100x, whereas x will be a number beginning with 1 to ∞
    private final String workerName; // Worker Name should be Full First and Last with Middle intial.
    private final int workerSalary; // Worker Salary will be the Annual yearly salary.

    Worker(int workerNumber, String workerName, int workerSalary) {
        this.workerNumber = workerNumber;
        this.workerName = workerName;
        this.workerSalary = workerSalary;
    }

    public int getWorkerNumber(){
        return workerNumber;
    }

    public int getWorkerSalary(){
        return workerSalary;
    }

    public String getWorkerName(){
        return workerName;
    }

    public String toString(){
        return "Worker's Number:" + workerNumber + "/  " + "Worker's Name:" + workerName + "/  " + "Worker's Annual Salary:" + workerSalary;
    }

}

class EmpApp {
    public EmpApp(int workerNumber, String workerName, int workerSalary) {

    }

    public static void main(String[] args) {

        String dbUrl = "jdbc:postgresql://java-angular-220815.cuuno41ql544.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=workersapp";
        String dbUsername = "scott";
        String dbPassword = "revature";
        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if (conn != null) {
            System.out.println("Connection successful!");
            }
        } catch (SQLException e) {
            System.err.println("Could not establish a connection");
            e.printStackTrace();
        }
        List<Worker> workerCollection = new ArrayList<Worker>();
        try (Scanner s = new Scanner(System.in)) {
            try (Scanner s1 = new Scanner(System.in)) {
                int di;
                do {
                    System.out.println("1.INSERT Data for Worker(s)"); // User will Insert(create number) 3 varibles, Workers Number, Workers name, Workers Salary
                    System.out.println("2.DISPLAY Data for Worker(s)"); // User can display all Created Workers
                    System.out.println("3.SEARCH Data of Worker(s)"); // User can search database for Workers
                    System.out.println("4.DELETE Data of Worker(s)"); // User can Delete any Worker in the system
                    System.out.println("5.UPDATE Data for Worker(s)");// User can update any information on Worker
                    System.out.println("Enter 0 to Exit");
                    System.out.print("Enter Number of Your Choice: ");
                    di = s.nextInt();

                    switch (di) {
                        case 1:
                            System.out.print("Enter Worker's Number starting with 100x : ");
                            int workerNumber = s.nextInt();
                            System.out.print("Enter Worker's Name: ");
                            String workerName = s1.nextLine();
                            System.out.print("Enter Worker's Salary: ");
                            int workerSalary = s.nextInt();

                            workerCollection.add(new Worker(workerNumber, workerName, workerSalary));
                            break;
                        case 2:
                            System.out.println("-------------------------");
                            Iterator<Worker> iterator = workerCollection.iterator();
                            while (iterator.hasNext()) {
                                Worker worker = iterator.next();
                                System.out.println(worker);
                            }
                            System.out.println("-------------------------");
                            break;
                        case 3:
                            boolean found = false;
                            System.out.println("Enter Worker's Number to Search:");
                            workerNumber = s.nextInt();
                            System.out.println("-------------------------");
                            iterator = workerCollection.iterator();
                            while (iterator.hasNext()) {
                                Worker worker = iterator.next();
                                if (worker.getWorkerNumber() == workerNumber) {
                                    System.out.println(worker);
                                    found = true;
                                }
                            }
                            if (!found) {
                                System.out.println("Worker's Number Not Found");
                            }
                            System.out.println("-------------------------");
                            break;
                        case 4:
                            found = false;
                            System.out.println("Enter Worker's Number to Delete:");
                            workerNumber = s.nextInt();
                            System.out.println("-------------------------");
                            iterator = workerCollection.iterator();
                            while (iterator.hasNext()) {
                                Worker worker = iterator.next();
                                if (worker.getWorkerNumber() == workerNumber) {
                                    iterator.remove();
                                    found = true;
                                }
                            }
                            if (!found) {
                                System.out.println("Worker's Number Not Found");
                            } else {
                                System.out.println("Worker's Number has been Deleted Successfully!");
                            }
                            System.out.println("-------------------------");
                            break;
                        case 5:
                            found = false;
                            System.out.println("Enter Worker's Number to Update Information:");
                            workerNumber = s.nextInt();
                            ListIterator<Worker> listIterator = workerCollection.listIterator();
                            while (listIterator.hasNext()) {
                                Worker worker = listIterator.next();
                                if (worker.getWorkerNumber() == workerNumber) {
                                    System.out.print("Enter New Worker Name:");
                                    workerName = s1.nextLine();

                                    System.out.print("Enter New Worker Salary:");
                                    workerSalary = s.nextInt();
                                    listIterator.set(new Worker(workerNumber, workerName, workerSalary));
                                    found = true;
                                }
                            }
                            if (!found) {
                                System.out.println("Worker's Number Not Found");
                            } else {
                                System.out.println("Worker's Number has been Updated Successfully!");
                            }
                            break;

                    }
                }while (di != 0) ;
            }
        }
    }
}