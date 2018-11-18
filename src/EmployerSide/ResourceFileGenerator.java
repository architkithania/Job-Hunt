package EmployerSide;

import java.io.PrintStream;

public class ResourceFileGenerator {

    public static void main(String[] args){
        generateJobsResource();
    }

    public static void generateJobsResource(){
        try{
            ParseAPI api = new ParseAPI();
            try{
                PrintStream ps = new PrintStream("jobs.txt");
                for(int i = 0; i < api.records.length; i++){
                    ps.println(api.records[i].jobtitle);
                }
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
