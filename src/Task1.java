import com.google.gson.Gson;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.SequencedCollection;

public class Task1 {
    private static final Gson gson = new Gson();

    public static void createUser(User user) throws IOException, URISyntaxException, InterruptedException {
        user.createUser();
    }

    public static void updateUser(User user) throws IOException, URISyntaxException, InterruptedException {
        user.updateUser();
    }

    public static void deleteUser(int id) throws IOException, URISyntaxException, InterruptedException, TaskError {
        HttpResponse<String> response = User.deleteUser(id);
        if((int)(response.statusCode() / 100) != 2) throw new TaskError();
    }
    
    public static List<User> getAllUsers() throws IOException, URISyntaxException, InterruptedException {
        return User.getAllUsers();
    }

    public static User getUser(int userId) throws TaskError, IOException, URISyntaxException, InterruptedException {
        User user = User.getUser(userId);
        if(user == null) throw new TaskError();
        return user;
    }

    public static User getUser(String username) throws TaskError, IOException, URISyntaxException, InterruptedException {
        User user = User.getUser(username);
        if(user == null) throw new TaskError();
        return user;
    }
}