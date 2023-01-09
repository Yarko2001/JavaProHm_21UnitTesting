package dehtiar.hillel.ua.csv_options;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

/**
 * {@link ParserOption} is a class which was designed for the parsing .csv files.
 *
 * @author Yaroslav Dehtiar on 08.01.2023
 */
@NoArgsConstructor
public class ParserOption {

  /**
   * Parsing all lines from a csv file and return list words {@code List<String[]>}.
   *
   * @param lines        lines of the text  {@code List<String>}
   * @param separator    the separator which means: char or text {@code String}
   * @param skippedLines amount of skipped lines
   * @return a list which is divided into the array of the words {@code List<String[]>}
   */
  public List<String[]> parse(List<String> lines, String separator, int skippedLines) {
    if (lines == null || separator == null) {
      List<String[]> list = new ArrayList<>();
      list.add(new String[0]);
      return list;
    }
    return lines.stream()
        .skip(skippedLines)
        .map(a -> a.split(separator))
        .collect(Collectors.toList());
  }

}
