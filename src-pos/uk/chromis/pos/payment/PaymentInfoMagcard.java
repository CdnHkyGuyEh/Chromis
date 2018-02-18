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
package uk.chromis.pos.payment;
import java.text.DecimalFormat;
import paymentgateway.Receipt;
 
/**
 *
 *   
 */
public class PaymentInfoMagcard extends PaymentInfo {
     
    public Receipt r;
    protected boolean ranTrans = false;
    protected double m_dTotal;
    protected String m_sHolderName;
    protected String m_sCardNumber;
    protected String m_sExpirationDate;
    protected String track1;
    protected String track2;
    protected String track3;
    protected String m_sTransactionID;
    protected String m_sAuthorization;    
    protected String m_sErrorMessage;
    protected String m_sReturnMessage;
    protected String m_dCardName =null; 
  
    /** Creates a new instance of PaymentInfoMagcard
     * @param sHolderName
     * @param sCardNumber
     * @param track3
     * @param sExpirationDate
     * @param track2
     * @param track1
     * @param sTransactionID
     * @param dTotal */
    public PaymentInfoMagcard(
            String sHolderName, 
            String sCardNumber, 
            String sExpirationDate, 
            String track1, 
            String track2, 
            String track3, 
            String sTransactionID, 
            double dTotal) {
        
        m_sHolderName = sHolderName;
        m_sCardNumber = sCardNumber;
        m_sExpirationDate = sExpirationDate;
        this.track1 = track1;
        this.track2 = track2;
        this.track3 = track3;
        
        m_sTransactionID = sTransactionID;
        m_dTotal = dTotal;
        
        m_sAuthorization = null;
        m_sErrorMessage = null;
        m_sReturnMessage = null;
    }
    
    /** Creates a new instance of PaymentInfoMagcard
     * @param sHolderName
     * @param sCardNumber
     * @param sExpirationDate
     * @param dTotal
     * @param sTransactionID */
    public PaymentInfoMagcard(
            String sHolderName, 
            String sCardNumber, 
            String sExpirationDate, 
            String sTransactionID, 
            double dTotal) {
        this(sHolderName, sCardNumber, sExpirationDate, null, null, null, sTransactionID, dTotal);
    }

    
    /**
     *
     * @return
     */
    @Override
    public PaymentInfo copyPayment(){
        PaymentInfoMagcard p = new PaymentInfoMagcard(
                m_sHolderName, 
                m_sCardNumber, 
                m_sExpirationDate, 
                track1, 
                track2, 
                track3, 
                m_sTransactionID, 
                m_dTotal);
        
        p.m_sAuthorization = m_sAuthorization;
        p.m_sErrorMessage = m_sErrorMessage;
        return p;
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return "magcard";
    }
    public void save() {
        
        
    }
    public boolean isApplicationLabel()
    {
        if(r!=null){
            String s = r.getAppLabel();
            if(s==null || s.length()==0)
                return false;
            return true;
        }
        return false;
    }
    public String printApplicationLabel()
    {
        if(r!=null){
            String s = r.getAppLabel();
            if(s==null || s.length()==0)
                return " ";
            return s;
        }
        return " ";
    }
    public boolean isApplicationPreferredName()
    {
        if(r!=null){
            String s = r.getAppPreferredName();
            if(s==null || s.length()==0)
                return false;
            return true;
        }
        return false;
    }
    public String printApplicationPreferredName()
    {
        if(r!=null){
            String s = r.getAppPreferredName();
            if(s==null || s.length()==0)
                return " ";
            return s;
        }
           
        return " ";
    }
    
    
    public boolean isEMVAid()
    {
        if(r!=null){
            String s = r.getEmvCompletionData();
            if(s==null || s.length()==0)
                return false;
            return true;
        }
        return false;
    }
    public String printEMVAid() //Maybe, cant tell tap doesnt work on this shit
    {
        if(r!=null){
            String s = r.getEmvCompletionData();
            if(s==null || s.length()==0)
                return " ";
            return s;
        }
        return " ";
    }
    
    public String printVerByPin(){
        if(r!=null)
        {
            if(english()){
                if ("P".equals(printCVMInd()) || "B".equals(printCVMInd()))
                {
                    return "VERIFIED BY PIN";
                }
                else if("F".equals(r.getPanEntry()))
                {
                    return "CHIP CARD SWIPED";
                }
            }
            else
            {
                if ("P".equals(printCVMInd()) || "B".equals(printCVMInd()))
                {
                    return "VERIFIEE PAR NIP";
                }
                else if("F".equals(r.getPanEntry()))
                {
                    return "CARTE A PUCE GLISSEE";
                }
            }
        }
        return " ";
    }
    public boolean isVerByPin()
    {
        if(printVerByPin().length()>1)
            return true;
        return false;
    }
    
