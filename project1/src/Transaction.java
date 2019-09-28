public class Transaction{


  static void checkOut(){
    String pattern = "DDMMYYYYHHMM";
    String appendString = "";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    String date = format.format(new Date());


    for (String i : orderList){
        appendString += date + ", " + i.split(",")[0].split() + "\n";
        System.out.println(appendString);
    }

  }

}
