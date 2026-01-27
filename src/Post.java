import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private int userId;
    private final int id;
    private String title;
    private String body;
}