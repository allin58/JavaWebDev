package entity.qualifier;

import entity.Wallet;
import exception.PersistentException;

public class WalletQualifier {

    public void reduceCurrency(Double value, String ticker, Wallet wallet) throws PersistentException {

        switch (ticker) {
            case "BTC" :  wallet.setBtc(wallet.getBtc() - value);
                break;

            case "ETH" : wallet.setEth(wallet.getEth() - value);
                break;

            case "USDT" : wallet.setUsdt(wallet.getUsdt() - value);
                break;

        }
        if (wallet.getBtc() < 0 || wallet.getEth() < 0 ||wallet.getUsdt() < 0) {
            throw new PersistentException();
        }




    }

    public void increaseCurrency(Double value, String ticker,Wallet wallet) throws PersistentException {

        switch (ticker) {
            case "BTC" : wallet.setBtc(wallet.getBtc() + value);
                break;

            case "ETH" : wallet.setEth(wallet.getEth() + value);
                break;

            case "USDT" : wallet.setUsdt(wallet.getUsdt() + value);
                break;

        }

        if (wallet.getBtc() < 0 || wallet.getEth() < 0 ||wallet.getUsdt() < 0) {
            throw new PersistentException();
        }
    }



    public Double getAmountByTicker(Wallet wallet, String ticker) {
switch(ticker){
    case "BTC": return wallet.getBtc();
    case "ETH": return wallet.getEth();
    case "USDT": return wallet.getUsdt();
}
          return 0.0;
    }






}
