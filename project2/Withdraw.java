import java.util.Random;

public class Withdraw implements Runnable {
  BalanceManager myManager;
  String threadName;

  public Withdraw(BalanceManager a, String name){
    this.myManager = a;
    this.threadName = name;
  }

  @Override
  public void run(){
    Integer randValue;
    Random rand = new Random();
    randValue = rand.nextInt(50);
    myManager.withdraw(randValue,threadName);
  }
}
