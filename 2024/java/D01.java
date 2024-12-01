import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** D01 */
public class D01 {

  public static void main(String[] args) {
    File star1 = new File("/home/hypnotics/dev/proj/aoc/2024/inputs/D01-1.txt");
    File star1Test = new File("/home/hypnotics/dev/proj/aoc/2024/inputs/D01s-1.txt");
    System.out.println("Star One Test: " + starOne(star1Test, 1));
    System.out.println("Star One: " + starOne(star1, 5));
  }

  private static int starOne(File file, int size) {
    try {
      Scanner input = new Scanner(file);
      ArrayList<Integer> first = new ArrayList<>();
      ArrayList<Integer> second = new ArrayList<>();
      String data;
      while (input.hasNextLine()) {
        data = input.nextLine();
        first.add(Integer.parseInt(data.substring(0, size)));
        second.add(Integer.parseInt(data.substring(size + 3)));
      }
      Collections.sort(first);
      Collections.sort(second);
      int tally = 0;
      for (int i = 0; i < first.size(); i++) {
        tally += Math.abs(second.get(i) - first.get(i));
      }
      input.close();
      return tally;
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: File not found");
      System.out.println(e.getMessage());
      System.exit(1);
    }
    return -1;
  }
}
