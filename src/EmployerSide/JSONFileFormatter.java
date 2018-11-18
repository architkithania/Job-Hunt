package EmployerSide;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class JSONFileFormatter {
    JSONFileFormatter() {
        try {
            ParseAPI api = new ParseAPI();
            Gson fileGson = new Gson();
            JobListingFileJSON jobListingFileJSON;
            for (int i = 0; i < api.records.length; i++) {
                Scanner jobListFile = new Scanner(new File("job_listings/" + api.records[i].jobtitle + ".json"));
                Applicant [] applicants = new Applicant[0];
                jobListingFileJSON = new JobListingFileJSON(api.records[i].jobtitle, applicants);
                PrintStream stream = new PrintStream("job_listings/" + api.records[i].jobtitle + ".json");
                stream.println(fileGson.toJson(jobListingFileJSON));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

class JobListingFileJSON {
    public String name;
    public Applicant [] applicants;

    JobListingFileJSON(String name, Applicant [] applicants) {
        this.name = name;
        this.applicants = applicants;
    }
}

class Applicant {
    private int id;
    private String education;
    private String experience;
    private String [] skills;
}
