//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by jni4net. See http://jni4net.sourceforge.net/ 
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace PaymentGateway {
    
    
    #region Component Designer generated code 
    public partial class ErrorCodes_ {
        
        public static global::java.lang.Class _class {
            get {
                return global::PaymentGateway.@__ErrorCodes.staticClass;
            }
        }
    }
    #endregion
    
    #region Component Designer generated code 
    [global::net.sf.jni4net.attributes.JavaProxyAttribute(typeof(global::PaymentGateway.ErrorCodes), typeof(global::PaymentGateway.ErrorCodes_))]
    [global::net.sf.jni4net.attributes.ClrWrapperAttribute(typeof(global::PaymentGateway.ErrorCodes), typeof(global::PaymentGateway.ErrorCodes_))]
    internal sealed partial class @__ErrorCodes : global::java.lang.Object {
        
        internal new static global::java.lang.Class staticClass;
        
        private @__ErrorCodes(global::net.sf.jni4net.jni.JNIEnv @__env) : 
                base(@__env) {
        }
        
        private static void InitJNI(global::net.sf.jni4net.jni.JNIEnv @__env, java.lang.Class @__class) {
            global::PaymentGateway.@__ErrorCodes.staticClass = @__class;
        }
        
        private static global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> @__Init(global::net.sf.jni4net.jni.JNIEnv @__env, global::java.lang.Class @__class) {
            global::System.Type @__type = typeof(__ErrorCodes);
            global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod> methods = new global::System.Collections.Generic.List<global::net.sf.jni4net.jni.JNINativeMethod>();
            return methods;
        }
        
        new internal sealed class ContructionHelper : global::net.sf.jni4net.utils.IConstructionHelper {
            
            public global::net.sf.jni4net.jni.IJvmProxy CreateProxy(global::net.sf.jni4net.jni.JNIEnv @__env) {
                return new global::PaymentGateway.@__ErrorCodes(@__env);
            }
        }
    }
    #endregion
}
