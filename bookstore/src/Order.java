import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;


public class Order{

  static Integer itemsAllowed = 0;
  static Integer itemsInOrder = 0;
  static Double subTotal = 0.0;
  static Double orderTotal = 0.0;
  static Double tax = 6.0;
  static Double taxAmount;




  public static ArrayList<String> bookIDList = new ArrayList<String>();




  public static ArrayList<String> orderList = new ArrayList<String>();



  public void addToOrder(String item){
    orderList.add(item);
    itemsInOrder += 1;
  }

  static void printOrder(){
  Integer num = 1;
  String orderString = "";
  for (String i : orderList){
    orderString += num + ". " + i + "\n";
    num++;
  }
  JOptionPane.showMessageDialog(null, orderString);
  }


  public Double getTotal(){
    return subTotal;
  }

  public Integer getItems(){
    return itemsInOrder;
  }

  static void addTotal(Double total){
    subTotal = subTotal + total;
  }

  static void incrementItems(){
    itemsInOrder++;
  }

  static void setOrderTotal(Integer total){
  itemsAllowed = total;
  }

  static void addToTransaction(String a, String b, String c){
  bookIDList.add(a);

  }

  static Boolean checkFull(){
    if (itemsAllowed == itemsInOrder)
      return true;
    else
      return false;
  }

  static void checkOut(){
    String pattern = "d/M/y, hh:mm:ss a";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    String date = format.format(new Date());


    orderTotal = (tax * subTotal);

    Integer num = 1;
    String checkoutString = "";

    checkoutString += "Date: " + date + ("\n\n");
    checkoutString += "Number of line items: " + itemsAllowed + ("\n\n");
    for (String i : orderList){
      checkoutString += num + ". " + i + "\n";
    }
    checkoutString += ("\n\n") + "Order subtotal: $" + String.format("%.2f", subTotal) +  ("\n\n");

    checkoutString += "Tax Rate:\t6%" + ("\n\n");

    checkoutString += "Tax Amount: \t$" + String.format("%.2f",(subTotal * .06)) + ("\n\n");

    checkoutString += "Order Total: \t" + String.format("%.2f",(subTotal * 1.06))+ ("\n\n");

    checkoutString += "Thanks for shopping at the Ye Olde Book Shoppe!";

    JOptionPane.showMessageDialog(null, checkoutString);

  }

  static void reset(){
    itemsAllowed = 0;
    itemsInOrder = 0;
    subTotal = 0.0;
    orderTotal = 0.0;
    orderList = new ArrayList<String>();
  }

  static void appendOutput(ArrayList<String> check){
    String pattern = "DDMMYYYYHHMM";
    String appendString = "";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    String date = format.format(new Date());


    for (String i : check){
        appendString += date + ", " + i.split(",")[0] + "\n";
        System.out.println(appendString);
    }



  }

}
