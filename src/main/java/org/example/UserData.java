package org.example;


public class UserData {
    public static User createUser() {
        User user = new User();
        final String email = "test-super-111021@yandex.ru";
        final String password = "12345678";
        final String name = "Maria22435";
        return new User(email, password, name);
    }

    public static User createWrongUser (){
        User user = new User();
        final String email = "test-super-500@yandex.ru";
        final String password = "12";
        final String name = "Elena";
        return new User(email, password, name);
    }
}
