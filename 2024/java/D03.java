import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class D03 {

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Star One Test: " + starOne(CodeIO.getInputs(2024, "D03s.txt")));
    System.out.println("Star One Main: " + starOne(CodeIO.getInputs(2024, "D03.txt")));
    System.out.println("Star Two Test: " + starTwo(CodeIO.getInputs(2024, "D03s2.txt")));
    System.out.println("Star Two Main: " + starTwo(CodeIO.getInputs(2024, "D03.txt")));
  }

  public static int starOne(File file) throws FileNotFoundException {
    LinkedList<String> list =
        getData(Pattern.compile("mul\\([1-9]\\d?\\d?,[1-9]\\d?\\d?\\)"), file);
    int total = 0;
    for (String string : list) total += mul(string);
    return total;
  }

  public static int starTwo(File file) throws FileNotFoundException {
    LinkedList<String> list =
        getData(
            Pattern.compile("(mul\\([1-9]\\d?\\d?,[1-9]\\d?\\d?\\))|(do\\(\\))|(don't\\(\\))"),
            file);
    int total = 0;
    Iterator<String> iter = list.iterator();
    boolean dont = false;
    while (iter.hasNext()) {
      String node = iter.next();
      // System.out.println(node);
      if (node.equals("do()")) dont = false;
      else if (node.equals("don't()")) dont = true;
      else if (!dont) total += mul(node);
    }
    return total;
  }

  private static LinkedList<String> getData(Pattern pattern, File file)
      throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    String real = "";
    LinkedList<String> stack = new LinkedList<>();
    while (scanner.hasNextLine()) {
      while (real != null) {
        real = scanner.findInLine(pattern);
        if (real == null) continue;
        else stack.add(real);
      }
      scanner.nextLine();
      real = "";
    }
    scanner.close();
    return stack;
  }

  private static int mul(String mul) {
    // System.out.println(mul);
    String[] ints = mul.substring(4, mul.length() - 1).split(",");
    // System.out.println("ints 0: " + ints[0] + " ints 1: " + ints[1]);
    return Integer.parseInt(ints[0]) * Integer.parseInt(ints[1]);
  }
}
