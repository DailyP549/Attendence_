package project.number1;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc= new Scanner(System.in);
    static Map <String,List<Boolean>>attendenceMap=new HashMap<>();

    public static void main(String[] args) {
        int choice;
        do{
            System.out.println("STUDENT ATTENDENCE MANAGER MENU:");
            System.out.println("1. ADD STUDENT");
            System.out.println("2. Mark Today's Attendence ");
            System.out.println("3. view Attendence  Summary");
            System.out.println("0. Exit ");
            System.out.println("Enter your choice: ");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1: addStudent();break;
                case 2: markAttendence();break;
                case 3:viewSummary();break;
                case 0:
                    System.out.println("existing....");break;

                default:
                    System.out.println("invalid choice");
            }
        }while(choice!=0);
    }
    static  void addStudent(){
        System.out.println("Enter student name:");
        String name=sc.nextLine();
        if(attendenceMap.containsKey(name)){
            System.out.println("student already exist");
        }else{
            attendenceMap.put(name,new ArrayList<>());
            System.out.println("Student"+name+"added.");
        }
    }
    static void markAttendence(){
        if (attendenceMap.isEmpty()){
            System.out.println("No student to mark attendence for.");
            return;
        }
        System.out.println("Mark attendence (p=present,A=absent):");
        for(String name: attendenceMap.keySet()){
            System.out.println(name+":");
            String input=sc.nextLine().trim().toUpperCase();
            boolean present =input.equals("P");
            attendenceMap.get(name).add(present);

        }
        System.out.println("Attendence recorded");
    }
    static void viewSummary(){
        if (attendenceMap.isEmpty()){
            System.out.println("no student data available");
            return;
        }
        System.out.println("\n Attendence Summary:");
        System.out.printf("%-20s %-10s %-10s %-10s\n","Student","Present","Total","Percentage");
        for(Map.Entry<String, List<Boolean>>entry:attendenceMap.entrySet()){
            String name=entry.getKey();
            List<Boolean>attendence=entry.getValue();
            int total = attendence.size();
            long presentDays=attendence.stream().filter(p->p).count();
            double percentage =(total==0)?0:(presentDays*100.0/total);
            System.out.printf("%-20s %-10d %-10d %-10.2f%%\n",name,presentDays,total,percentage);
        }
    }
}