package kmzenon.savethehacker;

/**
 * Created by Naray on 07-05-2017.
 */
public class Bid {

    String quantity, price;

    public Bid(){

    }

    public Bid(String quantity, String price) {
        this.quantity = quantity;
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

}
