import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;




public class Item{

public static String bookID;
public static String bookName;

public static Double itemPrice;
public static Double totalPrice;

public static Double quantity;
public static Double discount = 1.0;

public static String discountString = "";

public static String itemInfo = "";


public void createItem(String id,String name, Double price, Double quant){


  bookID = id;
  bookName = name;
  itemPrice = price;
  quantity = quant;


  if (quant < 4 && quant >= 1){
    discountString = "0%";
    discount = 1.0;
  }
  else if(quant > 5 && quant < 9){
    discountString = "10%";
    discount = .9;
  }
  else if(quant > 10 && quant < 14){
    discountString = "15%";
    discount = .85;
  }
  else
  {
    discountString = "20%";
    discount = .80;
  }

  totalPrice = (itemPrice * quantity * discount);

}

public String itemInfo(){

  itemInfo = bookID + " " + bookName + " $" + String.format("%.2f", itemPrice) +
    " " + quantity + " " + discountString + " $" + String.format("%.2f", totalPrice);

    return itemInfo;

}

public double getTotal(){

  return totalPrice;
}



}
