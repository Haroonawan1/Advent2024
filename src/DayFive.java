import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayFive {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/day5Input");
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

            if (!valid) {
                ArrayList<String> orderedInvalid = new ArrayList<>();

                for (int j = 0; j < splitPage.size(); j++) {
                    orderedInvalid.add(splitPage.get(j));
                    int idx = j;
                    while (!isValid(orderedInvalid, rules)) {
                        Collections.swap(orderedInvalid, idx, idx - 1);
                        idx--;
                    }
                }
                sum += Integer.parseInt(orderedInvalid.get(orderedInvalid.size() / 2));
            }
        }

        return sum;
    }

    public static boolean isValid(ArrayList<String> splitPage, ArrayList<String> rules) {
        boolean valid = true;

        for (int j = 0; j < splitPage.size(); j++) {
            for (String rule : rules) {
                String firstNum = rule.substring(0, 2);
                String secondNum = rule.substring(3);

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