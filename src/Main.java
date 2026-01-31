// Написать это нейросетью можно?
void main() {
    try {
        System.out.println("--- 1. Создание пользователя ---");
        User newUser = User.builder()
                .name("Alice")
                .username("alice_dev")
                .email("alice@example.com")
                .build();

        Task1.createUser(newUser);
        System.out.println("Пользователь создан (эмуляция)");

        System.out.println("\n--- 2. Получение всех пользователей ---");
        List<User> allUsers = Task1.getAllUsers();
        System.out.println("Всего пользователей: " + allUsers.size());
        System.out.println("Последний в списке: " + allUsers.getLast().getUsername());

        System.out.println("\n--- 3. Получение пользователя по ID ---");
        int targetId = 1;
        User userById = Task1.getUser(targetId);
        System.out.println("Найден по ID " + targetId + ": " + userById.getName());

        System.out.println("\n--- 4. Обновление пользователя ---");
        userById.setName("Updated Name");
        Task1.updateUser(userById);
        System.out.println("Данные обновлены");

        System.out.println("\n--- 5. Получение по Username ---");
        String username = "Chelsey Dietrich";
        User userByNick = Task1.getUser(username);
        System.out.println("Найден по нику " + username + ": " + userByNick.getEmail());

        System.out.println("\n--- 6. Удаление пользователя ---");
        int userIdToDelete = 10;
        Task1.deleteUser(userIdToDelete);
        System.out.println("Пользователь с ID " + userIdToDelete + " удален (код 2xx)");

        System.out.println("\n--- 7. Запись комментариев к посленему посту пользователя в файл ---");
        String filePath = "comments.json";
        Task2.writeAllCommentsToLastPostToFile(userByNick, filePath);
        System.out.println("Комментарии к последнему посту пользователя " + userByNick.getName() + " записаны в файл " + filePath);

        System.out.println("\n--- 8. Вывод всех открытых заданий пользователя " + userByNick.getName() + " ---");
        Task3.printAllOpenTasksToUser(userByNick);


    } catch (TaskError e) {
        System.err.println("Ошибка выполнения задачи: объект не найден или сервер вернул ошибку.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}