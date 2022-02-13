package user;

//GAV com.example:greetings-service:1.0.0-SNAPSHOT

import java.util.List;
import java.util.Map;

public interface GreetingsService {
    String sayHi(String name);

    String findNick(Integer id);

    List<Map<String, String>> statistics();

    User info(String name);

    String exceptionCall(String name) throws Exception;

    class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public User() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}