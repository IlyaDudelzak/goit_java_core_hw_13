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
    public static List<User> getAllUsers() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Util.sendGET(Util.testUrl + "/users");
        Type userListType = new TypeToken<List<User>>(){}.getType();
        return gson.fromJson(response.body(), userListType);
    }
    public static User getUser(int id) throws IOException, URISyntaxException, InterruptedException {
        return getAllUsers().get(id);
    }
    public static User getUser(String username) throws IOException, URISyntaxException, InterruptedException {
        List<User> users = getAllUsers();
        for(User user: users){
            if(user.getName().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }
    public static int maxUserId() throws IOException, URISyntaxException, InterruptedException {
        List<User> users = getAllUsers();
        if (users.isEmpty()) return 0;
        return users.getLast().getId();
    }
    public static HttpResponse<String> deleteUser(int id) throws URISyntaxException, IOException, InterruptedException {
        return Util.sendDELETE(Util.testUrl + "/users/" + id);
    }
    public HttpResponse<String> createUser() throws IOException, URISyntaxException, InterruptedException {
        String jsonBody = Util.gson.toJson(this);
        return Util.sendPOST(Util.testUrl + "/users", jsonBody);
    }
    public HttpResponse<String> updateUser() throws IOException, URISyntaxException, InterruptedException {
        String jsonBody = gson.toJson(this);
        return Util.sendPUT(Util.testUrl + "/users/" + this.id, jsonBody);
    }
    public static List<Post> getAllPosts(int id) throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Util.sendGET(Util.testUrl + "/users/" + id + "/posts");
        Type postListType = new TypeToken<List<Post>>(){}.getType();
        return Util.gson.fromJson(response.body(), postListType);
    }
    public static Post getLastPost(int id) throws IOException, URISyntaxException, InterruptedException {
        List<Post> posts = getAllPosts(id);
        return posts.getLast();
    }
    public List<Post> getAllPosts() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Util.sendGET(Util.testUrl + "/users/" + id + "/posts");
        Type postListType = new TypeToken<List<Post>>(){}.getType();
        return Util.gson.fromJson(response.body(), postListType);
    }
    public Post getLastPost() throws IOException, URISyntaxException, InterruptedException {
        List<Post> posts = getAllPosts();
        return posts.getLast();
    }
    public static List<Todo> getAllTasks(int id) throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Util.sendGET(Util.testUrl + "/users/" + id + "/todos");
        Type todoListType = new TypeToken<List<Todo>>(){}.getType();
        return Util.gson.fromJson(response.body(), todoListType);
    }
    public static List<Todo> getAllOpenTasks(int id) throws IOException, URISyntaxException, InterruptedException {
        return getAllTasks(id).stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }
    public List<Todo> getAllTasks() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Util.sendGET(Util.testUrl + "/users/" + id + "/todos");
        Type todoListType = new TypeToken<List<Todo>>(){}.getType();
        return Util.gson.fromJson(response.body(), todoListType);
    }
    public List<Todo> getAllOpenTasks() throws IOException, URISyntaxException, InterruptedException {
        return getAllTasks().stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }
}