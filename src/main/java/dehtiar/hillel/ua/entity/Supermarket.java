package dehtiar.hillel.ua.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link Supermarket} is a supermarket data class.
 *
 * @author Yaroslav Dehtiar on 08.01.2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supermarket {

  private String name;
  private String productName;
  private double price;
  private int quantity;

  public static Supermarket averagePriceAndSumQuantity(Supermarket firstSm, Supermarket secondSm) {
    firstSm.setPrice((firstSm.getPrice()) + secondSm.getPrice() / 2);
    firstSm.setQuantity((firstSm.getQuantity()) + secondSm.getQuantity());
    return firstSm;
  }

  @Override
  public String toString() {
    return productName + ";" + price + ";" + quantity + ";";
  }

}
