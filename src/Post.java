import lombok.Data;

@Data
public class Post {
    private int userId;
    private final int id;
    private String title;
    private String body;
}