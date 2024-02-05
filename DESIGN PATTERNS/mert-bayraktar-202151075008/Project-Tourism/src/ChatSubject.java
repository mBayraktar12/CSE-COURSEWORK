import java.util.ArrayList;
import java.util.List;

public class ChatSubject {
    private List<RoleChatObserver> observers = new ArrayList<>();

    public void addObserver(ChatObserver observer, String role) {
        observers.add(new RoleChatObserver(observer, role));
    }

    public void removeObserver(ChatObserver observer) {
        observers.removeIf(o -> o.getObserver() == observer);
    }

    public void notifyObservers(String message, String role) {
        // Check if the observer's role matches the specified role.
        for (RoleChatObserver observer : observers) {
            if (observer.getRole().equals(role)) {
                observer.update(message);
            }
        }
    }
 // Private inner class to associate observers with roles.
    private static class RoleChatObserver {
        private ChatObserver observer;
        private String role;

        public RoleChatObserver(ChatObserver observer, String role) {
            this.observer = observer;
            this.role = role;
        }

        public void update(String message) {
            observer.update(message, role);
        }

        public ChatObserver getObserver() {
            return observer;
        }

        public String getRole() {
            return role;
        }
    }
}
