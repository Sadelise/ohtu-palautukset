package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "";//011120775
        if (args.length > 0) {
            studentNr = args[0];
        }

        String subUrl = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String statsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";

        String subsBodyText = Request.Get(subUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();
        String statsResponse = Request.Get(statsUrl).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(subsBodyText);
        System.out.println("json-muotoinen kurssidata:");
        System.out.println(courseBodyText);
        System.out.println("json-muotoinen statistiikka:");
        System.out.println(statsResponse);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(subsBodyText, Submission[].class);
        System.out.println();
        Course course = mapper.fromJson(courseBodyText, Course.class);
        int[] exercises = course.getExercises();

        JsonParser parser = new JsonParser();
        JsonObject parsedData = parser.parse(statsResponse).getAsJsonObject();
        List<JsonObject> stats = new ArrayList<>();
        stats.add(parsedData.get("1").getAsJsonObject());
        stats.add(parsedData.get("2").getAsJsonObject());
        stats.add(parsedData.get("3").getAsJsonObject());
        int students = 0;
        int totalExcercises = 0;
        for (JsonObject stat : stats) {
            students += stat.get("students").getAsInt();
            totalExcercises += stat.get("exercise_total").getAsInt();
        }
        printSubmissions(subs, course, studentNr, exercises, students, totalExcercises);

    }

    private static void printSubmissions(Submission[] subs, Course course, String studentNr, int[] maxExercises, int students, int allStudentsExercises) {
        System.out.println();
        System.out.println("Kurssi: " + course.getName() + ", " + course.getTerm());
        System.out.println();
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println();
        int totalHours = 0;
        int totalExercises = 0;
        int i = 0;
        for (Submission submission : subs) {
            System.out.println("Viikko " + submission.getWeek() + ": ");
            System.out.print("\ttehtyjä tehtäviä yhteensä: " + submission.getExercises().length);
            System.out.print(" (maksimi " + maxExercises[i] + "), ");
            System.out.print("aikaa kului " + submission.getHours() + " tuntia, ");
            String tehtavat = Arrays.toString(submission.getExercises()).replace("]", "").replace("[", "");
            System.out.print("tehdyt tehtävät: " + tehtavat);
            System.out.println();
            totalHours += submission.getHours();
            totalExercises += submission.getExercises().length;
            i++;
        }
        System.out.println();
        System.out.println("yhteensä: " + totalExercises + " tehtävää " + totalHours + " tuntia");
        System.out.println();
        System.out.println("kurssilla yhteensä " + students + " palautusta, palautettuja tehtäviä " + allStudentsExercises + " kpl");
    }
}