    public String printResponseLine()
    {
        if(english()){
            if(r!=null){
                String rc = r.getResponseCode();

                int iRc = Integer.parseInt(rc);
                if(iRc <=49)
                {
                    return r.getIsoCode() + " Approved - Thank You "+r.getResponseCode();    
                }
                String ct = r.getCardType();
                int iso = Integer.parseInt(r.getIsoCode());
                if("D".equals(ct) && (iso==5 || iso == 51 || iso==54 || iso==55 || iso ==57 || iso ==58 || iso == 61 || iso == 62 || iso ==65 || iso == 75 || iso == 82 || iso == 92))
                    return r.getIsoCode() + " Transaction Not Approved "+r.getResponseCode();    

                else
                    return r.getIsoCode() +" Transaction Not Completed "+ r.getResponseCode();                        
            }
        }
        else
        {
            if(r!=null){
                String rc = r.getResponseCode();

                int iRc = Integer.parseInt(rc);
                if(iRc <=49)
                {
                    return r.getIsoCode() + " Approuvee - Merci "+r.getResponseCode();    
                }
                String ct = r.getCardType();
                int iso = Integer.parseInt(r.getIsoCode());
                if("D".equals(ct) && (iso==5 || iso == 51 || iso==54 || iso==55 || iso ==57 || iso ==58 || iso == 61 || iso == 62 || iso ==65 || iso == 75 || iso == 82 || iso == 92))
                    return r.getIsoCode() + " Opération Refusée "+r.getResponseCode();    

                else
                    return r.getIsoCode() +" Opération non Complétée "+ r.getResponseCode();                        
            }
        }
        return " ";
    }
    
    public boolean isTVR()
    {
        if(r!=null){
            String s = r.getTvrTCACC();
            if(s==null || s.length()==0)
                return false;
            return true;
        }
        return false;
    }
    public String printTVR()
    {
        if(r!=null){
            String s = r.getTvrTCACC();
            if(s==null || s.length()==0)
                return " ";
            return s;
        }
        return " ";
    }
    public boolean isTSI()
    {
        if(r!=null){
            String s = r.getTSI();
            if(s==null || s.length()==0)
                return false;
            return true;
        }
        return false;
    }
public String printTSI()
    {
        if(r!=null){
            String s = r.getTSI();
            if(s==null || s.length()==0)
                return " ";
            return s;
        }            
       return " ";
    }
    public boolean isCVMInd()
    {
        if(r!=null){
            String s = r.getCvmIndicator();
            if(s==null || s.length()==0)
                return false;
            return true;
        }
        return false;
    }
public String printCVMInd()
{
     if(r!=null){
            String s = r.getCvmIndicator();
            if(s==null || s.length()==0)
                return " ";
            return s;
        }
           
       return " ";
}
    
    public String printReferenceNum()
    {
        if(r!=null)
            return r.getRefNum() + " "+r.getPanEntry();
        return " ";
    }
    
    public String printTransDate()
    {
        if(r!=null)
            return r.getTransDate()+" "+r.getTransTime();
        return " ";
    }

    public boolean didTransRun()
    {
        if(r==null)
            return false;
        String comp = r.getCompleted();
        if(!ranTrans || "false".equals(comp))
            return false;
        return true; 
        
    }
    /**
     *
     * @return
     */
    @Override
    public double getTotal() {
        if(!ranTrans)
            return m_dTotal;
        String comp = r.getCompleted();
        String rc = r.getResponseCode();
        if(rc==null) return 0;
        if(comp==null || "false".equals(comp) || Integer.parseInt(r.getResponseCode()) >50)
            return 0;
        return m_dTotal;
    }
    
    public String printTotal()
    {
        DecimalFormat df2 = new DecimalFormat(".##");
        return df2.format(getTotal());
    }
        public String printTip()
    {
        DecimalFormat df2 = new DecimalFormat(".##");
        return df2.format(getTip());
    }
    /**
     *
     * @return
     */
    public boolean isPaymentOK() {
        return m_sAuthorization != null;
    }    

    /**
     *
     * @return
     */
    public String getHolderName() {
        return m_sHolderName;
    }
    
    /**
     *
     * @return
     */
    @Override
  public String getCardName() {      
      String cardName = getCardType();
      if("INTERAC".equals(cardName.toUpperCase())){
        int acct = Integer.parseInt( r.getAccountType());
        if(english()){
            if(acct==0)
                return "INTERAC";
            if(acct==1)
                return "INTERAC CHEQUING";
            if(acct==2)
                return "INTERAC SAVINGS";
            if(acct==4)
                return "INTERAC";
        }
        else{
            if(acct==0)
                return "INTERAC";
            if(acct==1)
                return "INTERAC CHEQUE";
            if(acct==2)
                return "INTERAC ÉPARGNE";
            if(acct==4)
                return "INTERAC";
        }
      }
      return getCardType();
   }
  
    /**
     *
     * @return
     */
    public String getCardNumber() {
        return "************"+r.getPan();
    }

    /**
     *
     * @return
     */
    public String getExpirationDate() {
        return m_sExpirationDate;
    }    

