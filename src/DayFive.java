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

        int sum = 0;
        for (String page : pages) {
            ArrayList<String> splitPage = new ArrayList<>(Arrays.asList(page.split(",")));
            boolean valid = isValid(splitPage, rules);

            if (valid) {
                sum += Integer.parseInt(splitPage.get(splitPage.size() / 2));
            }
        }

        return sum;
    }

    public static int starTwo(ArrayList<String> fileData) {
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> pages = new ArrayList<>();
        //ArrayList<String[]> orderedInvalids = new ArrayList<>();

        for (String dataRow : fileData) {
            if (dataRow.contains("|")) {
                rules.add(dataRow);
            } else {
                pages.add(dataRow);
            }
        }

        int sum = 0;
        for (int i = 0; i < pages.size(); i++) {
            ArrayList<String> splitPage = new ArrayList<>(Arrays.asList(pages.get(i).split(",")));
            boolean valid = isValid(splitPage, rules);

            if (!valid) {
                String[] orderedInvalid = new String[splitPage.size()];
                //put in a num, check if valid
                //if invalid move latest num back one index until list is valid
                //repeat

                sum += Integer.parseInt(orderedInvalid[orderedInvalid.length / 2]);
            }
        }

        return sum;
    }

    public static boolean isValid(ArrayList<String> splitPage, ArrayList<String> rules) {
        boolean valid = true;

        for (int j = 0; j < splitPage.size(); j++) {
            for (int k = 0; k < rules.size(); k++) {
                String firstNum = rules.get(k).substring(0, 2);
                String secondNum = rules.get(k).substring(3);

                if (splitPage.get(j).equals(firstNum) && splitPage.contains(secondNum) && j > splitPage.indexOf(secondNum)) {
                    valid = false;
                }
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