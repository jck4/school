import javax.swing.JFrame;
import javax.swing.*;

public class Bookshop {


  public static void main(String[] args){


    SwingUtilities.invokeLater(new Runnable() {
     public void run() {
         new MainFrame();
      }
    });

  }
}
