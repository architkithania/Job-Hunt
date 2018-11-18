package EmployerSide;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class ParseAPI {

    StringBuilder oneLineString = new StringBuilder();
    public Records [] records;

    public ParseAPI() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("API.json"));
        while (scanner.hasNextLine())
            makeOneLine(scanner.nextLine());
        Gson gson = new Gson();
        Jobs jobs = gson.fromJson(oneLineString.toString(), Jobs.class);
        PrintStream stream = new PrintStream(new File("test.json"));
        stream.println(gson.toJson(jobs.result.records));
        scanner = new Scanner(new File("test.json"));
        Records [] recordsArr = gson.fromJson(scanner.nextLine(), Records[].class);
        records = removeWhiteSpaceInTitle(recordsArr);
    }

    private Records [] removeWhiteSpaceInTitle(Records [] recordsArr) {
        Records [] records = recordsArr;
        System.out.println(records);
        for (int i = 0; i < recordsArr.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < recordsArr[i].jobtitle.length(); j++) {
                if (recordsArr[i].jobtitle.charAt(j) == ' ') {
                    sb.append('_');
                }
                else {
                    sb.append(recordsArr[i].jobtitle.charAt(j));
                }
            }
            records[i].jobtitle = sb.toString();
        }
        return records;
    }

    private void makeOneLine(String s) {
        oneLineString.append(s.trim());
    }
}

class Jobs
{
    private String help;
    private Boolean success;
    Results result;
}
class Results {
    private Boolean include_total;
    private String resource_id;
    Fields[] fields;
    private String records_format;
    Records[] records;
    Links _links;
    private int total;
}
class Fields
{
    private String type;
    private String id;
}
class Records
{
    public int _id;
    public String jobtitle;
    public String education;
    public String experience;
    public String mediansalary;
    public String skills;

    public String toString() {
        return ( _id + "," + jobtitle + "," + education + "," + experience + "," + mediansalary + "," + "[" + skills + "]");
    }
}
class Links
{
    private String start;
    private String next;
}