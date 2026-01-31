import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.List;

public class Task2 extends Task1{
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeAllCommentsToLastPostToFile(User user, String path) throws TaskError, IOException, URISyntaxException, InterruptedException {
        writeAllCommentsToLastPostToFile(user.getId(), path);
    }

    public static void writeAllCommentsToLastPostToFile(int id, String path) throws IOException, URISyntaxException, InterruptedException, TaskError {
        Post lastPost = User.getLastPost(id);
        if(lastPost == null) throw new TaskError();
        List<Comment> comments = lastPost.getComments();
        if(comments.isEmpty()) throw new TaskError();
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(comments, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
