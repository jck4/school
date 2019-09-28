import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.event.*;


public class MainFrame extends JFrame{


  private TextPanel textPanel;
  private Toolbar Toolbar;

  public MainFrame(){

    super("Ye Olde Book Shoppe");


    textPanel = new TextPanel();
    Toolbar = new Toolbar(textPanel);


    setLayout(new BorderLayout());


    add(Toolbar,BorderLayout.SOUTH);
    add(textPanel);

    setSize(750,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

}
