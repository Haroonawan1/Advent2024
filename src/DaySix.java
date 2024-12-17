import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DaySix {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/testInput");
        System.out.println("Star One: " + starOne(fileData));
        System.out.println("Star Two: " + starTwo(fileData));
    }

    public static int starOne(ArrayList<String> fileData) {
        String[][] map = new String[fileData.size()][fileData.getFirst().length()];
        int gXPos = 0;
        int gYPos = 0;

        for (int i = 0; i < fileData.size(); i++) {
            map[i] = fileData.get(i).split("");
            for (int j = 0; j < map[i].length; j++) {
                if ("<>^v".contains(map[i][j])) {
                    gYPos = i;
                    gXPos = j;
                }
            }
        }

        int distinctPositions = 0;
        boolean doneWalking = false;
        while (!doneWalking) {
            //check direction
            //based on direction check next item
            //if a ".", move increase sum and make "X"
            //if "#" change direction

            String currentTileStatus = "";
            switch (map[gYPos][gXPos]) {
                case "<" -> {
                    if (gXPos - 1 >= 0 && !map[gYPos][gXPos - 1].equals("#")) {
                        map[gYPos][gXPos] = "X";
                        currentTileStatus = map[gYPos][gXPos - 1];
                        map[gYPos][gXPos - 1] = "<";
                        if ()
                    }
                }
            }
        }

        return 0;
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