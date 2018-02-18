/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.chromis.pos.payment;

/**
 *
 * @author Justin
 */
public class JBatchRecord
{
    public String cardType, purchaseCount, purchaseAmount, refundCount, refundAmount, correctionCount, correctionAmount;
    public String getCardType()
    {
        if (null != cardType){
        cardType= cardType.trim();
        switch (cardType) {
            case "V":
                return "VISA";
                
            case "DS":
            case "NO":
                return  "DISCOVER";
                
            case "M":
                return  "MASTERCARD";
                
            case "AX":
                return  "AMERICAN EXPRESS";
                
            case "C":
            case "C1":
                return  "JCB";
                
            case "SE":
                return  "SEARS";
                
            case "P":
            case "D":
                return "INTERAC";
                
            default:
                break;
        }
        }
       return "UNKNOWN";   
    }
    public String getPurchaseCount()
    {
        return purchaseCount;
    }
    public String getPurchaseAmount()
    {
        return purchaseAmount;
    }
    public String getCorrectionCount()
    {
        return correctionCount;
    }
    public String getCorrectionAmount()
    {
        return correctionAmount;
    }
    public String getRefundCount()
    {
        return refundCount;
    }
    public String getRefundAmount()
    {
        return refundAmount;
    }
    
}