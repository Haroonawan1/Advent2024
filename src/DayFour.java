import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayFour {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/testInput");
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
                    boolean[] checks = new boolean[8];
                    checks[0] = checkLeftRight(data[i], j, "left");
                    checks[1] = checkLeftRight(data[i], j, "right");
                    checks[2] = checkUpDown(data, i, j, "up");
                    checks[3] = checkUpDown(data, i, j, "down");


                    for (boolean check : checks) {
                        if (check) {
                            sum++;
                        }
                    }
                }
            }
        }

        return sum;
    }

    public static boolean checkLeftRight(String[] row, int col, String direction) {
        try {
            if (direction.equals("left")) {
                return String.join("", Arrays.copyOfRange(row, col, col + 4)).equals("XMAS");
            } else {
                return String.join("", Arrays.copyOfRange(row, col - 3, col + 1)).equals("SAMX");
            }
        } catch (Exception _) {}
        return false;
    }

    public static boolean checkUpDown(String[][] data, int row, int col, String direction) {
        String check = "";

        try {
            for (int i = 0; i < 4; i++) {
                if (direction.equals("up")) {
                    check += data[row - i][col];
                } else {
                    check += data[row + i][col];
                }
            }
        } catch (Exception _) {}

        return check.equals("XMAS");
    }

    public static boolean checkDiags(String[][] data, int row, int col) {

        //based on direction multiply by neg

        String check = "";
        try {
            for (int i = 0; i < 4; i++) {
                check += data[row + i][col + i];
            }
        } catch (Exception _) {}



        return check.equals("XMAS");
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