package dehtiar.hillel.ua.csv_utils;

import dehtiar.hillel.ua.entity.Supermarket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

/**
 * {@link UtilOptions} is a class which was designed for transformation different types of data.
 *
 * @author Yaroslav Dehtiar on 09.01.2023
 */
@NoArgsConstructor
public class UtilOptions {

  /**
   * Converting the list  {@code List<String[]>} to List<Supermarket>.
   *
   * @param listWords is the list divided into an array of words {@code List<String[]>}
   * @return list objects {@code List<Supermarket>}
   * @throws IllegalArgumentException when array words size not match 4
   */


  public static List<Supermarket> toSupermarketList(List<String[]> listWords) {
    if (listWords == null) {
      return Collections.singletonList(new Supermarket());
    }
    return listWords.stream().map(d -> {
      if (d.length != 4) {
        throw new IllegalArgumentException(
            "The arrays word's length does not match the number of the fields in the object");
      }
      Supermarket supermarket = new Supermarket();
      supermarket.setName(d[0]);
      supermarket.setProductName(d[1]);
      supermarket.setPrice(Double.parseDouble(d[2]));
      supermarket.setQuantity(Integer.parseInt(d[3]));
      return supermarket;
    }).collect(Collectors.toList());
  }

  /**
   * Grouping {@code List<Supermarket>} by supermarket's name.
   *
   * @param supermarkets list objects {@code List<Supermarket>}
   * @return Map supermarkets {@code Map<String, List<Supermarket>>}
   */
  public static Map<String, List<Supermarket>> gropingByName(List<Supermarket> supermarkets) {
    if (supermarkets == null) {
      return new HashMap<>();
    }
    return supermarkets.stream()
        .collect(Collectors.groupingBy(Supermarket::getName));
  }

  /**
   * Method for collecting by product name, merging products, calculating the average price, total
   * sum of the quantity and convert to string.
   *
   * @param supermarkets list objects {@code List<Supermarket>}
   * @return merged and calculated product list {@code List<String>}
   */
  public static List<String> mergeProduct(List<Supermarket> supermarkets) {
    if (supermarkets == null) {
      return new ArrayList<>();
    }
    return supermarkets.stream()
        .collect(Collectors.toMap(Supermarket::getProductName, Function.identity(),
            Supermarket::averagePriceAndSumQuantity))
        .values().stream()
        .map(Supermarket::toString)
        .collect(Collectors.toList());
  }

}
