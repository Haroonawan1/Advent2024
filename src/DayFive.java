import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayFive {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/testInput");
        System.out.println("Star One: " + starOne(fileData));
        System.out.println("Star Two: " + starTwo(fileData));
    }

    public static int starOne(ArrayList<String> fileData) {
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> pages = new ArrayList<>();

        for (String dataRow : fileData) {
            if (dataRow.contains("|")) {
                rules.add(dataRow);
            } else {
                pages.add(dataRow);
            }
        }

        for (int i = 0; i < pages.size(); i++) {
            String[] splitPage = pages.get(i).split(",");
            System.out.println(splitPage[0]);
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