import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.*;


public class TextPanel extends JPanel {


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
