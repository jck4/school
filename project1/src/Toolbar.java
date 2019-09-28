import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;



public class Toolbar extends JPanel  {

  public JButton processItem;
  public JButton confirmItem;
  public JButton viewOrder;
  public JButton finishOrder;
  public JButton newOrder;
  public JButton exit;


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

    setLayout(new FlowLayout (FlowLayout.LEFT));

    ActionHandler handler = new ActionHandler();
    processItem.addActionListener(handler);

    //Adding buttons to panel.
    add(processItem);
    // add(confirmItem);
    // add(viewOrder);
    // add(finishOrder);
    // add(newOrder);
    // add(exit);


    //Process item to add to order.

    //
    // confirmItem.addActionListener(new ActionListener()
    // {
    //   public void actionPerformed(ActionEvent e)
    //   {
    //
    //   text.subtotalLabel.setText("Order subtotal for " + numItems + " item(s)");
    //   text.subtotal.setText(String.valueOf(getTotal()));
    //   numItems++;
    //   confirmItem.setEnabled(false);
    //   processItem.setEnabled(true);
    //   viewOrder.setEnabled(true);
    //   finishOrder.setEnabled(true);
    //   processItem.setText("Process item #" + numItems);
    //   confirmItem.setText("Confirm item #" + numItems);
    //   text.bookIDLabel.setText("Enter Book ID for item #" + numItems);
    //   text.itemQuanityLabel.setText("Enter quantity for item #" + numItems);
    //   text.itemInfoLabel.setText("Item #" + numItems + " info");
    //   text.bookID.setText("");
    //   text.itemQuanity.setText("");
    //
    //   //Checking if the number of items in the order has been hit
    //   if(numItems > itemsAllowed){
    //     processItem.setEnabled(false);
    //     processItem.setText("Process Item");
    //     confirmItem.setText("Confirm Item");
    //   }
    //
    //   JOptionPane.showMessageDialog(null, "Item #" + (numItems - 1) + " accepted");
    //
    //
    //
    // }
    // });
    //
    // newOrder.addActionListener(new ActionListener()
    // {
    //   public void actionPerformed(ActionEvent e)
    //   {
    //     itemList = new ArrayList<Item>();
    //     totalPrice = 0;
    //     numItems = 1;
    //     newText = "";
    //     total = 0.0;
    //     processItem.setText("Process Item #1");
    //     processItem.setEnabled(true);
    //     confirmItem.setEnabled(false);
    //     finishOrder.setEnabled(false);
    //     viewOrder.setEnabled(false);
    //     confirmItem.setText("Confirm Item #1");
    //     text.itemOrderLabel.setText("Enter number of items in this order: ");
    //     text.itemInfo.setText("");
    //     text.itemQuanity.setText("");
    //     text.bookID.setText("");
    //     text.itemOrder.setText("");
    //     text.subtotal.setText("");
    //     text.bookIDLabel.setText("Enter Book ID for Item #1:");
    //     text.itemQuanityLabel.setText("Enter quantity for Item #1:");
    //     text.itemInfoLabel.setText("Item #1 info:");
    //     text.subtotalLabel.setText("Order subtotal for " + (numItems - 1)  + " item(s):");
    //   }
    // });
    //
    // finishOrder.addActionListener(new ActionListener()
    // {
    //   public void actionPerformed(ActionEvent e)
    //   {
    //     // order.checkOut();
    //     // order.appendOutput();
    //   }
    // });
    //
    // viewOrder.addActionListener(new ActionListener()
    // {
    //   public void actionPerformed(ActionEvent e)
    //   {
    //     Integer num = 1;
    //     String orderString = "";
    //     for(Item item : itemList){
    //       orderString += num + ". " + item.itemInfo() + "\n";
    //       num++;
    //     }
    //     JOptionPane.showMessageDialog(null, orderString);
    //   }
    // });
    //
    // exit.addActionListener(new ActionListener()
    // {
    //   public void actionPerformed(ActionEvent e)
    //   {
    //     System.exit(0);
    //   }
    // });


