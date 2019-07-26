package by.taining.cryptomarket.entity.qualifier;

import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.exception.PersistentException;

/**
 * This class is to extend the functionality of wallet.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class WalletQualifier {


    /**
     * This method reduces amount of currency depend on ticker.
     * @param value value
     * @param ticker ticker
     * @param wallet wallet
     * @throws PersistentException
     */
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

    /**
     * This method increases amount of currency depend on ticker.
     * @param value value
     * @param ticker ticker
     * @param wallet wallet
     * @throws PersistentException
     */
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


    /**
     * The method for getting a amount of currency by ticker.
     * @param wallet wallet
     * @param ticker ticker
     * @return amount of currency
     */
    public Double getAmountByTicker(Wallet wallet, String ticker) {
switch(ticker){
    case "BTC": return wallet.getBtc();
    case "ETH": return wallet.getEth();
    case "USDT": return wallet.getUsdt();
}
          return 0.0;
    }

}
