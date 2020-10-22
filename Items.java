import java.math.BigDecimal;
import java.math.*;

/**
 * Items class: stores an item's name, price, amount of sales tax applied to it,
 *            and the type of item.
 * author: Latisha Sumardy
 */
public class Items
{
    // instance variables
    private String item;
    private BigDecimal raw_price;
    private BigDecimal tax;
    private String itemType;

    /**
     * Constructor for objects of class Items
     */
    public Items(String name, BigDecimal price)
    {
        // initialise instance variables
        item = name;
        raw_price = price;
        tax = new BigDecimal(0.0);
        itemType = "";
    }
    
    public String getName() {
        return item;
    }
    
    public BigDecimal getPrice() {
        return raw_price;
    }
    
    public BigDecimal getTax() {
        return tax;
    }
    
    public String getItemType() {
        return itemType;
    }
    
    /**
     * setItemType: checks if item is a book, food, or medical product
     *
     * can set itemType to either "excempt" or "regular"
     */
    public void setItemType() {
        //arrays below contain words that may match if an item is an excempted from sales tax product
        String[] book = {"book", "books"};
        String[] medical = {"pills", "shots"};
        String[] food = {"chocolates", "chocolate"};
        boolean excemptFlag = false;
        
        for(String b : book) {
            if(item.indexOf(b) != -1) {
                itemType = "excempt";
                excemptFlag = true;
            }
        }
        
        for(String m : medical) {
            if(item.indexOf(m) != -1) {
                itemType = "excempt";
                excemptFlag = true;
            }
        }
        
        for(String f : food) {
            if(item.indexOf(f) != -1) {
                itemType = "excempt";
                excemptFlag = true;
            }
        }
        
        if(!excemptFlag) {
            itemType = "regular";
        }
    }
    
    /**
     * setTax: calculates the sales tax of an item
     *
     */
    public void setTax()
    {
        tax = new BigDecimal(0.00);
        double priceDouble = raw_price.doubleValue();
        double temp;
        if(itemType == "regular") { //if item isn't excempted from sales tax
            
            if(item.indexOf("imported") != -1) {
                temp = Math.round((priceDouble * 15.0 / 100.0)*20.0) / 20.0;
                
                String tempString = String.valueOf(temp);
                tax = round(new BigDecimal(tempString), new BigDecimal("0.05"), RoundingMode.UP);


            }
            else {
                temp = Math.round((priceDouble * 10.0 / 100.0)*20.0) / 20.0;
                //String tempString = String.valueOf(temp);
                tax = round(new BigDecimal(temp), new BigDecimal("0.05"), RoundingMode.UP);
             
            }
            
        }
        else if(item.indexOf("imported") != -1) {
            temp = Math.round((priceDouble * 5.0 / 100.0)*20.0) / 20.0;
            //String tempString = String.valueOf(temp);
            tax = round(new BigDecimal(temp), new BigDecimal("0.05"), RoundingMode.UP);

        }

    }
    
    /**
     * round: rounds up to the nearest 0.05
     *
     */
    public static BigDecimal round(BigDecimal value, BigDecimal increment,RoundingMode roundingMode) {
        if (increment.signum() == 0) {
            // 0 increment does not make much sense, but prevent division by 0
            return value;
        } 
        
        //if value is already an increment of 0.05, don't round up anymore
        if(value.multiply(new BigDecimal("100")).remainder(new BigDecimal("5")).compareTo(new BigDecimal("0")) == 0) {
            return value;
        }
        else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            BigDecimal result = divided.multiply(increment);
            return result;
        }
    }
}
