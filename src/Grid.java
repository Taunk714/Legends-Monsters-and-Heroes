import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Grid {
    private final Cell[][] gridArea;
    private int size;
    private static final double marketPct;
    private static final double inaccessiblePct;
    static {
        marketPct = configUtil.getConfigInt("marketPct")/100.0;
        inaccessiblePct = configUtil.getConfigInt("inaccessiblePct")/100.0;
    }

    Grid(){
        System.out.println("Initializing grid......");
        size = pickSize();
        gridArea = new Cell[size][size];
        instantiateCell();
    }

    private int pickSize(){
        int size;
        int gridDefault = configUtil.getConfigInt("gridDefault");
        int gridLower = configUtil.getConfigInt("gridLower");
        int gridUpper = configUtil.getConfigInt("gridUpper");
        try {
            System.out.printf("Please enter the size of playing area[%d-%d]\n", gridLower, gridUpper);
            Scanner scanner = new Scanner(System.in);
            size = scanner.nextInt();
            if (size < gridLower|| size > gridUpper){
                System.out.printf("Out of Boundary. Choose %d automatically.\n\n", gridDefault);
                size = gridDefault;
            }
        }catch (InputMismatchException e){
            System.out.printf("Input Mismatch. Choose %d automatically.\n\n", gridDefault);
            size = gridDefault;
        }
        return size;
    }

    private void instantiateCell(){
        int total = size * size;
        gridArea[0][0] = new MarketCell(0, 0);
        Random rnd = new Random(10);
        for (int i = 0; i < total * inaccessiblePct; i++) {
            int row, col;
            int rndNum = rnd.nextInt(total);
            row = rndNum / size;
            col = rndNum % size;
            if (gridArea[row][col] == null){
                gridArea[row][col] = new InaccessibleCell(row, col);
            }
        }

        for (int i = 0; i < total * marketPct - 1; i++) {
            int row, col;
            int rndNum = rnd.nextInt(total);
            row = rndNum / size;
            col = rndNum % size;
            if (gridArea[row][col] == null){
                gridArea[row][col] = new MarketCell(row, col);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (gridArea[i][j] == null){
                    gridArea[i][j] = new CommonCell(i, j);
                }
            }
        }
    }

    public void enter(Team heroes, int[] pos){
        gridArea[pos[0]][pos[1]].enter(heroes);
    }

    public int getSize() {
        return size;
    }

    public Cell getCell(int row, int col){
        return gridArea[row][col];
    }
}
