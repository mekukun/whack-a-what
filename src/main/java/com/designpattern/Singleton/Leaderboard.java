package com.designpattern.Singleton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class Leaderboard {

    private static Leaderboard leaderboard_instance = null;

    private Leaderboard() {
        createLeaderboard();
    }

    public static synchronized Leaderboard getInstance() {
        if (leaderboard_instance == null)
            leaderboard_instance = new Leaderboard();

        return leaderboard_instance;
    }

    private void createLeaderboard() {
        File file = new File("Leaderboard.txt");
        // Check if the file already exists
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Leaderboard.txt"))) {
                writer.write(
                        "== Leaderboard ==\nRank || Name || Score\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ObservableList<LeaderboardEntry> extract() {
        ObservableList<LeaderboardEntry> entries = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader("Leaderboard.txt"))) {
            // Skip the first two lines (== Leaderboard == and Rank || Name || Score)
            reader.readLine();
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|\\|");
                if (parts.length == 3) {
                    int rank = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int score = Integer.parseInt(parts[2].trim());
                    entries.add(new LeaderboardEntry(rank, name, score));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    private ObservableList<LeaderboardEntry> updateLeaderboard(String newName, int newScore) {
        ObservableList<LeaderboardEntry> entries = extract();

        // Check if an entry with the same name already exists
        boolean nameExists = false;
        LeaderboardEntry existingEntry = null;
        for (LeaderboardEntry entry : entries) {
            if (entry.getName().equals(newName)) {
                nameExists = true;
                existingEntry = entry;
                break;
            }
        }

        if (nameExists) {
            // If the name already exists, update the score
            if (newScore > existingEntry.getScore()) {
                existingEntry.setScore(newScore);
            }
        } else {
            // If the name doesn't exist, add a new entry
            entries.add(new LeaderboardEntry(0, newName, newScore));
        }

        // Sort the entries by score in descending order
        entries.sort(Comparator.comparingInt(LeaderboardEntry::getScore).reversed());

        // Update the ranks
        for (int i = 0; i < entries.size(); i++) {
            entries.get(i).setRank(i + 1);
        }

        // Write the updated leaderboard to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Leaderboard.txt"))) {
            writer.write("== Leaderboard ==\nRank || Name || Score\n");
            for (LeaderboardEntry entry : entries) {
                writer.write(entry.print() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    public void paintLeaderboard(TableView<LeaderboardEntry> table) {
        ObservableList<LeaderboardEntry> entries = updateLeaderboard(Game.getInstance().getUsername(),
                Game.getInstance().getScore());

        table.setItems(entries);
    }
}
