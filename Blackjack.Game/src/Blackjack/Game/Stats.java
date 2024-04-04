package Blackjack.Game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.*;

public class Stats {

    public static int plays, money, moneyBet, moneyWon, moneyLost, profit;

    public static final String statsFile = "resources/stats.txt";
    int newBet;

    public Stats() throws FileNotFoundException {
        Stats.plays = searchFile("plays");
        Stats.money = searchFile("money");
        Stats.moneyBet = searchFile("moneyBet");
        Stats.moneyWon = searchFile("moneyWon");
        Stats.moneyLost = searchFile("moneyLost");
      //  Stats.profit = searchFile("profit");
       // Stats.newBet = searchFile("newBet");
    }

    public static int searchFile(String valueToFind) throws FileNotFoundException { //this is used for the constructer to print stats
        String searchPattern = "\\b" + valueToFind + "\\b\\s+(\\d+)";
        int foundValue = -1;

        try {
            FileReader fileReader = new FileReader(statsFile);
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                //fileContent = new StringBuilder;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }

            Pattern pattern = Pattern.compile(searchPattern);
            Matcher matcher = pattern.matcher(fileContent.toString());

            if (matcher.find()) {
                foundValue = Integer.parseInt(matcher.group(1));
            } else {
                System.err.println("Value not found in the file." +matcher);
            }
        } catch (IOException ex) {
            System.err.println("Error finding the file" + ex.getMessage());
            throw new FileNotFoundException("File not found: " + statsFile);
        }
        return foundValue;
    }

    public static void increasePlays() { //this was the first file write code i wrote and i reused it to make the updateStatsFile
        String searchPattern = "\\bplays\\b\\s+(\\d+)";
        int foundValue = -1;
        try {
            FileReader fileReader = new FileReader(statsFile);

            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }
        } catch (IOException ex) {
            System.err.println("Error reading the file. " + ex.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(statsFile))) {
            File tempFile = new File(statsFile + ".tmp");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    if (!found && line.matches(searchPattern)) {
                        found = true;
                        foundValue = extractInteger(line, searchPattern);
                        line = line.replaceAll(searchPattern, "plays " + (foundValue + 1));//this will increment the stats value to file
                    }
                    writer.write(line + System.lineSeparator());
                }
            }
            reader.close();

            Files.copy(tempFile.toPath(), Paths.get(statsFile), StandardCopyOption.REPLACE_EXISTING);
            tempFile.delete();
        } catch (IOException e) {
            System.err.println("Error editing the file." + e.getMessage());
        }
    }

    public static void updateStatsFile(String statToUpdate, int newVal) throws FileNotFoundException, IOException {
        String searchPattern = "\\b" + statToUpdate + "\\b\\s+(\\d+)";
        boolean found = false;

        try {
            FileReader fileReader = new FileReader(statsFile);

            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                StringBuilder fileContent = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }
        } catch (IOException ex) {
            System.err.println("Error reading the file: " + ex.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(statsFile))) {
            File tempFile = new File(statsFile + ".tmp");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    if (!found && line.matches(searchPattern)) {
                        found = true;
                        line = line.replaceAll(searchPattern, statToUpdate + " " + newVal);
                    }
                    writer.write(line + System.lineSeparator());
                }
            }
            reader.close();

            Files.copy(tempFile.toPath(), Paths.get(statsFile), StandardCopyOption.REPLACE_EXISTING);
            tempFile.delete();
        } catch (IOException e) {
            System.err.println("Error editing the file.");
        }
    }

    public static int extractInteger(String str, String searchKey) {
        Pattern pattern = Pattern.compile(searchKey);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

}
