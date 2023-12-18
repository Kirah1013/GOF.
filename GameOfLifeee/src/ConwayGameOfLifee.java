
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConwayGameOfLifee {
    public static void main(String[] args) {
        // Provide paths to pattern files
        String[] patternFilePaths = {
                "Exploder.pat",
                "Spaceship.pat"

                // Add more pattern file paths as needed
        };

        // User Input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type number of rows: ");
        int rows = scanner.nextInt();

        System.out.println("Type number of columns: ");
        int columns = scanner.nextInt();

        System.out.println("Type number of generations: ");
        int generations = scanner.nextInt();

        // Configure other parameters
        int sleepTime = 1000;
        String liveChar = "*";
        String generationDelimiter = "===============";
        String cellDelimiter = " ";

        // Load patterns from files
        List<String[][]> patterns = new ArrayList<>();
        for (String patternFilePath : patternFilePaths) {
            patterns.add(loadPatternFromFile(patternFilePath, rows, columns));
        }

        // Create a model instance with loaded patterns
        GameOfLifeModel model = new GameOfLifeModel(patterns, liveChar);

        // Create a view instance with specified decorations
        GameOfLifeView view = new GameOfLifeView(generationDelimiter, cellDelimiter);

        // Create a controller instance
        GameOfLifeController controller = new GameOfLifeController(model, view, patternFilePaths);

        // Run the simulation
        controller.runSimulation(generations, sleepTime);
    }

    private static String[][] loadPatternFromFile(String patternFilePath, int rows, int columns) {
        List<String> lines = readLinesFromFile(patternFilePath);

        if (lines.size() == 0) {
            throw new IllegalArgumentException("Pattern file is empty: " + patternFilePath);
        }

        String[][] pattern = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            String line = i < lines.size() ? lines.get(i) : "";
            for (int j = 0; j < columns; j++) {
                pattern[i][j] = (j < line.length()) ? String.valueOf(line.charAt(j)) : " ";
            }
        }

        return pattern;
    }

    private static List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    }

