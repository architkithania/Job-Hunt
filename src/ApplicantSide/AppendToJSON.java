package ApplicantSide;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AppendToJSON {
    AppendToJSON(String jobTitle, String education, String experience, String [] skills) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < jobTitle.length(); i++) {
                if (jobTitle.charAt(i) == ' ')
                    sb.append('_');
                else
                    sb.append(jobTitle.charAt(i));
            }

            int id = new Scanner(new File("counter.txt")).nextInt();
            Gson gson = new Gson();
            JobListingFileJSON listing = gson.fromJson(new Scanner(new File("job_listings/" + sb.toString() + ".json")).nextLine(), JobListingFileJSON.class);
            ArrayList<Applicant> applicants = new ArrayList<>(Arrays.asList(listing.applicants));
            Applicant toBeAppended = new Applicant(id, education, experience, skills);
            applicants.add(toBeAppended);
            Applicant [] appArr = new Applicant[applicants.size()];
            for (int i = 0; i < appArr.length; i++) {
                appArr[i] = applicants.get(i);
            }
            JobListingFileJSON appended = new JobListingFileJSON(sb.toString(), appArr);
            PrintStream stream = new PrintStream("job_listings/" + sb.toString() + ".json");
            stream.println(gson.toJson(appended));
        } catch (FileNotFoundException e) {
            e.getMessage();
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
    private String education; // 3 - "Master Degree", 2 - "Bachelor Degree", 1 - "Diploma / Certificate"
    private String experience;
    private String [] skills;


    public Applicant(int id, String education, String experience, String[] skills) {
        this.id = id;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
    }
}
