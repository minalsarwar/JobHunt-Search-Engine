//"Job Hunt"
//Job Portal [search engine for jobs] DS Project, Dr. Quratulain Rajput
//Maryam Tauqeer 22862, Minal Sarwar 22756, Ramsha Munawar 22738
//This is the class that contains the Hash Tables


public class Hashing {
    
    //6 Hash Tables
    JobDoubly [] Job_a; //single jobtitle ht
    JobDoubly [] City_n; //C>S>J
    JobDoubly [] Skill_n; //C>S>J
    JobDoubly [] Job_n; //C>S>J
    JobDoubly [] Skill_Job; //S>J
    JobDoubly [] Job_null; //S>J
    
    
    public static int collisionsJob_a = 0;
    public static int occupiedCellsJob_a = 0;
    
    public static int collisionsSkill_n = 0;
    public static int occupiedCellsSkill_n = 0;
    
    public static int collisionsCity_n = 0;
    public static int occupiedCellsCity_n = 0;
    
    public static int collisionsJob_n = 0;
    public static int occupiedCellsJob_n = 0;
    
    public static int collisionsSkill_Job = 0;
    public static int occupiedCellsSkill_Job = 0;
    
    public static int collisionsJob_null = 0;
    public static int occupiedCellsJob_null = 0;
    
    Hashing(int s1, int s2, int s3, int s4, int s5, int s6){
        // table size should be a prime number and 1/3 extra.
        
        int size=s1+(s1/3);
        int newSize = getPrime(size);
        Job_a=new JobDoubly[newSize];
      
        size=s2+(s2/3);
        newSize = getPrime(size);
        City_n=new JobDoubly [newSize];
     
        size=s3+(s3/3);
        newSize = getPrime(size);
        Skill_n=new JobDoubly [newSize];
      
        size=s4+(s4/3);
        newSize = getPrime(size);
        Job_n=new JobDoubly [newSize];
        
        size=s5+(s5/3);
        newSize = getPrime(size);
        Skill_Job=new JobDoubly [newSize];
              
        size=s6+(s6/3);
        newSize = getPrime(size);
        Job_null=new JobDoubly [newSize];
        
    }
    
    //linear
    private int getPrime(int n) {
        while(true) {
            if (isPrime(n)) return n;
            n++;
        }
    }
    
