import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/*
I am using the balance manager class as an easier way to deal with the locking of the balance 
variable across threads. Since both the deposit and withdraw objects share...
the same balancemanger object you can trivially lock/unlock it across threads.
*/
public class BalanceManager {

  Integer balance = 0;
  private final Lock lock = new ReentrantLock();
  Condition insufficientFunds = lock.newCondition();

  void deposit(Integer value, String threadName) {
    String threadDeposit, newDeposit;

    lock.lock();

    /*
     * This is the industry standard way to handle overflows for transactions. C = A
     * + B If C > A --> overflow This is just a clever way to determine if the value
     * overflowed, since you cannot tell otherwise. If you can overflow the balance,
     * you could add or remove an arbitary amount from balance.
     */
    newDeposit = String.format("Balance is $%d", balance);
    threadDeposit = String.format("Thread %s deposits  $%d", threadName, value);

    Integer safeMoney = value + balance;
    assert (safeMoney > balance) : "Overflow on deposit.";

    balance += value;
    System.out.printf("%-60s %s \n", threadDeposit, newDeposit);

    insufficientFunds.signalAll();
    lock.unlock();
  }


  void withdraw(Integer value, String threadName) {
    String threadWithdraw, newBalance;

    lock.lock();

    // If balance is greater than or equal to value, you can withdraw.
    threadWithdraw = String.format("                          Thread %s withdraws $%d", threadName, value);
    if (balance >= value) {
      balance -= value;
      newBalance = String.format("Balance is $%d", balance);
      System.out.printf("%-60s %s\n", threadWithdraw, newBalance);
      lock.unlock();
    }

    else {
      // The only possible case is value is greater than balance.
      System.out.printf("%s %s\n", threadWithdraw,"- Blocked - Insufficient funds");

      //Wait for deposit thread to run.
      try {
        insufficientFunds.await();
      } 
      catch (InterruptedException e) {
        e.printStackTrace();
      }

      lock.unlock();
    }

 
  }
}
