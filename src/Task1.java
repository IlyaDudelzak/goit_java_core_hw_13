import java.io.IOException;
import java.net.URISyntaxException;

public class Task1 {
    public static void createUser() throws IOException, URISyntaxException, InterruptedException {
        Util.sendPOST(Util.testUrl + "/users", "{\n" +
                "  \"name\": \"Ivan Ivanov\",\n" +
                "  \"username\": \"ivanov_dev\",\n" +
                "  \"email\": \"ivan@test.com\",\n" +
                "  \"id\": 11\n" +
                "}");
    }
    public static void updateUser() throws IOException, URISyntaxException, InterruptedException {
        Util.sendPOST(Util.testUrl + "/users", "{\n" +
                "  \"name\": \"Ivan Ivanov\",\n" +
                "  \"username\": \"ivanov_dev\",\n" +
                "  \"email\": \"ivan@test.com\",\n" +
                "  \"id\": 11\n" +
                "}");
    }
}
