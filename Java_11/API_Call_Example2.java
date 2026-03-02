import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class APICall {

    public static void main(String[] args) throws Exception {

        String username = "user";
        String password = "pass";

        // Create Basic Auth header manually
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder()
                                   .encodeToString(auth.getBytes());

        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Build request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/basic-auth/user/pass"))
                .header("Authorization", "Basic " + encodedAuth)
                .GET()
                .build();

        // Send request
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print response
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}
/*
Status Code: 200
Response Body: {
  "authenticated": true, 
  "user": "user"
}
*/
