package entity;

public class Wallet extends Entity {

    //Integer userId;
    private  Double btc;
    private  Double eth;
    private  Double usdt;

   /* public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }*/

    public Double getBtc() {
        return btc;
    }

    public void setBtc(Double btc) {
        this.btc = btc;
    }

    public Double getEth() {
        return eth;
    }

    public void setEth(Double eth) {
        this.eth = eth;
    }

    public Double getUsdt() {
        return usdt;
    }

    public void setUsdt(Double usdt) {
        this.usdt = usdt;
    }

    public void reduceCurrency(Double value, String ticker) {

        switch (ticker) {
            case "BTC" : btc = btc - value;
                break;

            case "ETH" : eth = eth - value;
                break;

            case "USDT" : usdt = usdt - value;;
                break;

        }
    }

    public void increaseCurrency(Double value, String ticker) {

        switch (ticker) {
            case "BTC" : btc = btc + value;
                break;

            case "ETH" : eth = eth + value;
                break;

            case "USDT" : usdt = usdt + value;;
                break;

        }
    }



}
