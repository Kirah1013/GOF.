class GameOfLifeController {
    private GameOfLifeModel model;
    private GameOfLifeView view;
    private String[] patternFilePaths;

    public GameOfLifeController(GameOfLifeModel model, GameOfLifeView view, String[] patternFilePaths) {
        this.model = model;
        this.view = view;
        this.patternFilePaths = patternFilePaths;
    }

    public void runSimulation(int generations, int sleepTime) {
        for (int generation = 1; generation <= generations; generation++) {
            // Calculate the next generation based on Conway's rules
            model.calculateNextGeneration();
            // Print the current generation with decorations
            view.printGrid(model.getCurrentGeneration(), generation);

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

