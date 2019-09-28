import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;

import java.lang.*;

public class BalanceManager {

  Integer balance = 0;
  private final Lock lock = new ReentrantLock();

  void deposit(Integer value, Integer sleepTime, String threadName) throws InterruptedException {
    while (true) {
      lock.lock();
      try {
        Integer safeMoney = value + balance;
        // If you insert more money than is possible for max double, it would cause an
        // overflow.
        assert (safeMoney > balance) : "Overflow on deposit.";

        balance += value;
            String.format("Thread %s deposits $%d                  Balance is $%d", threadName, value, balance));
        currentThread().sleep(sleepTime);
    }
    finally{
      lock.unlock();
    }
    }


  }


  void withdraw(Integer value,String threadName){
    while(true){
    lock.lock();

    try{
      //If balance is greater than  or equal to value, you can withdraw.
      if(balance >= value){
        balance -= value;
        System.out.println(String.format("Thread %s withdraws $%d                 Balance is $%d",threadName, value, balance));
      }

      else{
      // The only possible case is value is greater than balance.
        System.out.println("Insufficient funds");
      }

    }finally{
      lock.unlock();

    }

    }
  }

}
