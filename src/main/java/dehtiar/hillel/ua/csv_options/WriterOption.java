package dehtiar.hillel.ua.csv_options;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * {@link WriterOption} is a class for create .csv files.
 *
 * @author Yaroslav Dehtiar on 09.01.2023
 */
public class WriterOption {

  /**
   * Write lines of text to a csv file.
   *
   * @param path is the path to the file
   * @param lines are the text lines {@code List<String>}
   */

  public void write(String path, List<String> lines){
    try {
      Files.write(Paths.get(path),lines);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
