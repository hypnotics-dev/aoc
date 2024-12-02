import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** D01 */
public class D01 {

  public static void main(String[] args) {
    File starTest = new File("/home/hypnotics/dev/proj/aoc/2024/inputs/D01s.txt");
    File star = new File("/home/hypnotics/dev/proj/aoc/2024/inputs/D01.txt");
    System.out.println("Star One Test: " + starOne(starTest, 1));
    System.out.println("Star Two Test: " + starTwo(starTest, 1));
    System.out.println("Star One Main: " + starOne(star, 5));
    System.out.println("Star Two Main: " + starTwo(star, 5));
  }

  private static int starOne(File file, int size) {
    Tuple<ArrayList<Integer>, ArrayList<Integer>> tuple = getArrays(file, size);
    int tally = 0;
    for (int i = 0; i < tuple.x.size(); i++) {
      tally += Math.abs(tuple.y.get(i) - tuple.x.get(i));
    }
    return tally;
  }

  private static Tuple<ArrayList<Integer>, ArrayList<Integer>> getArrays(File file, int size) {
    try {
      Scanner input = new Scanner(file);
      ArrayList<Integer> first = new ArrayList<>();
      ArrayList<Integer> second = new ArrayList<>();
      String data;
      while (input.hasNextLine()) {
        data = input.nextLine();
        // System.out.println(Integer.parseInt(data.substring(0, size)));
        first.add(Integer.parseInt(data.substring(0, size)));
        second.add(Integer.parseInt(data.substring(size + 3)));
      }
      Collections.sort(first);
      Collections.sort(second);
      input.close();
      return new Tuple<ArrayList<Integer>, ArrayList<Integer>>(first, second);
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: File not found");
      System.out.println(e.getMessage());
      System.exit(1);
    }
    return new Tuple<ArrayList<Integer>, ArrayList<Integer>>(null, null);
  }

  private static int starTwo(File file, int size) {
    Tuple<ArrayList<Integer>, ArrayList<Integer>> numbers = getArrays(file, size);
    int stack = numbers.x.getFirst();
    int total = 0;
    int range = 1;
    int stackY = 0;
    int offset = 0;
    Tuple<Integer, Integer> unwindReturns;
    for (Integer i : numbers.x) {
      if (stack == i) {
        range++;
      } else {
        unwindReturns = unwind(stack, offset, i, stackY, numbers.y);
        offset = unwindReturns.x;

        total += (unwindReturns.y * stack) * range;
        stack = i;
        range = 1;
        stackY = 0;
      }
    }
    if (range == 0)
      total +=
          (unwind(numbers.x.getLast(), offset, numbers.x.getLast(), stackY, numbers.y).y * stack);
    else
      total +=
          (unwind(numbers.x.getLast(), offset, numbers.x.getLast(), stackY, numbers.y).y * stack)
              * range;

    return total;
  }

  private static Tuple<Integer, Integer> unwind(
      int stack, int offset, int target, int secStack, ArrayList<Integer> y) {
    if (y.get(offset) <= stack)
      while (y.get(offset) <= stack) {
        if (y.get(offset) == stack) secStack++;
        offset++;
      }
    return new Tuple<Integer, Integer>(offset, secStack);
  }
}
