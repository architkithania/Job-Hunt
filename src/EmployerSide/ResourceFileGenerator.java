package EmployerSide;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class ResourceFileGenerator {

    private static ParseAPI api;

    public static void main(String[] args){
        generateJobsResource();
        generateSkillsResource();
    }

    public static void generateJobsResource(){
        try{
            api = new ParseAPI();
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

    public static void generateSkillsResource() {
        ArrayList<String> skillsList = new ArrayList<>();
        try {
            PrintStream skillsFile = new PrintStream("skills.txt");
            for (int i = 0; i < api.records.length; i++) {
                String [] skillArr = api.records[i].skills.split(",");
                for (int j = 0; j < skillArr.length; j++) {
                    if (!(skillsList.contains(skillArr[j])))
                    skillsList.add(skillArr[j]);
                }
            }
            for (int i = 0; i < skillsList.size(); i++) {
                skillsFile.println(skillsList.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
