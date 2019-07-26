package by.taining.cryptomarket.entity.mapping;

import by.taining.cryptomarket.entity.Entity;

/**
 * The class for mapping of Transaction.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MappingTransaction extends Entity {


    /**
     * The field for userName.
     */
    private String user;

    /**
     * The field for name of coin.
     */
    private String coin;

    /**
     * The field for amount.
     */
    private Double amount;

    /**
     * The field for type.
     */
    private String type;

    /**
     * The field for status.
     */
    private String status;


    /**
     * Getter for status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for status.
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for user.
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter for user.
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getter for coin.
     * @return coin
     */
    public String getCoin() {
        return coin;
    }

    /**
     * Setter for coin.
     * @param coin
     */
    public void setCoin(String coin) {
        this.coin = coin;
    }

    /**
     * Getter for amount.
     * @return amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Setter for amount.
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
