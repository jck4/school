import java.util.Random;

public class Deposit implements Runnable {

  BalanceManager myManager;
  String threadName;

  public Deposit(BalanceManager a, String name){
    myManager = a;
    threadName = name;
  }
  public void run(){
    Integer randTime;
    Integer randValue;
    Random rand = new Random();
    randValue = rand.nextInt(50);
    randTime = rand.nextInt(250);
    myManager.deposit(randTime,randValue,threadName);
  }
}
