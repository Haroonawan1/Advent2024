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
                    boolean[] checks = {
                            checkLeftRight(data[i], j, "left"),
                            checkLeftRight(data[i], j, "right"),
                            checkUpDown(data, i, j, "up"),
                            checkUpDown(data, i, j, "down"),
                            checkDiags(data, i, j, "topRight"),
                            checkDiags(data, i, j, "topLeft"),
                            checkDiags(data, i, j, "bottomRight"),
                            checkDiags(data, i, j, "bottomLeft")
                    };

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

    public static boolean checkDiags(String[][] data, int row, int col, String direction) {
        int rowModifier = 1;
        int colModifier = 1;

        switch (direction) {
            case "topRight" -> rowModifier *= -1;
            case "topLeft" -> {
                rowModifier *= -1;
                colModifier *= -1;
            }
            case "bottomLeft" -> colModifier *= -1;
        }

        String check = "";
        try {
            for (int i = 0; i < 4; i++) {
                check += data[row + (i * rowModifier)][col + (i * colModifier)];
            }
        } catch (Exception _) {}

        return check.equals("XMAS");
    }

    public static int starTwo(ArrayList<String> fileData) {
        String[][] data = new String[fileData.size()][fileData.getFirst().length()];
        for (int i = 0; i < fileData.size(); i++) {
            data[i] = fileData.get(i).split("");
        }

        int sum = 0;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {

                if ("MS".contains(data[i][j])) {
                    String check = "";
                    try {
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                if ((k % 2 == 0 && l % 2 == 0) || (k % 2 != 0 && l % 2 != 0)) {
                                    check += data[i + k][j + l];
                                }
                            }
                        }

                        ArrayList<String> checkArr = new ArrayList<>(Arrays.asList(check.split("")));
                        checkArr.sort(String.CASE_INSENSITIVE_ORDER);
                        check = String.join("", checkArr);

                        if (check.equals("AMMSS")) {
                            sum++;
                        }

                        //System.out.println(check);
                    } catch (Exception _) {}
                }
            }
        }

        return sum;
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