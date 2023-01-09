package dehtiar.hillel.ua.csv_options;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * @author Yaroslav Dehtiar on 09.01.2023
 */
class ParserOptionTest {

  private final String ORDER = "/path/to/file.csv";
  ReaderOption readerOption = Mockito.mock(ReaderOption.class);
  private final ParserOption parserOption = new ParserOption();

  @Test
  void testCsvLines() {
    List<String> lines = Arrays.asList("a;", "a;b;", "a;b;c;", "a;b;c;d;");

    Mockito.when(readerOption.read(ORDER)).thenReturn(lines);
    List<String> orderLines = readerOption.read(ORDER);
    List<String[]> result = parserOption.parse(orderLines, ";", 0);

    assertArrayEquals(new String[]{"a"}, result.get(0));
    assertArrayEquals(new String[]{"a", "b"}, result.get(1));
    assertArrayEquals(new String[]{"a", "b", "c"}, result.get(2));
    assertArrayEquals(new String[]{"a", "b", "c", "d"}, result.get(3));
  }

  @Test
  void testLinesAreEmpty() {
    List<String> lines = Arrays.asList(";", " ;;", "", ";;;d;");

    Mockito.when(readerOption.read(ORDER)).thenReturn(lines);
    List<String> orderLines = readerOption.read(ORDER);
    List<String[]> result = parserOption.parse(orderLines, ";", 0);

    assertArrayEquals(new String[0], result.get(0));
    assertArrayEquals(new String[]{" "}, result.get(1));
    assertArrayEquals(new String[]{""}, result.get(2));
    assertArrayEquals(new String[]{"", "", "", "d"}, result.get(3));
  }

  @Test
  void testLinesAreNull() {
    List<String[]> result = parserOption.parse(null, ";", 0);

    assertArrayEquals(new String[0], result.get(0));
  }

  @Test
  void testParseWithAnotherSeparator() {
    List<String> lines = Arrays.asList("a,b,", " ,b,c");

    Mockito.when(readerOption.read(ORDER)).thenReturn(lines);
    List<String> orderLines = readerOption.read(ORDER);
    List<String[]> result = parserOption.parse(orderLines, ",", 0);

    assertArrayEquals(new String[]{"a", "b"}, result.get(0));
    assertArrayEquals(new String[]{" ", "b", "c"}, result.get(1));
  }

  @Test
  void testParseWithNullSeparator() {
    List<String> lines = Arrays.asList("a;b;", "c;");

    Mockito.when(readerOption.read(ORDER)).thenReturn(lines);
    List<String> orderLines = readerOption.read(ORDER);
    List<String[]> result = parserOption.parse(orderLines, null, 0);

    assertArrayEquals(new String[0], result.get(0));
  }

  @Test
  void testParseToScipSomeLines() {
    List<String> lines = Arrays.asList("a;", "a;b;", "a;b;c;", "a;b;c;d;");

    Mockito.when(readerOption.read(ORDER)).thenReturn(lines);
    List<String> orderLines = readerOption.read(ORDER);
    List<String[]> result = parserOption.parse(orderLines, ";", 1);

    assertArrayEquals(new String[]{"a", "b"}, result.get(0));
    assertArrayEquals(new String[]{"a", "b", "c"}, result.get(1));
    assertArrayEquals(new String[]{"a", "b", "c", "d"}, result.get(2));

    assertThrows(IllegalArgumentException.class, () -> parserOption.parse(lines, ";", -1));
  }

}