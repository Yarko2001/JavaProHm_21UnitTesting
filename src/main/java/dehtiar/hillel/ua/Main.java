package dehtiar.hillel.ua;

import dehtiar.hillel.ua.csv_options.ParserOption;
import dehtiar.hillel.ua.csv_options.ReaderOption;
import dehtiar.hillel.ua.csv_options.WriterOption;
import dehtiar.hillel.ua.csv_utils.UtilOptions;
import dehtiar.hillel.ua.entity.Supermarket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main class
 *
 * @author Yaroslav Dehtiar on 09.01.2023
 */
public class Main {

  private static final String ORDER_1 = "src/main/resources/order_csv_1";
  private static final String ORDER_2 = "src/main/resources/order_csv_2";
  private static final String ATB_RES = "src/main/resources/atb_resources.csv";
  private static final String SILPO_RES = "src/main/resources/silpo_resources.csv";
  private static final String TITLE = "НАИМЕНОВАНИЕ;ЦЕНА;ШТ;";

  public static void main(String[] args) {

    ParserOption parserOption = new ParserOption();
    ReaderOption readerOption = new ReaderOption();
    WriterOption writerOption = new WriterOption();

    List<Supermarket> supermarketList = new ArrayList<>();

    List<String> order1Lines = readerOption.read(ORDER_1);
    List<String[]> orderWords1 = parserOption.parse(order1Lines, ";", 1);
    supermarketList.addAll(UtilOptions.toSupermarketList(orderWords1));

    List<String> order2Lines = readerOption.read(ORDER_2);
    List<String[]> orderWords2 = parserOption.parse(order2Lines, ";", 1);
    supermarketList.addAll(UtilOptions.toSupermarketList(orderWords2));

    Map<String, List<Supermarket>> supermarketMap = UtilOptions.gropingByName(supermarketList);

    List<String> atbProductsLines = UtilOptions.mergeProduct(supermarketMap.get("АТБ"));
    atbProductsLines.add(0, TITLE);

    List<String> silpoProductsLines = UtilOptions.mergeProduct(supermarketMap.get("Сильпо"));
    silpoProductsLines.add(0, TITLE);

    writerOption.write(ATB_RES, atbProductsLines);
    writerOption.write(SILPO_RES, silpoProductsLines);
  }

}
