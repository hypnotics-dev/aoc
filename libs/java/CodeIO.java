import java.io.File;

/** CodeIO */
public class CodeIO {

  public static File getInputs(int year, String file) {
    return new File("/home/hypnotics/dev/proj/aoc/" + year + "/inputs/" + file);
  }
}
