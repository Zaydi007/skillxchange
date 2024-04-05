import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SkillXChangeDatabase {
    public static void main(String[] args) {
        final int DATABASE_SIZE = 50; // Size of the database
        String userNames[] = new String[DATABASE_SIZE]; // Array to hold users usernames
        String userPasswords[] = new String[DATABASE_SIZE]; // Array to hold users passwords
        String userSkills[] = new String[DATABASE_SIZE]; // Array to hold users skills
        String userSkillsToLearn[] = new String[DATABASE_SIZE]; // Array to hold skills that the user wants to learn

        int counter = 0; // Declare and initialize counter outside the try block

        try {
            File userDataFile = new File("userData.txt");

            // Create the file if it doesn't exist
            if (!userDataFile.exists()) {
                userDataFile.createNewFile();
                System.out.println("File created: userData.txt");
            }

            // Read data from the file
            BufferedReader reader = new BufferedReader(new FileReader(userDataFile));
            String line;
            while ((line = reader.readLine()) != null && counter < DATABASE_SIZE) {
                String[] userData = line.split(","); // Split the line by commas
                if (userData.length >= 4) { // Make sure there are enough elements
                    userNames[counter] = userData[0];
                    userPasswords[counter] = userData[1];
                    userSkills[counter] = userData[2];
                    userSkillsToLearn[counter] = userData[3];
                    counter++;
                }
            }
            reader.close();

          //exception handling
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file.");
            e.printStackTrace();
        }

        // Print the data for verification
        for (int i = 0; i < counter; i++) {
            System.out.println("User " + (i + 1) + ":");
            System.out.println("Name: " + userNames[i]);
            System.out.println("Password: " + userPasswords[i]);
            System.out.println("Skills: " + userSkills[i]);
            System.out.println("Skills to learn: " + userSkillsToLearn[i]);
            System.out.println();
        }
    }
}
