import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/day3Input");
        System.out.println("Star One: " + starOne(fileData));
        System.out.println("Star Two: " + starTwo(fileData));
    }

    public static int starOne(ArrayList<String> fileData) {
        int sum = 0;
        for (String row : fileData) {
            for (int i = 0; i < row.length() - 4; i++) {
                if (row.startsWith("mul(", i)) {
                    int endIdx = row.substring(i + 4).indexOf(")") + i + 4;
                    String mul = row.substring(i, endIdx + 1);

                    try {
                        int num1 = Integer.parseInt(mul.substring(mul.indexOf("(") + 1, mul.indexOf(",")));
                        int num2 = Integer.parseInt(mul.substring(mul.indexOf(",") + 1, mul.length() - 1));
                        sum += (num1 * num2);

                    } catch (Exception _) {

                    }
                }
            }
        }
        return sum;
    }

    public static int starTwo(ArrayList<String> fileData) {
        int sum = 0;
        boolean enabled = true;

        for (String row : fileData) {
            for (int i = 0; i < row.length() - 4; i++) {
                if (row.startsWith("do()", i)) {
                    enabled = true;
                } else if (row.startsWith("don't()", i)) {
                    enabled = false;
                }

                if (enabled && row.startsWith("mul(", i)) {
                    int endIdx = row.substring(i + 4).indexOf(")") + i + 4;
                    String mul = row.substring(i, endIdx + 1);

                    try {
                        int num1 = Integer.parseInt(mul.substring(mul.indexOf("(") + 1, mul.indexOf(",")));
                        int num2 = Integer.parseInt(mul.substring(mul.indexOf(",") + 1, mul.length() - 1));
                        sum += (num1 * num2);

                    } catch (Exception _) {

                    }
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