  // }



}
}


class ActionHandler implements ActionListener {
public void actionPerformed(ActionEvent event){

    double totalPrice = 0;
    Integer numItems = 1;

    ArrayList<String> check;
    ArrayList<String> inventory = new ArrayList<String>();
    ArrayList<String> checkInventory;


    Integer itemsAllowed = 0;
    String newText = "";

    //Array list of item objects that have been inserted into the order.
    List<Item> itemList = new ArrayList<Item>();
     Double total = 0.0;



      if (event.getSource()==processItem)
           {
             String x,y,itemQuant;
             String[] z;
             double discount;
             Boolean isInInventory = false;
             String dis;
             totalPrice = 0;
             Item item = new Item();

             itemsAllowed = Integer.valueOf(text.itemOrder().getText());
             x = text.bookID().getText();

             checkInventory = getInventory();


             if(!x.equals(""))
               for (String i : checkInventory) {
                 //y is the ID for comparison in the inventory
                 y = i.split(",")[0];
                 // z

                 //Check if The book id is equal to the ID in the inventory file.
                 if (Integer.parseInt(x) == Integer.parseInt(y)){

                   z = i.split(",");

                   itemQuant = text.itemQuanity().getText();


                   item.createItem(z[0], z[1], Double.parseDouble(z[2]), Double.parseDouble(itemQuant));
                   newText = item.itemInfo();

                   itemList.add(item);

                   text.itemInfo().setText(newText);
                   isInInventory = true;
                   processItem.setEnabled(false);
                   confirmItem.setEnabled(true);
                   }
               }

               if(!isInInventory)
               JOptionPane.showMessageDialog(null, "Book ID: " + x + " Is not on file");

           }
       // else if (event.getSource()==z)
       //     {
       //        //do something
       //     }
       // else if (event.getSource()==a)
       //     {
       //        //do something
       //     }
       // else if (event.getSource()==b)
       //     {
       //        //do something
       //     }
       // else if (event.getSource()==c)
       //     {
       //        //do something
       //     }

}
}


class TextPanel extends JPanel {


  public JTextField itemOrder;
  public JLabel itemOrderLabel;

  public JTextField bookID;
  public JLabel bookIDLabel;

  public JTextField itemQuanity;
  public JLabel itemQuanityLabel;

  public JTextField itemInfo;
  public JLabel itemInfoLabel;

  public JTextField subtotal;
  public JLabel subtotalLabel;




  public TextPanel(){

    itemOrder = new JTextField();
    itemOrderLabel = new JLabel("Enter number of items in this order: ");

    bookID = new JTextField();
    bookIDLabel = new JLabel("Enter Book ID for Item #1:");

    itemQuanity = new JTextField();
    itemQuanityLabel = new JLabel("Enter quantity for Item #1:");

    itemInfo = new JTextField();
    itemInfoLabel = new JLabel("Item #1 info:");

    subtotal = new JTextField();
    subtotalLabel = new JLabel("Order subtotal for 0 item(s):");


    setLayout(new GridLayout(5,2));

    add(itemOrderLabel);
    add(itemOrder);

    add(bookIDLabel);
    add(bookID);

    add(itemQuanityLabel);
    add(itemQuanity);

    add(itemInfoLabel);
    add(itemInfo);

    add(subtotalLabel);
    add(subtotal);
  }

  public JTextField itemOrder(){
    return itemOrder;
  }
  public JLabel itemOrderLabel(){
    return itemOrderLabel;
  }

  public JTextField bookID(){
    return bookID;
  }
  public JLabel bookIDLabel(){
    return bookIDLabel;
  }

  public JTextField itemQuanity(){
    return itemQuanity;
  }
  public JLabel itemQuanityLabel(){
    return itemQuanityLabel;
  }

  public JTextField itemInfo(){
    return itemInfo;
  }
  public JLabel itemInfoLabel(){
    return itemInfoLabel;
  }

  public JTextField subtotal(){
    return subtotal;
  }
  public JLabel subtotalLabel(){
    return subtotalLabel;
  }

}
