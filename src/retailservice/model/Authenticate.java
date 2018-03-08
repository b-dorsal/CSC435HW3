package retailservice.model;

public class Authenticate {

    public boolean authenticate(String username, String password) {
        User user = User.getUser(username);
        if ((username.equalsIgnoreCase(user.getUsername())) && (password.equals(user.getPassword()))) {
            return true;
        } else {
            return false;
        }
    }
}
