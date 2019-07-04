package entity;

public class Wallet extends Entity {

    //Integer userId;
    Float btc;
    Float eth;
    Float usdt;

   /* public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }*/

    public Float getBtc() {
        return btc;
    }

    public void setBtc(Float btc) {
        this.btc = btc;
    }

    public Float getEth() {
        return eth;
    }

    public void setEth(Float eth) {
        this.eth = eth;
    }

    public Float getUsdt() {
        return usdt;
    }

    public void setUsdt(Float usdt) {
        this.usdt = usdt;
    }
}
