package by.taining.cryptomarket.entity;

/**
 * The order class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class Order extends Entity {

    /**
     * The field for storage a userId.
     */
    private Integer userId;

    /**
     * The field for storage a pair.
     */
    private String pair;

    /**
     * The field for storage a amount.
     */
    private  Double amount;

    /**
     * The field for storage a price.
     */
    private Double price;

    /**
     * The field for storage a type.
     */
    private  String type;

    /**
     * The field for storage a state.
     */
    private  String state;

    /**
     * The getter for userId.
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * The setter for userId.
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * The getter for pair.
     * @return pair
     */
    public String getPair() {
        return pair;
    }

    /**
     * The setter for pair.
     * @param pair
     */
    public void setPair(String pair) {
        this.pair = pair;
    }

    /**
     * The getter for amount.
     * @return amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * The setter for amount.
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * The getter for price.
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * The setter for price.
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * The getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * The setter for type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * The getter for state.
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * The setter for state.
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
}
