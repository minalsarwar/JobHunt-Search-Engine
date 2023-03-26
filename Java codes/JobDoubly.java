//"Job Hunt"
//Job Portal [search engine for jobs] DS Project, Dr. Quratulain Rajput
//Maryam Tauqeer 22862, Minal Sarwar 22756, Ramsha Munawar 22738
//This is the class for a Doubly Linked List that contains 100 jobs

public class JobDoubly {
    Node head;
    Node tail;
    
    //O(1)
    //Insertion of a node that contains data of type Job
    public void insert(Job i){
        Node newNode= new Node(i);
        
        if (head==null){ //if newNode is the 1st node
            head=newNode;
            newNode.prev=null;
        }
        else{
            newNode.next=head;
            head.prev=newNode;
            head=newNode;
        }
    }
    
    //O(n)
    public String print(){ // print the content of all nodes separated by a new line
        String str="";
        for (Node i=head; i!=null; i=i.next){
            if (i.job.Date.compareTo("/")!=0){
                str+="CompanyName: "+i.job.companyName+"\nCity: "+i.job.city+"\nSkill: "+i.job.skill+"\nJobTitle: "+i.job.jobTitle+"\nQualification: "+i.job.qual+"\nEmail: "+i.job.email+"\nJob Description: "+i.job.jobDesc+"\nDate: "+i.job.Date+"\nExperience: "+i.job.exp+" \n\n";
            }
        }
        return str;
    }
    
    //O(n)
    public int length(){
        Node temp=head;
        int count=0;
        while (true){
            if (temp==null){
                break;
            }
            count++;
            temp=temp.next;  
        }
        return count;
    }
    
    //O(n)
    public Boolean isThere(String city, String comp, String skill, String jobTitle){
        Node temp=head;

        while (true){
            if (temp==null || (temp.job.companyName.compareTo(comp)==0 && temp.job.skill.compareTo(skill)==0 && temp.job.jobTitle.compareTo(jobTitle)==0 && temp.job.city.compareTo(city)==0)){
                break;
            }
            temp=temp.next;  
        }
        
        if (temp==null){
            return false;
        }
        else{
            return true;
        } 
    }
    
    //O(n)
    public void Delete(String city, String comp, String skill, String jobTitle){
        
        if (!this.isThere(city, comp, skill, jobTitle)){
            return;
        }
        else{
            Node temp=head;

            while (true){
                if (temp==null || (temp.job.companyName.compareTo(comp)==0 && temp.job.skill.compareTo(skill)==0 && temp.job.jobTitle.compareTo(jobTitle)==0 && temp.job.city.compareTo(city)==0)){
                    break;
                }
                temp=temp.next;  
            }

            if (temp==head&&head.next==null){
                temp.job.Date="/"; temp.job.city="/"; temp.job.companyName="/"; temp.job.email="/"; temp.job.email="/"; temp.job.exp="/";
                temp.job.jobDesc="/"; temp.job.jobTitle="/"; temp.job.qual="/"; temp.job.skill="/";

            }
            else if (temp==head){
                head=head.next;
                head.prev=null;
            }
            else if (temp==null){
//                System.out.println("Error: no such job exists.");
            }
            else if (temp.next==null){
                temp.prev.next=null;
            }

            else{
                temp.prev.next=temp.next;
                temp.next.prev=temp.prev;   
            }

        }
                
    }
    
    //O(n)
    public void printAlt(){ // print the content of all nodes separated by a new line
        for (Node i=head; i!=null; i=i.next){
            if (i.job.Date.compareTo("/")!=0){
                System.out.println("CompanyName: "+i.job.companyName+", City: "+i.job.city+", Skill: "+i.job.skill+", JobTitle: "+i.job.jobTitle);
            }
        }
    }
    
}
    