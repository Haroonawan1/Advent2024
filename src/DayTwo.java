import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        for (String row : fileData) {
            String[] splitData = row.split(" ");
            ArrayList<Integer> deltas = new ArrayList<>();

            for (int j = 0; j < splitData.length - 1; j++) {
                deltas.add(Integer.parseInt(splitData[j + 1]) - Integer.parseInt(splitData[j]));
            }

            boolean valid = isValid(deltas);

            if (!valid) {
                for (int k = 0; k < deltas.size(); k++) {
                    int currentRemoved = deltas.remove(k);

                    if (isValid(deltas)) {
                        valid = true;
                    }

                    deltas.add(k, currentRemoved);
                }
            }

            if (valid) {
                validReports++;
            }
        }

        return validReports;
    }

    public static boolean isValid(ArrayList<Integer> deltas) {
        boolean valid = true;
        for (int i = 0; i < deltas.size() - 1; i++) {
            if (!(1 <= Math.abs(deltas.get(i)) && Math.abs(deltas.get(i)) <= 3 && (deltas.get(i) * deltas.get(i + 1) > 0))) {
                valid = false;
            }
        }
        return valid;
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