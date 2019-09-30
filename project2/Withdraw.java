import java.util.Random;

public class Withdraw implements Runnable {
  BalanceManager myManager;
  String threadName;

  public Withdraw(BalanceManager a, String name) {
    this.myManager = a;
    this.threadName = name;
  }

  @Override
  public void run() {
    Integer randValue;
    Integer randSleep;
    Random rand = new Random();
    while (true) {
      /*
      randoms values for sleep time and withdraw.
      After a successful withdraw, the thread will be put to sleep for random interval.
      */
      randValue = rand.nextInt(50);
      randSleep = rand.nextInt(5);
      myManager.withdraw(randValue, threadName);
      try {
        Thread.sleep(randSleep);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
