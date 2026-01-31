import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Task3 extends Task2 {
    public static void printAllOpenTasksToUser(User user) throws IOException, URISyntaxException, InterruptedException, TaskError {
        printAllOpenTasksToUser(user.getId());
    }
    public static void printAllOpenTasksToUser(int id) throws IOException, URISyntaxException, InterruptedException, TaskError {
        List<Todo> todos = User.getAllOpenTasks(id);
        if(todos.isEmpty()) throw new TaskError();
        todos.stream().forEach((todo -> System.out.println(todo.toString())));
    }
}
