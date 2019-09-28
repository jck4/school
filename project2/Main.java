import java.util.concurrent.*;

public class Main{

  public static void main(String args[]){
  BalanceManager  myManager = new BalanceManager();
  ExecutorService executor1 = Executors.newCachedThreadPool();





      for ( int i=0; i < 4; i++){
        executor1.execute(new Deposit(myManager,String.format("D%d",i)));
      }

      ExecutorService executor2 = Executors.newCachedThreadPool();


      for ( int i=0; i < 8; i++){
        executor2.execute(new Withdraw(myManager,String.format("W%d",i)));
      }

    


  // executor1.shutdown();
  // executor2.shutdown();

  }

}
