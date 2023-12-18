import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public  class GameOfLifeModel {
    private List<String[][]> patterns;
    private String[][] currentGeneration;
    private String liveChar;
    private int currentPatternIndex = 0;

    public GameOfLifeModel(List<String[][]> patterns, String liveChar) {
        this.patterns = patterns;
        this.liveChar = liveChar;
        this.currentGeneration = patterns.get(currentPatternIndex);
    }

    public String[][] getCurrentGeneration() {
        return currentGeneration;
    }

    public void calculateNextGeneration() {
        int rows = currentGeneration.length;
        int cols = currentGeneration[0].length;
        String[][] nextGeneration = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);

                if (currentGeneration[i][j].equals(liveChar)) {
                    nextGeneration[i][j] = (liveNeighbors < 2 || liveNeighbors > 3) ? " " : liveChar;
                } else {
                    nextGeneration[i][j] = (liveNeighbors == 3) ? liveChar : " ";
                }
            }
        }

        currentGeneration = nextGeneration;
        updatePatternFile();
    }

    private int countLiveNeighbors(int row, int col) {
        int count = 0;
        int rows = currentGeneration.length;
        int cols = currentGeneration[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborRow = row + i;
                int neighborCol = col + j;

                if (!(i == 0 && j == 0) &&
                        neighborRow >= 0 && neighborRow < rows &&
                        neighborCol >= 0 && neighborCol < cols &&
                        currentGeneration[neighborRow][neighborCol].equals(liveChar)) {
                    count++;
                }
            }
        }

        return count;
    }

    private void updatePatternFile() {
        String patternFilePath = "pattern" + currentPatternIndex + ".txt";
        try (PrintWriter writer = new PrintWriter(patternFilePath)) {
            for (String[] row : currentGeneration) {
                for (String cell : row) {
                    writer.print(cell);
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNextGeneration(String[][] nextGeneration) {
        this.currentGeneration = nextGeneration;
    }
}
