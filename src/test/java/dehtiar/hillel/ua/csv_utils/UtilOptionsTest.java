package dehtiar.hillel.ua.csv_utils;

import static org.junit.jupiter.api.Assertions.*;

import dehtiar.hillel.ua.csv_options.ParserOption;
import dehtiar.hillel.ua.csv_options.ReaderOption;
import dehtiar.hillel.ua.entity.Supermarket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author Yaroslav Dehtiar on 09.01.2023
 */
class UtilOptionsTest {

  private final String ORDER = "/path/to/file.csv";
  ReaderOption readerOption = Mockito.mock(ReaderOption.class);
  ParserOption parserOption = Mockito.mock(ParserOption.class);

  @Test
  void testToObjectList() {
    List<String[]> testList = Arrays.asList(new String[]{"АТБ", "Гречка", "31.25", "120"},
        new String[]{"Сильпо", "Сахар", "21.40", "107"});

    Mockito.when(parserOption.parse(readerOption.read(ORDER), ";", 0)).thenReturn(testList);
    List<String[]> orderWords = parserOption.parse(readerOption.read(ORDER), ";", 0);
    List<Supermarket> supermarkets = UtilOptions.toSupermarketList(orderWords);

    assertEquals("АТБ", supermarkets.get(0).getName());
    assertEquals("Гречка", supermarkets.get(0).getProductName());
    assertEquals(31.25, supermarkets.get(0).getPrice());
    assertEquals(120, supermarkets.get(0).getQuantity());

    assertEquals("Сильпо", supermarkets.get(1).getName());
    assertEquals("Сахар", supermarkets.get(1).getProductName());
    assertEquals(21.40, supermarkets.get(1).getPrice());
    assertEquals(107, supermarkets.get(1).getQuantity());
  }

  @Test
  void testToObjectListIsNull() {
    List<Supermarket> supermarkets = UtilOptions.toSupermarketList(null);

    assertNull(supermarkets.get(0).getName());
    assertNull(supermarkets.get(0).getProductName());
    assertEquals(0, supermarkets.get(0).getPrice());
    assertEquals(0, supermarkets.get(0).getQuantity());
  }

  @Test
  void testToObjectListMatchesNumFields() {
    List<String[]> testList = new ArrayList<>();
    testList.add(new String[]{"АТБ"});

    Mockito.when(parserOption.parse(readerOption.read(ORDER), ";", 0)).thenReturn(testList);
    List<String[]> orderWords = parserOption.parse(readerOption.read(ORDER), ";", 0);

    assertThrows(IllegalArgumentException.class, () -> UtilOptions.toSupermarketList(orderWords));
  }

  @Test
  void testGroupingByName() {
    List<Supermarket> supermarkets = Arrays.asList(new Supermarket("АТБ", "Гречка", 31.25, 120),
        new Supermarket("Сильпо", "Сахар", 21.40, 107));

    Map<String, List<Supermarket>> supermarketMap = UtilOptions.gropingByName(supermarkets);

    assertEquals("АТБ", supermarketMap.get("АТБ").get(0).getName());
    assertEquals("Гречка", supermarketMap.get("АТБ").get(0).getProductName());

    assertEquals("Сильпо", supermarketMap.get("Сильпо").get(0).getName());
    assertEquals("Сахар", supermarketMap.get("Сильпо").get(0).getProductName());
  }

  @Test
  void testGroupingByNameIsNull() {
    assertNotNull(UtilOptions.gropingByName(null));
  }

  @Test
  void testMergeProduct() {
    List<Supermarket> supermarkets = Arrays.asList(new Supermarket("АТБ", "Гречка", 100.0, 10),
        new Supermarket("АТБ", "Гречка", 80.5, 5));

    List<String> mergedProducts = UtilOptions.mergeProduct(supermarkets);

    assertEquals("Гречка;140.25;15;", mergedProducts.get(0));
  }

  @Test
  void testMergeProductIsNull() {
    assertNotNull(UtilOptions.mergeProduct(null));
  }

}