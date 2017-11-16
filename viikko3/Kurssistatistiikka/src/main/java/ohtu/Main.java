package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String subUrl = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";

        String subsBodyText = Request.Get(subUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(subsBodyText);
        System.out.println("json-muotoinen kurssidata:");
        System.out.println(courseBodyText);
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(subsBodyText, Submission[].class);
        Course course = mapper.fromJson(courseBodyText, Course.class);

        printSubmissions(subs, course, studentNr);

    }

    private static void printSubmissions(Submission[] subs, Course course, String studentNr) {
        System.out.println();
        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm());
        System.out.println();
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println();
        for (Submission submission : subs) {
            System.out.print("Viikko " + submission.getWeek() + ": ");
            System.out.print("tehtyjä tehtäviä yhteensä: " + submission.getExercises().length);
            System.out.print(", aikaa kului " + submission.getHours() + " tuntia, ");
            String tehtavat = Arrays.toString(submission.getExercises()).replace("]", "").replace("[", "");
            System.out.print("tehdyt tehtävät: " + tehtavat);
            System.out.println();
        }
    }
}
