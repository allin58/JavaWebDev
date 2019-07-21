package entity;

public class CryptoPair extends  Entity {

    private Integer firstCurrency;
    private Integer secondCurrency;
    private Boolean active;


    public Integer getFirstCurrency() {
        return firstCurrency;
    }

    public void setFirstCurrency(Integer firstCurrency) {
        this.firstCurrency = firstCurrency;
    }

    public Integer getSecondCurrency() {
        return secondCurrency;
    }

    public void setSecondCurrency(Integer secondCurrency) {
        this.secondCurrency = secondCurrency;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}