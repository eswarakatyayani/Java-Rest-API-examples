
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class APICall {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://httpbin.org/basic-auth/user/pass";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("user", "pass");  // username, password

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
    }
}

/*
Status Code: 200 OK
Response Body: {
  "authenticated": true, 
  "user": "user"
}

*/
