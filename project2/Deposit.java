import java.util.Random;

public class Deposit implements Runnable {

  BalanceManager myManager;
  String threadName;

  public Deposit(BalanceManager a, String name) {
    myManager = a;
    threadName = name;
  }

  @Override
  public void run() {
    Integer randValue;
    Integer randSleep;
    Random rand = new Random();
    while (true) {
      randValue = rand.nextInt(250);
      randSleep = rand.nextInt(30);
      myManager.deposit(randValue, threadName);
      // Put to sleep for random.
      try {
        Thread.sleep(randSleep);
      } 
      catch (InterruptedException e) {
        e.printStackTrace();
      }

      
    }
  }
}
