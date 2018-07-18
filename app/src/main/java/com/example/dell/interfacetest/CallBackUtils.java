package com.example.dell.interfacetest;

public class CallBackUtils {
    private static CallBack sCallBack;

    public static void setCallBack(CallBack callBack) {
        sCallBack = callBack;
        String s = "hello world";
        if(callBack != null) {
            callBack.dosomething (s);
        }
    }
}
