package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MazeGenerator {

    private int dimensionX, dimensionY; // dimension of maze
    private int gridDimensionX, gridDimensionY; // dimension of output grid
    
    private char[][] grid; // output grid
    private Cell[][] cells; // 2d array of Cells
    private Random random = new Random(); // The random object

    private int playerPosX, playerPosY;

    // initialize with x and y the same
    public MazeGenerator(int aDimension) {
        // Initialize
        this(aDimension, aDimension);
    }

    // constructor with different x and y
    public MazeGenerator(int xDimension, int yDimension){
        this.dimensionX = xDimension;
        this.dimensionY = yDimension;
        gridDimensionX = dimensionX * 4 + 1;
        gridDimensionY = dimensionY * 2 + 1;
        grid = new char[gridDimensionX][gridDimensionY];
        
        // newMaze();
        // draw();
    }

    public void newMaze(){
        init();
        generateMaze();
        solve();
        updateGrid();
    }

    private void init() {
        // create cells
        cells = new Cell[dimensionX][dimensionY];
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                cells[x][y] = new Cell(x, y, false); // create cell (see Cell constructor)
            }
        }
    }

    // generate from upper left (In computing the y increases down often)
    private void generateMaze() {
        generateMaze(0, 0);
    }

    // generate the maze from coordinates x, y
    private void generateMaze(int x, int y) {
        generateMaze(getCell(x, y)); // generate from Cell
    }

    private void generateMaze(Cell startAt) {
        // don't generate from cell not there
        if (startAt == null)
            return;
        startAt.open = false; // indicate cell closed for generation
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(startAt);

        while (!cells.isEmpty()) {
            Cell cell;
            // this is to reduce but not completely eliminate the number
            // of long twisting halls with short easy to detect branches
            // which results in easy mazes
            if (random.nextInt(10) == 0)
                cell = cells.remove(random.nextInt(cells.size()));
            else
                cell = cells.remove(cells.size() - 1);
            // for collection
            ArrayList<Cell> neighbors = new ArrayList<>();
            // cells that could potentially be neighbors
            Cell[] potentialNeighbors = new Cell[] { getCell(cell.x + 1, cell.y), getCell(cell.x, cell.y + 1),
                    getCell(cell.x - 1, cell.y), getCell(cell.x, cell.y - 1) };
            for (Cell other : potentialNeighbors) {
                // skip if outside, is a wall or is not opened
                if (other == null || other.wall || !other.open)
                    continue;
                neighbors.add(other);
            }
            if (neighbors.isEmpty())
                continue;
            // get random cell
            Cell selected = neighbors.get(random.nextInt(neighbors.size()));
            // add as neighbor
            selected.open = false; // indicate cell closed for generation
            cell.addNeighbor(selected);
            cells.add(cell);
            cells.add(selected);
        }
    }

    // used to get a Cell at x, y; returns null out of bounds
    private Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) { // catch out of bounds
            return null;
        }
    }

    private void solve() {
        // default solve top left to bottom right
        this.solve(0, 0, dimensionX - 1, dimensionY - 1);
    }

    // solve the maze starting from the start state (A-star algorithm)
    private void solve(int startX, int startY, int endX, int endY) {
        // re initialize cells for path finding
        for (Cell[] cellrow : this.cells) {
            for (Cell cell : cellrow) {
                cell.parent = null;
                cell.visited = false;
                cell.inPath = false;
                cell.travelled = 0;
                cell.projectedDist = -1;
            }
        }
        // cells still being considered
        ArrayList<Cell> openCells = new ArrayList<>();
        // cell being considered
        Cell endCell = getCell(endX, endY);
        if (endCell == null)
            return; // quit if end out of bounds
        { // anonymous block to delete start, because not used later
            Cell start = getCell(startX, startY);
            if (start == null)
                return; // quit if start out of bounds
            start.projectedDist = getProjectedDistance(start, 0, endCell);
            start.visited = true;
            openCells.add(start);
        }
        boolean solving = true;
        while (solving) {
            if (openCells.isEmpty())
                return; // quit, no path
            // sort openCells according to least projected distance
            Collections.sort(openCells, new Comparator<Cell>() {
                @Override
                public int compare(Cell cell1, Cell cell2) {
                    double diff = cell1.projectedDist - cell2.projectedDist;
                    if (diff > 0)
                        return 1;
                    else if (diff < 0)
                        return -1;
                    else
                        return 0;
                }
            });
            Cell current = openCells.remove(0); // pop cell least projectedDist
            if (current == endCell)
                break; // at end
            for (Cell neighbor : current.neighbors) {
                double projDist = getProjectedDistance(neighbor, current.travelled + 1, endCell);
                if (!neighbor.visited || // not visited yet
                        projDist < neighbor.projectedDist) { // better path
                    neighbor.parent = current;
                    neighbor.visited = true;
                    neighbor.projectedDist = projDist;
                    neighbor.travelled = current.travelled + 1;
                    if (!openCells.contains(neighbor))
                        openCells.add(neighbor);
                }
            }
        }
        // create path from end to beginning
        Cell backtracking = endCell;
        backtracking.inPath = true;
        while (backtracking.parent != null) {
            backtracking = backtracking.parent;
            backtracking.inPath = true;
        }
    }

    // get the projected distance
    // (A star algorithm consistent)
    private double getProjectedDistance(Cell current, double travelled, Cell end) {
        return travelled + Math.abs(current.x - end.x) + Math.abs(current.y - current.x);
    }

    // construct the maze and solution
    private void updateGrid() {
        char backChar = ' ', wallChar = 'X', pathChar = '*', startChar = 'S' , winChar = 'W';
        // fill background
        for (int x = 0; x < gridDimensionX; x++) {
            for (int y = 0; y < gridDimensionY; y++) {
                grid[x][y] = wallChar;
            }
        }
        // build walls
        for (int x = 0; x < gridDimensionX; x++) {
            for (int y = 0; y < gridDimensionY; y++) {
                if (x % 4 == 0 || y % 2 == 0)
                    grid[x][y] = wallChar;
            }
        }
        // representation
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                Cell current = getCell(x, y);
                int gridX = x * 4 + 2, gridY = y * 2 + 1;
                if (current.inPath) {
                    grid[gridX][gridY] = pathChar;
                    if (current.isCellBelowNeighbor())
                        if (getCell(x, y + 1).inPath) {
                            grid[gridX][gridY + 1] = pathChar;
                            // grid[gridX + 1][gridY + 1] = backChar;
                            // grid[gridX - 1][gridY + 1] = backChar;
                        } else {
                            grid[gridX][gridY + 1] = backChar;
                            // grid[gridX + 1][gridY + 1] = backChar;
                            // grid[gridX - 1][gridY + 1] = backChar;
                        }
                    if (current.isCellRightNeighbor())
                        if (getCell(x + 1, y).inPath) {
                            grid[gridX + 2][gridY] = pathChar;
                            grid[gridX + 1][gridY] = pathChar;
                            grid[gridX + 3][gridY] = pathChar;
                        } else {
                            grid[gridX + 2][gridY] = backChar;
                            grid[gridX + 1][gridY] = backChar;
                            grid[gridX + 3][gridY] = backChar;
                        }
                } else {
                    grid[gridX][gridY] = backChar;
                    if (current.isCellBelowNeighbor()) {
                        grid[gridX][gridY + 1] = backChar;
                        // grid[gridX + 1][gridY + 1] = backChar;
                        // grid[gridX - 1][gridY + 1] = backChar;
                    }
                    if (current.isCellRightNeighbor()) {
                        grid[gridX + 2][gridY] = backChar;
                        grid[gridX + 1][gridY] = backChar;
                        grid[gridX + 3][gridY] = backChar;
                    }
                }
            }
        }
        grid[2][0] = startChar;
        playerPosX = 2; playerPosY = 1;
        grid[gridDimensionX-3][gridDimensionY-1] = winChar;
    }

    public char[][] getGrid() {
        return grid;
    }
    public int getGridDimensionX() {
        return gridDimensionX;
    }
    public int getGridDimensionY() {
        return gridDimensionY;
    }
    public int getPlayerPosX() {
        return playerPosX;
    }
    public int getPlayerPosY() {
        return playerPosY;
    }

    // simply prints the map
    public void draw() {
        System.out.print(this);
    }

    // forms a meaningful representation
    @Override
    public String toString() {
        updateGrid();
        String output = "";
        for (int y = 0; y < gridDimensionY; y++) {
            for (int x = 0; x < gridDimensionX; x++) {
                output += grid[x][y];
            }
            output += "\n";
        }
        return output;
    }

}