import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.*;

class Main {
  /**
     * main: holds all input tests, puts each list of inputs into a 
     * separate arrayList
     */
  public static void main(String[] args) {
        ArrayList<String> input1 = new ArrayList<String>();
        input1.add("1 book at 12.49");
        input1.add("1 music CD at 14.99");
        input1.add("1 chocolate bar at 0.85");
        System.out.println("Input 1 Receipt: ");
        receiptList(input1);
        System.out.println();
        
        ArrayList<String> input2 = new ArrayList<String>();
        input2.add("1 imported box of chocolates at 10.00");
        input2.add("1 imported bottle of perfume at 47.50");
        System.out.println("Input 2 Receipt: ");
        receiptList(input2);
        System.out.println();
        
        ArrayList<String> input3 = new ArrayList<String>();
        input3.add("1 imported bottle of perfume at 27.99");
        input3.add("1 bottle of perfume at 18.99");
        input3.add("1 packet of headache pills at 9.75");
        input3.add("1 imported box of chocolates at 11.25");
        System.out.println("Input 3 Receipt: ");
        receiptList(input3);
        System.out.println();
    }
    

    /**
     * receiptList: prints receipt of a given arrayList of items
     * uses Items class
     */
    public static void receiptList(ArrayList<String> items)
    {
        // put your code here
        int size = items.size();
        String item;
        BigDecimal price;
        BigDecimal totalTax = new BigDecimal(0.0);
        BigDecimal totalPrice = new BigDecimal(0.0);
        ArrayList<Items> item_objs = new ArrayList<Items>();
        
        for(int i = 0; i < size; i++) {
            int of_index = items.get(i).indexOf(" at ");
            item = items.get(i).substring(0,of_index);
            price = new BigDecimal(items.get(i).substring(of_index + 4));
            item_objs.add(new Items(item,price));
            item_objs.get(i).setItemType();
            item_objs.get(i).setTax();
        }
        
        for(int i = 0; i < size; i++) {
            totalTax = totalTax.add(item_objs.get(i).getTax());
            System.out.println(item_objs.get(i).getName() + ": " + (item_objs.get(i).getPrice().add(item_objs.get(i).getTax())));
            totalPrice = totalPrice.add(item_objs.get(i).getPrice());
        }
        totalTax = totalTax.setScale(2, RoundingMode.CEILING);
        System.out.println("Sales tax: " + totalTax);
        System.out.println("Total: " + (totalTax.add(totalPrice)));
    }
}