
 class GameOfLifeView {
    private String generationDelimiter;
    private String cellDelimiter;

    public GameOfLifeView(String generationDelimiter, String cellDelimiter) {
        this.generationDelimiter = generationDelimiter;
        this.cellDelimiter = cellDelimiter;
    }

    public void printGrid(String[][] grid, int generation) {
        System.out.println("Generation " + generation + ":");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + cellDelimiter);
            }
            System.out.println();
        }
        System.out.println(generationDelimiter);
    }
}