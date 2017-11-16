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

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);

        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("Oliot:");
        for (Submission submission : subs) {
            System.out.print("Viikko "+submission.getWeek()+": ");
            System.out.print("tehtyjä tehtäviä yhteensä: " + submission.getExercises().length);
            System.out.print(", aikaa kului " + submission.getHours() + " tuntia, ");
            String tehtavat = Arrays.toString(submission.getExercises()).replace("]", "").replace("[", "");
            System.out.print("tehdyt tehtävät: " + tehtavat);
            System.out.println();
        }

    }
}
