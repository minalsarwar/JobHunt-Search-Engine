//"Job Hunt"
//Job Portal [search engine for jobs] DS Project, Dr. Quratulain Rajput
//Maryam Tauqeer 22862, Minal Sarwar 22756, Ramsha Munawar 22738
//This is the class for data type Job

public class Job {
    //search
    String jobTitle;
    String city;
    String qual; //qualification
    
    //display
    String companyName;
    String skill;
    String email; //contact details
    String jobDesc; //job description
    String Date; //date
    String exp; //experience
    
    Job(String companyName, String jobTitle, String city, String skill, String qual, String email, String jobDesc, String Date, String exp){
        this.companyName=companyName;
        this.jobTitle=jobTitle;
        this.city=city;
        this.skill=skill; 
        this.qual=qual;
        this.email=email;
        this.jobDesc=jobDesc;
        this.Date=Date;
        this.exp=exp;
    }
            
}
