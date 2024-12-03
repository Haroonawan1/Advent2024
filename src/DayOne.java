import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("data/day1Input");
        System.out.println("Star One: " + starOne(fileData));
        System.out.println("Star Two: " + starTwo(fileData));
    }

    public static int starOne(ArrayList<String> fileData) {
        int[] leftData = new int[fileData.size()];
        int[] rightData = new int[fileData.size()];

        for (int i = 0; i < fileData.size(); i++) {
            leftData[i] = Integer.parseInt(fileData.get(i).substring(0, fileData.get(i).indexOf(" ")));
            rightData[i] = Integer.parseInt(fileData.get(i).substring(fileData.get(i).indexOf(" ") + 3));
        }

        Arrays.sort(leftData);
        Arrays.sort(rightData);

        int totalDistance = 0;
        for (int i = 0; i < fileData.size(); i++) {
            totalDistance += Math.abs(leftData[i] - rightData[i]);
        }
        return totalDistance;
    }

    public static int starTwo(ArrayList<String> fileData) {
        int[] leftData = new int[fileData.size()];
        int[] rightData = new int[fileData.size()];

        for (int i = 0; i < fileData.size(); i++) {
            leftData[i] = Integer.parseInt(fileData.get(i).substring(0, fileData.get(i).indexOf(" ")));
            rightData[i] = Integer.parseInt(fileData.get(i).substring(fileData.get(i).indexOf(" ") + 3));
        }

        int similarityScore = 0;
        for (int leftDatum : leftData) {
            int count = 0;
            for (int rightDatum : rightData) {
                if (leftDatum == rightDatum) {
                    count++;
                }
            }
            similarityScore += (leftDatum * count);
        }

        return similarityScore;
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