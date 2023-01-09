package dehtiar.hillel.ua.csv_options;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * {@link ReaderOption} is a class which was designed  for the reading .csv files.
 *
 * @author Yaroslav Dehtiar on 08.01.2023
 */
public class ReaderOption {

  /**
   * Read all lines from a csv file and return {@code List<String>}.
   *
   * @param path is the path to the file
   * @return the lines from the file as a {@code List<String>}
   */

  public List<String> read(String path){

    try {
      return Files.readAllLines(Paths.get(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
