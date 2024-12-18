import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DaySix {
    private static int gYPos;
    private static int gXPos;
    private static int distinctPositions;
    private static String currentTile;

    public static void main(String[] args) {
        gXPos = 0;
        gYPos = 0;
        distinctPositions = 0;
        currentTile = ".";

        ArrayList<String> fileData = getFileData("data/day6Input");
        System.out.println("Star One: " + starOne(fileData));
        //System.out.println("Star Two: " + starTwo(fileData));
    }

    public static int starOne(ArrayList<String> fileData) {
        String[][] map = new String[fileData.size()][fileData.getFirst().length()];

        for (int i = 0; i < fileData.size(); i++) {
            map[i] = fileData.get(i).split("");
            for (int j = 0; j < map[i].length; j++) {
                if ("<>^v".contains(map[i][j])) {
                    gYPos = i;
                    gXPos = j;
                }
            }
        }

        boolean doneWalking = false;
        while (!doneWalking) {
            switch (map[gYPos][gXPos]) {
                case "<" -> doneWalking = move(map, 0, -1, "<", "^");
                case ">" -> doneWalking = move(map, 0, 1, ">", "v");
                case "^" -> doneWalking = move(map, -1, 0, "^", ">");
                case "v" -> doneWalking = move(map, 1, 0, "v", "<");
            }
        }

        return distinctPositions + 1;
    }

    public static boolean move(String[][] map, int deltaY, int deltaX, String currentDir, String nextDir) {
        if (gYPos + deltaY < 0 || gYPos + deltaY == map.length || gXPos + deltaX < 0 || gXPos + deltaX > map[0].length) {
            return true;
        }

        if (gYPos - 1 >= 0 && !map[gYPos + deltaY][gXPos + deltaX].equals("#")) {
            if (currentTile.equals(".")) {
                distinctPositions++;
                map[gYPos][gXPos] = "X";
            }
            currentTile = map[gYPos + deltaY][gXPos + deltaX];
            map[gYPos + deltaY][gXPos + deltaX] = currentDir;
            gYPos += deltaY;
            gXPos += deltaX;

        } else {
            map[gYPos][gXPos] = nextDir;
        }

        return false;
    }

    public static int starTwo(ArrayList<String> fileData) {
        return 0;
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            fileData.add("getFileData failed");
            return fileData;
        }
    }
}