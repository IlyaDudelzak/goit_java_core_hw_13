import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;

@Data
@Builder
public class Post {
    private int userId;
    private final int id;
    private String title;
    private String body;

    public List<Comment> getComments() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = Util.sendGET(Util.testUrl + "/posts/" + id + "/comments");
        Type commentListType = new TypeToken<List<Comment>>(){}.getType();
        return Util.gson.fromJson(response.body(), commentListType);
    }
}