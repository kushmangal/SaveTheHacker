package kmzenon.savethehacker;

/**
 * Created by Naray on 07-05-2017.
 */
public class Bid {

    int quantity, price;

    public Bid(){

    }

    public Bid(int quantity, int price) {
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

}
