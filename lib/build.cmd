@echo off
if not exist target mkdir target
if not exist target\classes mkdir target\classes


echo compile classes
javac -nowarn -d target\classes -sourcepath jvm -cp "c:\jni4net\lib\jni4net.j-0.8.8.0.jar"; "jvm\paymentgateway\extensionCls.java" "jvm\paymentgateway\TransCode.java" "jvm\paymentgateway\Receipt.java" "jvm\paymentgateway\BatchRecord.java" "jvm\paymentgateway\Pay.java" 
IF %ERRORLEVEL% NEQ 0 goto end


echo Payment2.j4n.jar 
jar cvf Payment2.j4n.jar  -C target\classes "paymentgateway\extensionCls.class"  -C target\classes "paymentgateway\TransCode.class"  -C target\classes "paymentgateway\Receipt.class"  -C target\classes "paymentgateway\BatchRecord.class"  -C target\classes "paymentgateway\Pay.class"  > nul 
IF %ERRORLEVEL% NEQ 0 goto end


echo Payment2.j4n.dll 
csc /nologo /warn:0 /t:library /out:Payment2.j4n.dll /recurse:clr\*.cs  /reference:"C:\payment\Payment2\Payment2\bin\Debug\Payment2.dll" /reference:"c:\jni4net\lib\jni4net.n-0.8.8.0.dll"
IF %ERRORLEVEL% NEQ 0 goto end


:end
