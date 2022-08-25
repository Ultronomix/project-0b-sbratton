import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

class Worker{
    private final int workerNumber;
    private final String workerName;
    private final int workerSalary;

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

        List<Worker> workerCollection = new ArrayList<Worker>();
        try (Scanner s = new Scanner(System.in)) {
            try (Scanner s1 = new Scanner(System.in)) {
                int di;
                do {
                    System.out.println("1.INSERT Data for Worker(s)");
                    System.out.println("2.DISPLAY Data for Worker(s)");
                    System.out.println("3.SEARCH Data of Worker(s)");
                    System.out.println("4.DELETE Data of Worker(s)");
                    System.out.println("5.UPDATE Data for Worker(s)");
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