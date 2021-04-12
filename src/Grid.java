import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Grid {
    private final Cell[][] gridArea;
    private int size;
    private static final double marketPct = 0.3;
    private static final double inaccessPct = 0.2;
    Grid(){
        System.out.println("Initializing grid......");
        size = pickSize();
        gridArea = new Cell[size][size];
        instantiateCell();
    }

    private int pickSize(){
        int size;
        try {
            System.out.println("Please enter the size of playing area[8-30]");
            Scanner scanner = new Scanner(System.in);
            size = scanner.nextInt();
            if (size < 8 || size > 30){
                System.out.println("Out of Boundary. Choose 8 automatically.\n");
                size = 8;
            }
        }catch (InputMismatchException e){
            System.out.println("Input Mismatch. Choose 8 automatically.\n");
            size = 8;
        }
        return size;
    }

    private void instantiateCell(){
        int total = size * size;
        gridArea[0][0] = new MarketCell(0, 0);
        Random rnd = new Random(10);
        for (int i = 0; i < total * inaccessPct; i++) {
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
