package by.taining.cryptomarket.entity;

import java.sql.Timestamp;

/**
 * The transaction class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class Transaction extends Entity {

    /**
     * The field for storage a userId.
     */
    private Integer userId;

    /**
     * The field for storage a coinId.
     */
    private Integer coinId;

    /**
     * The field for storage a amount.
     */
    private Double amount;

    /**
     * The field for storage a type.
     */
    private String type;

    /**
     * The field for storage a timestamp.
     */
    private Timestamp timestamp;

    /**
     * The field for storage a status.
     */
    private String status;

    /**
     * The getter for status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * The setter for status.
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }


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
     * The getter for coinId.
     * @return coinId
     */
    public Integer getCoinId() {
        return coinId;
    }

    /**
     * The setter for coinId.
     * @param coinId
     */
    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
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
     * The getter for timestamp.
     * @return timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * The setter for timestamp.
     * @param timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
