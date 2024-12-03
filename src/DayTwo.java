import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/day2Input");
        System.out.println("Star One: " + starOne(fileData));
        System.out.println("Star Two: " + starTwo(fileData));
    }

    public static int starOne(ArrayList<String> fileData) {
        int validReports = 0;

        for (String fileDatum : fileData) {
            String[] splitData = fileDatum.split(" ");
            boolean valid = true;
            int initialDelta = Integer.parseInt(splitData[0]) - Integer.parseInt(splitData[1]);

            for (int j = 0; j < splitData.length - 1; j++) {
                int delta = Integer.parseInt(splitData[j]) - Integer.parseInt(splitData[j + 1]);
                if (!(1 <= Math.abs(delta) && Math.abs(delta) <= 3 && (initialDelta * delta) > 0)) {
                    valid = false;
                }
            }

            if (valid) {
                validReports++;
            }
        }

        return validReports;
    }

    public static int starTwo(ArrayList<String> fileData) {
        int validReports = 0;

        for (String fileDatum : fileData) {
            String[] splitData = fileDatum.split(" ");
            boolean valid = true;
            int errorCount = 0;
            int initialDelta = Integer.parseInt(splitData[0]) - Integer.parseInt(splitData[1]);

            for (int j = 0; j < splitData.length - 1; j++) {
                int delta = Integer.parseInt(splitData[j]) - Integer.parseInt(splitData[j + 1]);
                if (!(1 <= Math.abs(delta) && Math.abs(delta) <= 3 && (initialDelta * delta) > 0)) {
                    errorCount++;
                }
            }

            if (errorCount <= 1) {
                validReports++;
            }
        }

        return validReports;
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