    /**
     *
     * @return
     */
    @Override
    public String getTransactionID() {
        return m_sTransactionID;
    }

    public boolean isCredit(){
        String ct = getCardType();
        if("INTERAC".equals(ct))
            return false;
        return true;
    }
    public boolean english(){
        String l = r.getLang();
        if("FRENCH".equals(l.toUpperCase()))
            return false;
        return true;       
    }
    
    public boolean isTip()
    {
        if(getTip()<=0)
            return false;
        return true;
    }
    
    /**
     *
     * @param sCardNumber
     * @return
     */
    @Override
    public String getCardType(){
        String c = "UNKNOWN";
        String ct = r.getCardType();
       if (null != ct) switch (ct.trim()) {
            case "V":
                c = "VISA";
                break;
            case "DS":
            case "NO":
                c = "DISCOVER";
                break;
            case "M":
                c = "MASTERCARD";
                break;
            case "AX":
                c = "AMERICAN EXPRESS";
                break;
            case "C":
            case "C1":
                c = "JCB";
                break;
            case "SE":
                c = "SEARS";
                break;
            case "P":
            case "D":
                c="INTERAC";
                break;
            default:
                break;
        }
       m_dCardName = c;
       return c;
   }
   
    public boolean isSignatureRequired()
    {
        String ct = getCardType();
        if("INTERAC".equals(ct.toUpperCase()))
            return false;
        String panEntry = r.getPanEntry();
        String cvm = r.getCvmIndicator();
        if((cvm == null  && !"T".equals(panEntry)) 
                || "S".equals(cvm) || "B".equals(cvm) || (cvm.length() ==0 && !"T".equals(panEntry)))
            return true;
        return false;
    }
    
    /**
     * Get tracks of magnetic card.
     *   Framing characters: 
     *    - start sentinel (SS)
     *    - end sentinel (ES) 
     *    - LRC 
     * @param framingChar 
     *    true: including framing characters
     *    false: excluding framing characters
     * @return tracks of the magnetic card
     */
    public String getTrack1(boolean framingChar) {
        return (framingChar)
            ? track1
            : track1.substring(1, track1.length()-2);
    }

    /**
     *
     * @param framingChar
     * @return
     */
    public String getTrack2(boolean framingChar) {
        return (framingChar)
            ? track2
            : track2.substring(1, track2.length()-2);
    }

    /**
     *
     * @param framingChar
     * @return
     */
    public String getTrack3(boolean framingChar) {
        return (framingChar)
            ? track3
            : track3.substring(1, track3.length()-2);
    }
    
    /**
     *
     * @return
     */
    public String getAuthorization() {
        return m_sAuthorization;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return m_sAuthorization;
    }
    
    /**
     *
     * @param sMessage
     * @param moreInfo
     */
    public void paymentError(String sMessage, String moreInfo) {
        m_sAuthorization = null;
        m_sErrorMessage = sMessage + "\n" + moreInfo;
    }
    
    public String printDateTime()
    {
        return r.getTransDate()+" " + r.getTransTime();
    }

    
    
    /**
     *
     * @param returnMessage
     */
    public void setReturnMessage(String returnMessage){
        m_sReturnMessage = returnMessage;
    }
    
    /**
     *
     * @return
     */
    public String getReturnMessage(){
        return m_sReturnMessage;
    }
    
    /**
     *
     * @param sAuthorization
     * @param sTransactionId
     * @param sReturnMessage
     */
    public void paymentOK(String sAuthorization, String sTransactionId, String sReturnMessage) {
        m_sAuthorization = sAuthorization;
        m_sTransactionID = sTransactionId;
        m_sReturnMessage = sReturnMessage;
        m_sErrorMessage = null;
    }  
    
    @Override
    public double getTip()
    {
        if(!isPaymentOK())
            return 0;
        String tip = r.getTipAmount();
        if(tip!=null)
        {
            return Double.parseDouble(tip);
        }
        return 0;
    }
            

    
    /**
     *
     * @return
     */
    public String printCardNumber() {
        return "************"+r.getPan();
    }

    /**
     *
     * @return
     */
    public String printExpirationDate() {
        return m_sExpirationDate;
    }

    /**
     *
     * @return
     */
    public String printAuthorization() {
        return m_sAuthorization;
    }

    /**
     *
     * @return
     */
    public String printTransactionID() {
        return m_sTransactionID;
    }

    /**
     *
     * @return
     */
    @Override
    public double getPaid() {
        return getTotal(); 
    }

    /**
     *
     * @return
     */
    @Override
    public double getChange(){
        /*if(!didTransRun())
            return 0;
        String rc = r.getResponseCode();
        if(rc==null) return 0;
        if(Integer.parseInt(r.getResponseCode()) >50)
            return 0;
        try {
            return Double.parseDouble(r.getTipAmount());
        }
        catch(NumberFormatException e)
        {
            return 0;
        }*/
        return 0;
   }   

    /**
     *
     * @return
     */
    @Override
    public double getTendered() {
        return getTotal();
    }
  
}