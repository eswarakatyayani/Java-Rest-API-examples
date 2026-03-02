import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APICall {

    public static void main(String[] args) throws Exception {

        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();
        
        //url
        String url = "https://jsonplaceholder.typicode.com/posts";

        // JSON body
        String requestBody = "{ \"title\": \"Test\", \"body\": \"Hello\", \"userId\": 6 }";

        // Build request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send request
        HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print response
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}

/*
Status Code: 201
Response Body: {
  "title": "Test",
  "body": "Hello",
  "userId": 6,
  "id": 101
}
*/
