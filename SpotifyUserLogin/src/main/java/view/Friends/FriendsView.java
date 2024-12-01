package view.Friends;

import entities.users.UserProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FriendsView extends JPanel {
    private final JButton backButton = new JButton("Back");
    private final JPanel friendsPanel;

    /**
     * Initialize the view the friends list
     */
    public FriendsView() {
        this.defineLayout();

        friendsPanel = new JPanel();
        this.defineFriendsPanel();

        this.defineBackButton();
    }

    private void defineLayout() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
    }

    private void defineFriendsPanel() {
        friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
        friendsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(friendsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void defineBackButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Display friends list
     * @param currentUser the current user
     */
    public void displayFriends(UserProfile currentUser) {
        friendsPanel.removeAll();

        List<String> friendsList = currentUser.getFriendsListNames();

        if (!friendsList.isEmpty()) {
            handleFriendDisplay(friendsList);
        } else {
            handleEmptyFriendsList();
        }
    }

    private void handleEmptyFriendsList() {
        JLabel emptyListLabel = new JLabel("You follow no playlists");
        emptyListLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        emptyListLabel.setHorizontalAlignment(SwingConstants.CENTER);
        friendsPanel.add(emptyListLabel);
    }

    private void handleFriendDisplay(List<String> friendsList) {
        for (String friend : friendsList) {
            JPanel friendItem = individualFriendDisplayHelper();

            JLabel friendLabel = individualFriendLabelHelper(friend);
            friendItem.add(friendLabel, BorderLayout.CENTER);

            friendsPanel.add(friendItem);
            friendsPanel.add(Box.createVerticalStrut(10)); // Spacing between items
        }
    }

    private JPanel individualFriendDisplayHelper() {
        JPanel friendItem = new JPanel();
        friendItem.setLayout(new BorderLayout());
        friendItem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        friendItem.setBackground(Color.WHITE);
        return friendItem;
    }

    private JLabel individualFriendLabelHelper(String friendName) {
        JLabel friendLabel = new JLabel(friendName);
        friendLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        return friendLabel;
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}
