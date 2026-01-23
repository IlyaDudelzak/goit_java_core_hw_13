import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

abstract public class Util {

    private static HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    private static Gson gson = new Gson();
    public static String testUrl = "http://jsonplaceholder.typicode.com";
    public static JsonArray getUsers() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = sendGET(testUrl + "/users");
        return gson.fromJson(response.body(), JsonArray.class);
    }
    public static int maxUserId() throws IOException, URISyntaxException, InterruptedException {
        return getUsers().asList().getLast().getAsJsonObject().get("id").getAsInt();
    }
    public static HttpResponse<String> sendGET(String Url) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Url))
                .GET()
                .headers("Accept", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> sendPOST(String Url, String jsonPayload) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .headers("Accept", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> sendPUT(String Url, String jsonPayload) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Url))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .headers("Accept", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
