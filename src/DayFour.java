import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayFour {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/day4Input");
        System.out.println("Star One: " + starOne(fileData));
        System.out.println("Star Two: " + starTwo(fileData));
    }

    public static int starOne(ArrayList<String> fileData) {
        String[][] data = new String[fileData.size()][fileData.getFirst().length()];
        for (int i = 0; i < fileData.size(); i++) {
            data[i] = fileData.get(i).split("");
        }

        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].equals("X")) {
                    if (j + 3 < data[i].length && data[i][j + 3].equals("XMAS")) {
                        System.out.println();
                    }
                }
            }
        }

        return sum;
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