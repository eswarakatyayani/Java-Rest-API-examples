import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class APICall {

    public static String bestInGenre(String genre) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        int page = 1;
        int totalPages = 1;

        double maxRating = -1.0;
        String bestSeries = "";

        while (page <= totalPages) {

            String url = "https://jsonmock.hackerrank.com/api/tvseries?page=" + page;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());

            totalPages = jsonObject.getInt("total_pages");

            JSONArray data = jsonObject.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {

                JSONObject series = data.getJSONObject(i);

                String genres = series.getString("genre");

                if (genres.contains(genre)) {

                    double rating = series.getDouble("imdb_rating");
                    String name = series.getString("name");

                    if (rating > maxRating ||
                       (rating == maxRating && name.compareTo(bestSeries) < 0)) {

                        maxRating = rating;
                        bestSeries = name;
                    }
                }
            }

            page++;
        }

        return bestSeries;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Drama: "+bestInGenre("Drama"));
        System.out.println("=============================");
        System.out.println("Animation: "+bestInGenre("Animation"));
        
    }
}

/*
output:

Drama: Breaking Bad
=============================
Animation: Avatar: The Last Airbender

*/
