import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.*;



public class Toolbar extends JPanel  {

  public JButton processItem;
  public JButton confirmItem;
  public JButton viewOrder;
  public JButton finishOrder;
  public JButton newOrder;
  public JButton exit;

  public double totalPrice = 0;
  public Integer numItems = 1;

  public ArrayList<String> check;
  public ArrayList<String> inventory = new ArrayList<String>();
  public ArrayList<String> checkInventory;

  public Order order = new Order();
  public String newText;

  public ArrayList<String> getInventory()
     {

       String temp = "";
       // pass the path to the file as a parameter
       try{
         File file = new File("inventory.txt");
         Scanner scanner = new Scanner(file);

         while (scanner.hasNext()){
           temp = scanner.nextLine();
           inventory.add(temp);
         }
       }

       catch (FileNotFoundException e) {
         e.printStackTrace();
         System.out.println("Couldn't read file.");
      }
      return inventory;
    }


  public Toolbar(TextPanel textPanel){
    TextPanel text = textPanel;

    processItem = new JButton("Process Item #1");

    //Creating all the buttons for toolbar
    confirmItem = new JButton("Confirm Item #1");
    confirmItem.setEnabled(false);
    viewOrder = new JButton("View Order");
    viewOrder.setEnabled(false);
    finishOrder = new JButton("Finish Order");
    finishOrder.setEnabled(false);
    newOrder = new JButton("New Order");
    exit = new JButton("Exit");



    //Process item to add to order.
    processItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {


        String x,y,itemQuant;
        String[] z;
        double discount;
        Boolean isInInventory = false;
        String dis;
        totalPrice = 0;

        x = text.bookID().getText();

        checkInventory = getInventory();

        if(!x.equals(""))
          for (String i : checkInventory) {
            y = i.split(",")[0];
            z = i.split(",");
            //Check if The book id is equal to the ID in the inventory file.
            if (Integer.parseInt(x) == Integer.parseInt(y)){
              itemQuant = text.itemQuanity().getText();

              // Discount chart.
              if (Integer.parseInt(itemQuant) < 4 && Integer.parseInt(itemQuant) >= 1){
                dis = "0%";
                discount = 1;
              }
              else if(Integer.parseInt(itemQuant) > 5 && Integer.parseInt(itemQuant) < 9){
                dis = "10%";
                discount = .9;
              }
              else if(Integer.parseInt(itemQuant) > 10 && Integer.parseInt(itemQuant) < 14){
                dis = "15%";
                discount = .85;
              }
              else
              {
                dis = "20%";
                discount = .80;
              }

              //Total price of the items.
              totalPrice = (Double.parseDouble(z[2]) * Double.parseDouble(itemQuant) * discount);

              newText = z[0] + " " + z[1] + " $" +
                z[2].replaceAll("\\s+", "") +
                " " + itemQuant + " " + dis + " $" + String.format("%.2f", totalPrice);



              text.itemInfo().setText(newText);
              isInInventory = true;
              processItem.setEnabled(false);
              confirmItem.setEnabled(true);

              }
          }

          order.setOrderTotal(Integer.parseInt(text.itemOrder.getText()));
          if(!isInInventory)
          JOptionPane.showMessageDialog(null, "Book ID: " + x + " Is not on file");

      }
    });

    confirmItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {

      order.addTotal(totalPrice);
      text.subtotalLabel.setText("Order subtotal for " + numItems + " item(s)");
      text.subtotal.setText(String.valueOf(order.getTotal()));
      order.addToOrder(newText);
      numItems++;
      confirmItem.setEnabled(false);
      processItem.setEnabled(true);
      viewOrder.setEnabled(true);
      finishOrder.setEnabled(true);
      processItem.setText("Process item #" + numItems);
      confirmItem.setText("Confirm item #" + numItems);
      text.bookIDLabel.setText("Enter Book ID for item #" + numItems);
      text.itemQuanityLabel.setText("Enter quantity for item #" + numItems);
      text.itemInfoLabel.setText("Item #" + numItems + " info");
      text.bookID.setText("");
      text.itemQuanity.setText("");

      //Checking if the number of items in the order has been hit
      if(order.checkFull()){
        processItem.setEnabled(false);
        processItem.setText("Process Item");
        confirmItem.setText("Confirm Item");

      }

      JOptionPane.showMessageDialog(null, "Item #" + (numItems - 1) + " accepted");



    }
    });

    newOrder.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        totalPrice = 0;
        numItems = 1;
        newText = "";
        order.reset();
        processItem.setText("Process Item #1");
        processItem.setEnabled(true);
        confirmItem.setEnabled(false);
        finishOrder.setEnabled(false);
        viewOrder.setEnabled(false);
        confirmItem.setText("Confirm Item #1");
        text.itemOrderLabel.setText("Enter number of items in this order: ");
        text.itemInfo.setText("");
        text.itemQuanity.setText("");
        text.bookID.setText("");
        text.itemOrder.setText("");
        text.subtotal.setText("");
        text.bookIDLabel.setText("Enter Book ID for Item #1:");
        text.itemQuanityLabel.setText("Enter quantity for Item #1:");
        text.itemInfoLabel.setText("Item #1 info:");
        text.subtotalLabel.setText("Order subtotal for " + (numItems - 1)  + " item(s):");
      }
    });

    finishOrder.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        order.checkOut();
        order.appendOutput(checkInventory);
      }
    });

    viewOrder.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        order.printOrder();
      }
    });

    exit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
    });

    setLayout(new FlowLayout (FlowLayout.LEFT));

    //Adding buttons to panel.
    add(processItem);
    add(confirmItem);
    add(viewOrder);
    add(finishOrder);
    add(newOrder);
    add(exit);
  }



}
