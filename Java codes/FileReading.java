//"Job Hunt"
//Job Portal [search engine for jobs] DS Project, Dr. Quratulain Rajput
//Maryam Tauqeer 22862, Minal Sarwar 22756, Ramsha Munawar 22738
//This is the class that contains the class for reding the text file that contains details of the job records

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class FileReading {
    public static JobDoubly jd;
    public static JobDoubly fileReading() throws FileNotFoundException{
//        int count=0;
        try{
            
            //add the class path where the text file is saved
            File file=new File("C://Users//Home//Desktop/JOBDATA_FINAL.txt");
            Scanner sc= new Scanner(file);

            String jobTitle="";
            String city="";
            String qual="";//qualification

            String companyName="";
            String skill="";
            String email=""; //contact details
            String jobDesc=""; //job description
            String Date=""; //date
            String exp=""; //experience
            String line="";
            Job j;
            jd= new JobDoubly();
            
            
            while (sc.hasNextLine()){
                line= sc.nextLine();
                if (!line.isEmpty()) {
                    companyName= line;
                    jobTitle= sc.nextLine();
                    city= sc.nextLine();
                    skill= sc.nextLine();
                    qual= sc.nextLine();
                    email= sc.nextLine();
                    jobDesc = sc.nextLine();
                    Date= sc.nextLine();
                    exp=  sc.nextLine();
                    j= new Job(companyName,  jobTitle,  city,  skill,  qual,  email, jobDesc, Date, exp);
                    jd.insert(j);
//                    count++;
                }
                 
              
            }
            sc.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
//        System.out.println("Count="+count);
        return jd;
    }
    
}
