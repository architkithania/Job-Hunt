package EmployerSide;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortedCandidates {

    private class JobListingFileJSON {
        public String name;
        public Applicant [] applicants;

        JobListingFileJSON(String name, Applicant [] applicants) {
            this.name = name;
            this.applicants = applicants;
        }
    }

    private class Applicant {
        private int id;
        private String education;
        private String experience;
        private String [] skills;

        public Applicant(int id, String education, String experience, String[] skills) {
            this.id = id;
            this.education = education;
            this.experience = experience;
            this.skills = skills;
        }
    }

     class IntOnlyApplicants {
        int id;
        int education;
        int experience;
        int skills;

        int getPriority(String s) {
            if (s.equals("education"))
                return education;
            else if (s.equals("experience"))
                return experience;
            else {
                return skills;
            }
        }
    }


    public boolean isEmpty;
    public int [] ids;

    SortedCandidates(int interviewSlots, String [] priorityList, String jobTitle) {
        try {
            ParseAPI api = new ParseAPI();
            Records record = new Records();
            for (int i = 0; i < api.records.length; i++) {
                if (api.records[i].jobtitle.equals(jobTitle)) {
                    record.jobtitle = api.records[i].jobtitle;
                    record.skills = api.records[i].skills;
                    record.mediansalary = api.records[i].mediansalary;
                    record.experience = api.records[i].experience;
                    record.education = api.records[i].education;
                    record._id = api.records[i]._id;
                    break;
                }
            }
            Gson gson = new Gson();
            Scanner jobListing = new Scanner(new File("job_listings/" + jobTitle + ".json"));
            JobListingFileJSON listingJSON = gson.fromJson(jobListing.nextLine(), JobListingFileJSON.class);
            IntOnlyApplicants [] intOnlyApplicants = new IntOnlyApplicants[listingJSON.applicants.length];
            for (int i = 0; i < intOnlyApplicants.length; i++) {
                intOnlyApplicants[i] = new IntOnlyApplicants();

                // Id
                intOnlyApplicants[i].id = listingJSON.applicants[i].id;
                // Education
                if (listingJSON.applicants[i].education.equals("Master Degree")) {
                    intOnlyApplicants[i].education = 3;
                }
                else if (listingJSON.applicants[i].education.equals("Bachelor Degree")) {
                    intOnlyApplicants[i].education = 2;
                }
                else {
                    intOnlyApplicants[i].education = 1;
                }

                // Experience;
                String [] exps = listingJSON.applicants[i].experience.split(" ");
                intOnlyApplicants[i].experience = Integer.parseInt(exps[1]);

                // skills
                intOnlyApplicants[i].skills = 0;
                String [] skillsArr = record.skills.split(",");
                for (int j = 0; j < skillsArr.length; j++) {
                    for (int k = 0; k < listingJSON.applicants[i].skills.length; k++) {
                        if (skillsArr[j].equals(listingJSON.applicants[i].skills[k]))
                            intOnlyApplicants[i].skills++;
                    }
                }
            }
            if (listingJSON.applicants.length == 0) {
                isEmpty = true;
            }
            else {
                for (int i = 0; i < intOnlyApplicants.length; i++) {
                    IntOnlyApplicants max = intOnlyApplicants[i];
                    for (int j = i; j < intOnlyApplicants.length; j++) {
                        // Checking P0
                        if (intOnlyApplicants[j].getPriority(priorityList[0]) > max.getPriority(priorityList[0])) {
                            swapper(max, intOnlyApplicants[j]);
                        }
                        else if (intOnlyApplicants[j].getPriority(priorityList[0]) == max.getPriority(priorityList[0])) {
                            // Checking P1
                            if (intOnlyApplicants[j].getPriority(priorityList[1]) > max.getPriority(priorityList[1])) {
                                swapper (max, intOnlyApplicants[j]);
                            }
                            else if (intOnlyApplicants[j].getPriority(priorityList[1]) == max.getPriority(priorityList[1])) {
                                // Checking P2
                                if (intOnlyApplicants[j].getPriority(priorityList[2]) > max.getPriority(priorityList[2])) {
                                    swapper(max, intOnlyApplicants[j]);
                                }
                            }
                        }
                    }
                }
            }

            ids = new int [intOnlyApplicants.length];
            for (int i = 0; i < intOnlyApplicants.length; i++) {
                ids[i] = intOnlyApplicants[i].id;
            }

            for (int i : ids) {
                System.out.println(i);
            }
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }
    }

    private void swapper(IntOnlyApplicants a, IntOnlyApplicants b) {
        // id
        int tempId = a.id;
        a.id = b.id;
        b.id = tempId;

        // education
        int tempEd = a.education;
        a.education = b.education;
        b.education = tempEd;

        // Experience
        int tempExp = a.experience;
        a.experience = b.experience;
        b.experience = tempExp;

        // Skills
        int tempSk = a.skills;
        a.skills = b.skills;
        b.skills = tempSk;

    }
}