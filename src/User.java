import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import com.google.gson.Gson;


@Data
@Builder
public class User {
    private static final Gson gson = new Gson();

    private final int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @Data
    @Builder
    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        @Data
        @Builder
        public static class Geo {
            private String lat;
            private String lng;
        }
    }

    @Data
    @Builder
    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;
    }
    private static class TaskError extends Exception {}
    public static List<User> getAllUsers() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Util.sendGET(Util.testUrl + "/users");
        Type userListType = new TypeToken<List<User>>(){}.getType();
        return Util.gson.fromJson(response.body(), userListType);
    }
    public static User getUser(int id) throws IOException, URISyntaxException, InterruptedException, TaskError {
        User user =  getAllUsers().get(id);
        if(user == null) throw new TaskError();
        return user;
    }
    public static User getUser(String username) throws IOException, URISyntaxException, InterruptedException, TaskError {
        User user =  getAllUsers().get();
        if(user == null) throw new TaskError();
        return user;
    }
    public static int maxUserId() throws IOException, URISyntaxException, InterruptedException {
        List<User> users = getAllUsers();
        if (users.isEmpty()) return 0;
        return users.getLast().getId();
    }
    public static HttpResponse<String> deleteUser(int id) throws URISyntaxException, IOException, InterruptedException, TaskError {
        HttpResponse<String> response = Util.sendDELETE(Util.testUrl + "/users/" + id);
        if((int)(response.statusCode() / 100) != 2) throw new TaskError();
        return response;
    }
    public HttpResponse<String> createUser() throws IOException, URISyntaxException, InterruptedException {
        String jsonBody = Util.gson.toJson(this);
        HttpResponse<String> response = Util.sendPOST(Util.testUrl + "/users", jsonBody);
    }
    public HttpResponse<String> updateUser() throws IOException, URISyntaxException, InterruptedException {
        String jsonBody = gson.toJson(this);
        HttpResponse<String> response = Util.sendPUT(Util.testUrl + "/users/" + this.id, jsonBody);
    }
}