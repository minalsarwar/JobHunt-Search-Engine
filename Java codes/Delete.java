//"Job Hunt"
//Job Portal [search engine for jobs] DS Project, Dr. Quratulain Rajput
//Maryam Tauqeer 22862, Minal Sarwar 22756, Ramsha Munawar 22738

/********NOTE: This class and main method is used for DELETION only********/
//The actual main method (GUI) is in GUI class, please run that class

//No GUI has been made for this. It uses the console only. 
//Our project was to build a search engine that would be accessible by users (the general public)
//Deletion is a special that is to be done by the administrator of the portal, hence no GUI has been made for that.
//For  Deletion, we have assumed that specfic jobs would be deleted, i.e a particular city's particular company's
//particular skill's particular job title
//We delete that node from the main doubly linked list, as well as the hash tables 
// To check if this method is working, you can search for a particular job, then delete it, then search for it again


import java.io.FileNotFoundException;
import java.util.Scanner;


public class Delete {
   
    public static void main(String[] args) throws FileNotFoundException {
        
        Hashing h= new Hashing(42, 20, 300 , 6300, 60 , 630);
        JobDoubly jd= FileReading.fileReading();
        
        for (Node temp=jd.head; temp!=null; temp=temp.next){
            h.insertJobAlone(temp);
        }   
        for (Node temp=jd.head; temp!=null; temp=temp.next){
            h.insertSkill_Job(temp);
        }
        for (Node temp=jd.head; temp!=null; temp=temp.next){
            h.insertCity_Skill_Job(temp);
        }
        
        Scanner sc= new Scanner(System.in);
        System.out.println("You are beginning to delete. Press 1 to continue, press 0 to stop");
        String choice=sc.nextLine();
        String city; String comp; String skill; String jobTitle;
        while (choice.compareTo("0")!=0){
            
            System.out.println("Enter city");
            city=sc.nextLine();
            System.out.println("Enter company");
            comp=sc.nextLine();
            System.out.println("Enter skill");
            skill=sc.nextLine();
            System.out.println("Enter job title");
            jobTitle=sc.nextLine();
            
            if (jd.isThere(city, comp, skill, jobTitle)){
                jd.Delete(city, comp, skill, jobTitle);
                h.Delete(city, comp, skill, jobTitle);
            }
            else{
                System.out.println("No such job exists");
            }
            
            System.out.println("Continuing Deletion. Press 1 to continue, press 0 to stop");
            choice=sc.nextLine();
        }
        System.out.println("Deletion completed");  
        
        //To check the number of collisions and occupied cells for every hash table:

//        System.out.println("Collisions Job_a[]="+Hashing.collisionsJob_a+", Occupied cells="+Hashing.occupiedCellsJob_a);
//        System.out.println("Collisions Skill_Job[]="+Hashing.collisionsSkill_Job + ", Occupied cells="+Hashing.occupiedCellsSkill_Job);
//        System.out.println("Collisions Job_null[]="+Hashing.collisionsJob_null+", Occupied cells="+Hashing.occupiedCellsJob_null);
//        System.out.println("Collisions City_n[]="+Hashing.collisionsCity_n + ", Occupied cells="+Hashing.occupiedCellsCity_n);
//        System.out.println("Collisions Skill_n[]="+Hashing.collisionsSkill_n + ", Occupied cells="+Hashing.occupiedCellsSkill_n);
//        System.out.println("Collisions Job_n[]="+Hashing.collisionsJob_n + ", Occupied cells="+Hashing.occupiedCellsJob_n);

    }
    
    
    
    
}
