/*
**    Chromis POS  - The New Face of Open Source POS
**    Copyright (c)2015-2016
**    http://www.chromis.co.uk
**
**    This file is part of Chromis POS Version V0.60.2 beta
**
**    Chromis POS is free software: you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation, either version 3 of the License, or
**    (at your option) any later version.
**
**    Chromis POS is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>
**
**
*/

package uk.chromis.pos.payment;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jni4net.Bridge;
import paymentgateway.BatchRecord;
import paymentgateway.Pay;
import paymentgateway.Receipt;
import uk.chromis.pos.printer.screen.DeviceDisplayWindow;
import uk.chromis.pos.util.StringUtils;
/**
 *
 *   
 */



public class PaymentGatewayExt implements PaymentGateway {
    
    private static Pay c2 = null;
    /** Creates a new instance of PaymentGatewayExt */
    public PaymentGatewayExt() {
        try{
        
        //initialize();
        }
        catch (Exception e)
        {
        Logger.getLogger(DeviceDisplayWindow.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public static void initialize()
    {
        try{
        if(c2 != null)
            c2.close();
        c2 =new Pay();
        if(!c2.Initialize())
            Logger.getLogger(DeviceDisplayWindow.class.getName()).log(Level.WARNING, null, "Failed to initialize pinpad!");
        }
        catch (Exception e)
        {
            Logger.getLogger(DeviceDisplayWindow.class.getName()).log(Level.WARNING, null, e);
        } 
    }
    
    public static JBatchRecord[] CloseBatch()
    {
        BatchRecord[] br = c2.CloseBatch();
        
        JBatchRecord[] jbr = new JBatchRecord[br.length];
       
        for(int x =0; x<br.length;x++){
            jbr[x] = new JBatchRecord();
            jbr[x].cardType = br[x].getcardType();
            jbr[x].purchaseCount = br[x].getpurchaseCount();
            jbr[x].purchaseAmount = br[x].getpurchaseAmount();
            jbr[x].correctionCount = br[x].getcorrectionCount();
            jbr[x].correctionAmount = br[x].getcorrectionAmount();
            jbr[x].refundCount = br[x].getrefundCount();
            jbr[x].refundAmount = br[x].getrefundAmount();            
        }
        return jbr;
    }
    
    
    private String lastTrans= null;
    /**
     *
     * @param payinfo
     */
    @Override
    public void execute(PaymentInfoMagcard payinfo) {
        //payinfo.paymentError("Failed to init bridge...","");
        //return;
        try{
            Receipt r;
        if(payinfo.getTotal() <0)
        {
            
            //refund...
            String[] order = payinfo.m_sTransactionID.split("\\|");
            if(order.length <2)
                return;
            r = c2.Refund(order[0],order[1],Double.toString(payinfo.getTotal()*-1));
            
        }
        else
        {           
            if(lastTrans == payinfo.m_sTransactionID)
            {
               payinfo.m_sTransactionID = StringUtils.getCardNumber();
            }
            lastTrans = payinfo.m_sTransactionID;
            r = c2.Purchase(payinfo.m_sTransactionID,Double.toString(payinfo.getTotal()));
        }
        
        payinfo.r = r;
        payinfo.ranTrans=true;
        String comp = r.getCompleted();
        if("false".equals(comp))
        {
            String err = r.getErrorMessage();
            payinfo.paymentError(err, "Transaction did not complete");    
            return;
        }
        String sig = r.getSignature();
        String tip = r.getTipAmount();
        String amount = r.getAmount();
        String authCode = r.getAuthCode();

        String msg = r.getMessage();
        payinfo.m_sCardNumber = r.getPan();        
        try{
        payinfo.m_dTotal = Double.parseDouble(r.getAmount());
        }
        catch(Exception e1)
        {
            
        }
        
        //String sAuthorization, String sTransactionId, String sReturnMessage
        String rc = r.getResponseCode();
        if(rc==null)
        {//incomplete
            payinfo.paymentError("Incomplete", r.getErrorMessage());
        }
        else{
            int ResponseCode = Integer.parseInt(r.getResponseCode());
        
            if(ResponseCode <=49)
                payinfo.paymentOK(r.getAuthCode(),payinfo.m_sTransactionID+"|"+r.getTransactionNo(),r.getMessage());
            else
                payinfo.paymentError("Declined",r.getErrorMessage());
            return;
        }
        }
        catch(Exception e)
        {
            payinfo.paymentError("", e.toString());
            initialize();
            return;
        }
       // payinfo.paymentOK("OK", payinfo.getTransactionID() , "");
    }
}