    //linear
    private boolean isPrime(int n) {
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    //O(1)
    private boolean isClustered(int occupiedCells, JobDoubly table[]){
        double loadFactor = ((double) occupiedCells) / ((double) table.length);
        return loadFactor > 2.0/3.0;
    }
    
    //O(n),n=length of the string
    public int Hash(String key, JobDoubly table[]){
        //compute hash value by taking mod on key value and return remainder
        
        key=key.toUpperCase();
        int sum=0;
        int p=31;
        int pow=0;
        for (int i=0; i<key.length(); i++){
            int ascii=(int)key.charAt(i);
            pow=(int)Math.pow(p,i);
            sum+=(ascii*pow);
        }
        sum=Math.absExact(sum);
        int hash= sum%table.length;
        return hash;
    }
    
    //O(n),n=length of the string
    public int Rehash(String key, int i, JobDoubly table[]){
        // quadratic probing
        int rehash= Hash((key + i*i), table)%table.length;
        return rehash;
    }
    
    //O(n)
    public void Insert(Node j){
        this.insertJobAlone(j);
        this.insertSkill_Job(j);
        this.insertCity_Skill_Job(j);
    }
    
    public void insertJobAlone(Node j){
        //j node thats passing from the  job doubly ll
        
        //FOR Job_a hash table
        
        if (isClustered(occupiedCellsJob_a,Job_a)){
            System.out.println("Could not insert '" + j.job.jobTitle + "'.  Clustering limit reached.");
            return;
        }
        
        int i = 1;
        //hash based on job title
        int hash=this.Hash(j.job.jobTitle, Job_a);
        
        //storing doubly for job title
        
        if (Job_a[hash]==null){
            Job_a[hash]=new JobDoubly();
            occupiedCellsJob_a++;
        }
        //In case of collisions
        //we are checking collisions by checking if the incoming job title matches the one
        //that is already there
        else if (Job_a[hash].head.job.jobTitle.compareTo(j.job.jobTitle)!=0){
            while (true){
                collisionsJob_a++;
                hash = Rehash(j.job.jobTitle, i, Job_a);
                i++;
                if (i >= Job_a.length){
                  System.out.println("Could not insert '" + j.job.jobTitle + "'.  Rehashing limit reached.");
                  return;
                }
                if (hash >= Job_a.length){
                  System.out.println("Could not insert '" +j.job.jobTitle + "'.  No empty cells found.");
                  return;
                }
                if (Job_a[hash]==null){
                    Job_a[hash]= new JobDoubly();
                    occupiedCellsJob_a++;
                    break;
                }
                else if (Job_a[hash].head.job.jobTitle.compareTo(j.job.jobTitle)==0){
                    break;
                }
            }
        }
        Job_a[hash].insert(j.job);
        
    }
    
    //O(n)
    public void insertSkill_Job(Node j){
        //j node thats passing from the  job doubly ll
        
        //FOR Skill_Job hash table
        
        if (isClustered(occupiedCellsSkill_Job, Skill_Job)){
            System.out.println("Could not insert '" + j.job.skill + "'.  Clustering limit reached.");
            return;
        }
        
        int i = 1;
        //hash based on skill
        int hashSkill=this.Hash(j.job.skill, Skill_Job);
        
        //storing doubly for skills
        
        if (Skill_Job[hashSkill]==null){
            Skill_Job[hashSkill]=new JobDoubly();
            occupiedCellsSkill_Job++;
        }
        //In case of collisions
        //we are checking collisions by checking if the incoming job title matches the one
        //that is already there
        else if (Skill_Job[hashSkill].head.job.skill.compareTo(j.job.skill)!=0){
            while (true){
                collisionsSkill_Job++;
                hashSkill = Rehash(j.job.skill, i, Skill_Job);
                i++;
                if (i >= Skill_Job.length){
                  System.out.println("Could not insert '" + j.job.skill + "'.  Rehashing limit reached.");
                  return;
                }
                if (hashSkill >= Skill_Job.length){
                  System.out.println("Could not insert '" +j.job.skill + "'.  No empty cells found.");
                  return;
                }
                
                if (Skill_Job[hashSkill]==null){
                    Skill_Job[hashSkill]=new JobDoubly();
                    occupiedCellsSkill_Job++;
                    break;
                }
                else if (Skill_Job[hashSkill].head.job.skill.compareTo(j.job.skill)==0){
                    break;
                }
            }
        }
        Skill_Job[hashSkill].insert(j.job);
       
        
        //***********************************************************************************************************
        //FOR Job_null hash table
        
        if (isClustered(occupiedCellsJob_null, Job_null)){
            System.out.println("Could not insert '" + j.job.skill + " and "+ j.job.jobTitle+ "'.  Clustering limit reached.");
            return;
        }
        
        int i2 = 1;
        //hash based on skill+job title strings
        int hashJob=this.Hash((j.job.skill+j.job.jobTitle), Job_null);
        
        //storing doubly for skill+job title
        
        if (Job_null[hashJob]==null){
            Job_null[hashJob]=new JobDoubly();
            occupiedCellsJob_null++;
        }
        //In case of collisions
        //we are checking collisions by checking if the incoming job title matches the one
        //that is already there
        else if (Job_null[hashJob].head.job.skill.compareTo(j.job.skill)!=0 || Job_null[hashJob].head.job.jobTitle.compareTo(j.job.jobTitle)!=0 ){
            while (true){
                collisionsJob_null++;
                hashJob = Rehash((j.job.skill+j.job.jobTitle), i2, Job_null);
                i2++;
                if (i >= Job_null.length){
                  System.out.println("Could not insert '" +j.job.skill + " and "+ j.job.jobTitle+ "'.  Rehashing limit reached.");
                  return;
                }
                if (hashJob >= Job_null.length){
                  System.out.println("Could not insert '" +j.job.skill + " and "+ j.job.jobTitle+ "'.  No empty cells found.");
                  return;
                }
                
                if (Job_null[hashJob]==null){
                    Job_null[hashJob]=new JobDoubly();
                    occupiedCellsJob_null++;
                    break;
                }
                
                else if (Job_null[hashJob].head.job.skill.compareTo(j.job.skill)==0 && Job_null[hashJob].head.job.jobTitle.compareTo(j.job.jobTitle)==0 ){
                    break;
                }
            }
        }
        Job_null[hashJob].insert(j.job);
        
        
    }
    
    //O(n)
    public void insertCity_Skill_Job(Node j){
        //j node thats passing from the  job doubly ll
        
        //FOR City_n hash table
        if (isClustered(occupiedCellsCity_n, City_n)){
            System.out.println("Could not insert '" + j.job.city + "'.  Clustering limit reached.");
            return;
        }
        
        int i1 = 1;
        //hash based on city
        int hashCity=this.Hash(j.job.city, City_n);
        
        //storing doubly for city
        
        if (City_n[hashCity]==null){
            City_n[hashCity]=new JobDoubly();
            occupiedCellsCity_n++;
        }
        //In case of collisions
        //we are checking collisions by checking if the incoming job title matches the one
        //that is already there
        else if (City_n[hashCity].head.job.city.compareTo(j.job.city)!=0){
            while (true){
                collisionsCity_n++;
                hashCity = Rehash(j.job.city, i1, City_n);
                i1++;
                if (i1 >= City_n.length){
                  System.out.println("Could not insert '" + j.job.city + "'.  Rehashing limit reached.");
                  return;
                }
                if (hashCity >= City_n.length){
                  System.out.println("Could not insert '" +j.job.city + "'.  No empty cells found.");
                  return;
                }
                if (City_n[hashCity]==null){
                    City_n[hashCity]=new JobDoubly();
                    occupiedCellsCity_n++;
                    break;
                }
                else if (City_n[hashCity].head.job.city.compareTo(j.job.city)==0){
                    break;
                }
            }
        }
        City_n[hashCity].insert(j.job);
        
        
        //***********************************************************************************************************
        //FOR Skill_n hash table
        
        if (isClustered(occupiedCellsSkill_n, Skill_n)){
            System.out.println("Could not insert '" + j.job.city +" and "+j.job.skill+ "'.  Clustering limit reached.");
            return;
        }
        
        int i2 = 1;
        //hash based on city+skill strings
        int hashSkill=this.Hash((j.job.city+j.job.skill), Skill_n); //hash for HT skill
        
        //storing doubly for city+skills
        if (Skill_n[hashSkill]==null){
            Skill_n[hashSkill]=new JobDoubly();
            occupiedCellsSkill_n++;
        }
        //In case of collisions
        //we are checking collisions by checking if the incoming job title matches the one
        //that is already there
        else if (Skill_n[hashSkill].head.job.city.compareTo(j.job.city)!=0 || Skill_n[hashSkill].head.job.skill.compareTo(j.job.skill)!=0 ){
            while (true){
                collisionsSkill_n++;
                hashSkill = Rehash((j.job.city+j.job.skill), i2, Skill_n);
                i2++;
                if (i2 >= Skill_n.length){
                  System.out.println("Could not insert '" + j.job.city +" and "+j.job.skill + "'.  Rehashing limit reached.");
                  return;
                }
                if (hashSkill >= Skill_n.length){
                  System.out.println("Could not insert '" +j.job.city +" and "+j.job.skill + "'.  No empty cells found.");
                  return;
                }
                
                if (Skill_n[hashSkill]==null){
                    Skill_n[hashSkill]=new JobDoubly();
                    occupiedCellsSkill_n++;
                    break;
                }
                else if (Skill_n[hashSkill].head.job.city.compareTo(j.job.city)==0 && Skill_n[hashSkill].head.job.skill.compareTo(j.job.skill)==0 ){
                    break;
                }
            }
        }
        Skill_n[hashSkill].insert(j.job);
        
        
        //***********************************************************************************************************
        //FOR Job_n hash table
        if (isClustered(occupiedCellsJob_n, Job_n)){
            System.out.println("Could not insert '" + j.job.city +", "+j.job.skill+" and "+j.job.jobTitle+ "'.  Clustering limit reached.");
            return;
        }
        int i3 = 1;
        //hash based on city+skill+job title strings
        int hashJob=this.Hash((j.job.city+j.job.skill+j.job.jobTitle), Job_n); //hash for HT Job title
        
        //storing doubly for city+skills+job title title
        if (Job_n[hashJob]==null){
            Job_n[hashJob]=new JobDoubly();
            occupiedCellsJob_n++;
        }
        //In case of collisions
        //we are checking collisions by checking if the incoming job title matches the one
        //that is already there
        else if (Job_n[hashJob].head.job.city.compareTo(j.job.city)!=0 || Job_n[hashJob].head.job.skill.compareTo(j.job.skill)!=0 || Job_n[hashJob].head.job.jobTitle.compareTo(j.job.jobTitle)!=0 ){
            while (true){
                collisionsJob_n++;
                hashJob = Rehash((j.job.city+j.job.skill+j.job.jobTitle), i2, Job_n);
                i3++;
                if (i3 >= Job_n.length){
                  System.out.println("Could not insert '" + j.job.city +", "+j.job.skill+" and "+j.job.jobTitle + "'.  Rehashing limit reached.");
                  return;
                }
                if (hashJob >= Job_n.length){
                  System.out.println("Could not insert '" +j.job.city +", "+j.job.skill+" and "+j.job.jobTitle+ "'.  No empty cells found.");
                  return;
                }
                
                if (Job_n[hashJob]==null){
                    Job_n[hashJob]=new JobDoubly();
                    occupiedCellsJob_n++;
                    break;
                }
                else if (Job_n[hashJob].head.job.city.compareTo(j.job.city)==0 && Job_n[hashJob].head.job.skill.compareTo(j.job.skill)==0 && Job_n[hashJob].head.job.jobTitle.compareTo(j.job.jobTitle)==0 ){
                 break;   
                }
            }
        }
        Job_n[hashJob].insert(j.job);
        
        
    }
    
    //O(l)
    public JobDoubly searchJobAlone (String key){
            
        int i= 1;
        int hash=Hash(key, Job_a);
        
        //RETURNING NULL IF KEY NOT FOUND
        if (Job_a[hash]==null){
            return null;
        }
        //In case of collisions
        //we are checking collisions by checking if the incoming job title matches the one
        //that is already there
        else if (Job_a[hash].head.job.jobTitle.compareTo(key)!=0){
            while (true){
                hash = Rehash(key, i, Job_a);
                i++;
                if (i >= Job_a.length || hash >= Job_a.length || Job_a[hash]==null){
                  return null; //not found
                }
                if (Job_a[hash].head.job.jobTitle.compareTo(key)==0){
                    return Job_a[hash];
                }
            }
        }
        else{ //if Job_a[hash].head.job.jobTitle.compareTo(key)==0
            return Job_a[hash];
        }
         
    }
    
    //O(l)
    public JobDoubly searchSkill_Job (String keySkill, String keyJobTitle) {
        int hash;
        int i;
        
        //For only skill
        if (keyJobTitle.compareTo("")==0){
            i=1;
            
            //hash based on skill
            hash=Hash(keySkill, Skill_Job);
            
            //RETURNING NULL IF KEY NOT FOUND
            if (Skill_Job[hash]==null){
                return null;
            }
            
            //In case of collisions
            //we are checking collisions by checking if the incoming job title matches the one
            //that is already there
            else if (Skill_Job[hash].head.job.skill.compareTo(keySkill)!=0){
                while (true){
                    hash = Rehash(keySkill, i, Skill_Job);
                    i++;
                    if (i >= Skill_Job.length || hash >= Skill_Job.length || Skill_Job[hash]==null){
                      return null; //not found
                    }
                    if (Skill_Job[hash].head.job.skill.compareTo(keySkill)==0){
                        return Skill_Job[hash];
                    }
                }
            }
            else{ //Skill_Job[hashSkill].head.job.skill.compareTo(keySkill)==0
                return Skill_Job[hash];
            }
        }   
        
        //***********************************************************************************************************
        //For both skill+job title
        else{
            i = 1;
            //hash based on skill+job title strings
            hash=Hash((keySkill+keyJobTitle), Job_null);
            
            //RETURNING NULL IF KEY NOT FOUND
            if (Job_null[hash]==null){
                return null;
            }
            
            //In case of collisions
            //we are checking collisions by checking if the incoming job title matches the one
            //that is already there
            else if (Job_null[hash].head.job.skill.compareTo(keySkill)!=0 || Job_null[hash].head.job.jobTitle.compareTo(keyJobTitle)!=0 ){
                while (true){
                    hash= Rehash((keySkill+keyJobTitle), i, Job_null);
                    i++;
                    if (i >= Job_null.length || hash >= Job_null.length|| Job_null[hash]==null){
                        return null; //not found
                    }
                    if (Job_null[hash].head.job.skill.compareTo(keySkill)==0 && Job_null[hash].head.job.jobTitle.compareTo(keyJobTitle)==0 ){
                        return Job_null[hash];
                    }
                }
            }
            
            else{ //Job_null[hash].head.job.skill.compareTo(keySkill)==0 && Job_null[hash].head.job.jobTitle.compareTo(keyJobTitle)==0 
                return Job_null[hash];
            }
        }
    }
    
    //O(l)
    public JobDoubly searchCity_Skill_Job (String keyCity, String keySkill, String keyJobTitle) {
        int hash;
        int i;
        
        //For only city
        if (keySkill.compareTo("")==0 && keyJobTitle.compareTo("")==0){
            i = 1;
            
            //hash based on city
            hash=Hash(keyCity, City_n);

            //RETURNING NULL IF KEY NOT FOUND
            if (City_n[hash]==null){
                return null;
            }
            //In case of collisions
            //we are checking collisions by checking if the incoming job title matches the one
            //that is already there
            else if (City_n[hash].head.job.city.compareTo(keyCity)!=0){
                while (true){
                    hash = Rehash(keyCity, i, City_n);
                    i++;
                    if (i >= City_n.length|| hash >= City_n.length|| City_n[hash]==null){
                      return null; //not found
                    }
                    if (City_n[hash].head.job.city.compareTo(keyCity)==0){
                        return City_n[hash];
                    }
                }
            }
            else{ //if (City_n[hash].head.job.city.compareTo(keyCity)==0)
                return City_n[hash];
            }
        }
        //***********************************************************************************************************
        
        //For both city+skill, NOT job title
        else if (keyCity.compareTo("")!=0 && keySkill.compareTo("")!=0 && keyJobTitle.compareTo("")==0){
            i= 1;
            //hash based on city+skill strings
            hash=Hash((keyCity+keySkill), Skill_n);

            //RETURNING NULL IF KEY NOT FOUND
            if (Skill_n[hash]==null){
                return null;
            }
            //In case of collisions
            //we are checking collisions by checking if the incoming job title matches the one
            //that is already there
            else if (Skill_n[hash].head.job.city.compareTo(keyCity)!=0 || Skill_n[hash].head.job.skill.compareTo(keySkill)!=0 ){
                while (true){
                    hash = Rehash((keyCity+keySkill), i, Skill_n);
                    i++;
                    if (i >= Skill_n.length || hash >= Skill_n.length || Skill_n[hash]==null){
                      return null;
                    }
                    if (Skill_n[hash].head.job.city.compareTo(keyCity)==0 && Skill_n[hash].head.job.skill.compareTo(keySkill)==0 ){
                        return Skill_n[hash];
                    }
                }
            }
            else{ //if (Skill_n[hash].head.job.city.compareTo(keyCity)==0 && Skill_n[hash].head.job.skill.compareTo(keySkill)==0 )
                return Skill_n[hash];
            }
        }
        //***********************************************************************************************************
        
        //For city+skill+Job title
        else{
            i = 1;
            //hash based on city+skill+job title strings
            hash=this.Hash((keyCity+keySkill+keyJobTitle), Job_n); 

            //RETURNING NULL IF KEY NOT FOUND
            if (Job_n[hash]==null){
                return null;
            }
            //In case of collisions
            //we are checking collisions by checking if the incoming job title matches the one
            //that is already there
            else if (Job_n[hash].head.job.city.compareTo(keyCity)!=0 || Job_n[hash].head.job.skill.compareTo(keySkill)!=0 || Job_n[hash].head.job.jobTitle.compareTo(keyJobTitle)!=0 ){
                while (true){
                    hash= Rehash((keyCity+keySkill+keyJobTitle), i, Job_n);
                    i++;
                    if (i >= Job_n.length|| hash >= Job_n.length || Job_n[hash]==null){
                        return null; //not found
                    }
                    if (Job_n[hash].head.job.city.compareTo(keyCity)==0 && Job_n[hash].head.job.skill.compareTo(keySkill)==0 && Job_n[hash].head.job.jobTitle.compareTo(keyJobTitle)==0 ){
                        return Job_n[hash];
                    }
                }
            }
            else{ //if (Job_n[hash].head.job.city.compareTo(keyCity)==0 && Job_n[hash].head.job.skill.compareTo(keySkill)==0 && Job_n[hash].head.job.jobTitle.compareTo(keyJobTitle)==0 )
                return Job_n[hash];
            }
        }
    }
    
    
    public void Delete(String city, String comp, String skill, String jobTitle){
        this.deletionInJob_a(city, comp, skill, jobTitle);
        this.deletionInSkill_Job(city, comp, skill, jobTitle);
        this.deletionInCity_Skill_Job(city, comp, skill, jobTitle);
    }
    
    //O(n)
    public void print(JobDoubly Table[]){
        for (int i = 0; i < Table.length; i++) {
            if (Table[i]!=null && Table[i].head.job.Date.compareTo("/")!=0){
                System.out.print("["+i+"] ");
                Table[i].printAlt();
                System.out.println("");  
            }
            
        }
    }
    
    public void deletionInJob_a(String city, String comp, String skill, String jobTitle){
        
        if (searchJobAlone(jobTitle)!=null){ //job found and deleted its node from that doubly
            searchJobAlone(jobTitle).Delete(city, comp, skill, jobTitle);
        }
        
    }    
    
    public void deletionInSkill_Job(String city, String comp, String skill, String jobTitle){
        if (this.searchSkill_Job(skill, "")!=null){//job found and deleted its node from that doubly
           this.searchSkill_Job(skill, "").Delete(city, comp, skill, jobTitle);
       }
        
       if (this.searchSkill_Job(skill, jobTitle)!=null){//job found and deleted its node from that doubly
           this.searchSkill_Job(skill, jobTitle).Delete(city, comp, skill, jobTitle);
       }
    }
   
    public void deletionInCity_Skill_Job(String city, String comp, String skill, String jobTitle){
        if (this.searchCity_Skill_Job(city, "", "")!=null){//job found and deleted its node from that doubly
           this.searchCity_Skill_Job(city, "", "").Delete(city, comp, skill, jobTitle);
       }
        
       if (this.searchCity_Skill_Job(city, skill, "")!=null){//job found and deleted its node from that doubly
           this.searchCity_Skill_Job(city, skill, "").Delete(city, comp, skill, jobTitle);
       }
        
       if (this.searchCity_Skill_Job(city, skill, jobTitle)!=null){//job found and deleted its node from that doubly
           this.searchCity_Skill_Job(city, skill, jobTitle).Delete(city, comp, skill, jobTitle);
       }
       
    }
 
}
