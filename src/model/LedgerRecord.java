package model;

import java.util.Date;

public class LedgerRecord {
    private Date paymentDate;
    private int billID;
    private int paymentID;
    private String paymentAmount;
    private String balanceAfterPayment;

    public LedgerRecord(Date paymentDate, int billID, int paymentID, String paymentAmount, String balanceAfterPayment) {
        this.paymentDate = paymentDate;
        this.billID = billID;
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.balanceAfterPayment = balanceAfterPayment;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public int getBillID() {
        return billID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getBalanceAfterPayment() {
        return balanceAfterPayment;
    }
}