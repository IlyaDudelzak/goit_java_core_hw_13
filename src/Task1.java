import com.google.gson.Gson;
import java.io.IOException;
import java.net.URISyntaxException;
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

    public static void deleteUser() throws IOException, URISyntaxException, InterruptedException {
        User.deleteUser(User.maxUserId() + 1);
    }
    
    public static List<User> getAllUsers() throws IOException, URISyntaxException, InterruptedException {
        return User.getAllUsers();
    }

    public static User getUser(int userId)
}