// ------------------------------------------------------------------------------
//  <autogenerated>
//      This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
// 
//      Changes to this file may cause incorrect behavior and will be lost if 
//      the code is regenerated.
//  </autogenerated>
// ------------------------------------------------------------------------------

package paymentgateway;

@net.sf.jni4net.attributes.ClrType
public class BatchRecord extends system.Object {
    
    //<generated-proxy>
    private static system.Type staticType;
    
    protected BatchRecord(net.sf.jni4net.inj.INJEnv __env, long __handle) {
            super(__env, __handle);
    }
    
    @net.sf.jni4net.attributes.ClrConstructor("()V")
    public BatchRecord() {
            super(((net.sf.jni4net.inj.INJEnv)(null)), 0);
        paymentgateway.BatchRecord.__ctorBatchRecord0(this);
    }
    
    @net.sf.jni4net.attributes.ClrMethod("()V")
    private native static void __ctorBatchRecord0(net.sf.jni4net.inj.IClrProxy thiz);
    
    @net.sf.jni4net.attributes.ClrMethod("()LSystem/String;")
    public native java.lang.String getcardType();
    
    @net.sf.jni4net.attributes.ClrMethod("(LSystem/String;)V")
    public native void setcardType(java.lang.String value);
    
    @net.sf.jni4net.attributes.ClrMethod("()LSystem/String;")
    public native java.lang.String getpurchaseCount();
    
    @net.sf.jni4net.attributes.ClrMethod("(LSystem/String;)V")
    public native void setpurchaseCount(java.lang.String value);
    
    @net.sf.jni4net.attributes.ClrMethod("()LSystem/String;")
    public native java.lang.String getpurchaseAmount();
    
    @net.sf.jni4net.attributes.ClrMethod("(LSystem/String;)V")
    public native void setpurchaseAmount(java.lang.String value);
    
    @net.sf.jni4net.attributes.ClrMethod("()LSystem/String;")
    public native java.lang.String getrefundCount();
    
    @net.sf.jni4net.attributes.ClrMethod("(LSystem/String;)V")
    public native void setrefundCount(java.lang.String value);
    
    @net.sf.jni4net.attributes.ClrMethod("()LSystem/String;")
    public native java.lang.String getrefundAmount();
    
    @net.sf.jni4net.attributes.ClrMethod("(LSystem/String;)V")
    public native void setrefundAmount(java.lang.String value);
    
    @net.sf.jni4net.attributes.ClrMethod("()LSystem/String;")
    public native java.lang.String getcorrectionCount();
    
    @net.sf.jni4net.attributes.ClrMethod("(LSystem/String;)V")
    public native void setcorrectionCount(java.lang.String value);
    
    @net.sf.jni4net.attributes.ClrMethod("()LSystem/String;")
    public native java.lang.String getcorrectionAmount();
    
    @net.sf.jni4net.attributes.ClrMethod("(LSystem/String;)V")
    public native void setcorrectionAmount(java.lang.String value);
    
    public static system.Type typeof() {
        return paymentgateway.BatchRecord.staticType;
    }
    
    private static void InitJNI(net.sf.jni4net.inj.INJEnv env, system.Type staticType) {
        paymentgateway.BatchRecord.staticType = staticType;
    }
    //</generated-proxy>
}
