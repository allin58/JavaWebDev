package entity;

public class WalletQualifier {

    public void reduceCurrency(Double value, String ticker,Wallet wallet) {

        switch (ticker) {
            case "BTC" :  wallet.setBtc(wallet.getBtc() - value);
                break;

            case "ETH" : wallet.setEth(wallet.getEth() - value);
                break;

            case "USDT" : wallet.setUsdt(wallet.getUsdt() - value);
                break;

        }
    }

    public void increaseCurrency(Double value, String ticker,Wallet wallet) {

        switch (ticker) {
            case "BTC" : wallet.setBtc(wallet.getBtc() + value);
                break;

            case "ETH" : wallet.setEth(wallet.getEth() + value);
                break;

            case "USDT" : wallet.setUsdt(wallet.getUsdt() + value);
                break;

        }
    }





}
