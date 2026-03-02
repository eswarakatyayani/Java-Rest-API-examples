import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class APICall {

    public static void main(String[] args) {
      
        RestTemplate restTemplate = new RestTemplate();
      
        String url = "https://jsonplaceholder.typicode.com/posts";
      
        String requestBody = "{ \"title\": \"Test\", \"body\": \"Hello World\", \"userId\": 1 }";
      
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
      
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
      
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
      
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
    }
}

/*
Status Code: 201 CREATED
Response Body: {
  "title": "Test",
  "body": "Hello World",
  "userId": 1,
  "id": 101
}
*/
