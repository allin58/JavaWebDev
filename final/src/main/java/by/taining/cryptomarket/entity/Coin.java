package by.taining.cryptomarket.entity;


/**
 * The coin class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class Coin extends Entity {

    /**
     * The field for storage a ticker.
     */
    private String ticker;


    /**
     * The field for storage a fullName.
     */
    private String fullName;

    /**
     * Getter for ticker.
     * @return ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Setter for ticker.
     * @param ticker
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * Getter for fullName.
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter for fullName.
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
