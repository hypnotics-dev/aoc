import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/** D02 */
public class D02 {

  public static void main(String[] args) {

    System.out.println("Star One Test: " + starOne(CodeIO.getInputs(2024, "D02s.txt")));
    System.out.println("Star One Main: " + starOne(CodeIO.getInputs(2024, "D02.txt")));
    System.out.println("Star Two Test: " + starTwo(CodeIO.getInputs(2024, "D02s.txt")));
    System.out.println("Star Two Main: " + starTwo(CodeIO.getInputs(2024, "D02.txt")));
  }

  private static List<Integer> reader(String line) {
    return Arrays.stream(line.split(" "))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .map(Integer::valueOf)
        .collect(Collectors.toList());
  }

  private static int starOne(File file) {
    int safe = 0;
    try {
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        // First time using functional programming in java, it's fun so far
        safe += safeChainV1(reader(scanner.nextLine())) ? 1 : 0;
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
    return safe;
  }

  private static int starTwo(File file) {
    int safe = 0;
    try {
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        safe += safeChainV2(reader(scanner.nextLine())) ? 1 : 0;
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
    return safe;
  }

  private static boolean safeChainV1(List<Integer> list) {
    int sec = list.get(1);
    if (list.getFirst() == sec) return false;

    boolean asc = !(list.getFirst() > sec);

    Iterator<Integer> iter = list.iterator();
    int cache = list.getFirst();
    iter.next();
    while (iter.hasNext()) {
      int i = iter.next();
      if (i == cache) return false;
      if (i < cache && asc) return false;
      if (i > cache && !asc) return false;
      if (i - cache > 3 || i - cache < -3) return false;
      cache = i;
    }
    return true;
  }

  private static boolean safeChainV2(List<Integer> list) {
    if (safeChainV1(list)) return true;
    else {
      for (int i = 0; i < list.size(); i++) {
        if (safeChainV1(remove(list, i))) return true;
      }
      return false;
    }
  }

  private static List<Integer> remove(List<Integer> list, int remove) {
    List<Integer> ret = new ArrayList<>(list);
    ret.remove(remove);
    return ret;
  }
}
