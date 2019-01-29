package com.wt.sort;

import java.util.ArrayList;
import java.util.List;

public class aaaa {

    static boolean foo(char c) {
        System.out.print(c);
        return true;
    }

    public static void main(String[] args) {
       List<String> aa = new ArrayList<>();
       aa.add("F1");
       aa.add("F2");
       aa.add("F3");
       
       for(String temp:aa) {
           if("F3".equals(temp)) {
               aa.remove(temp);
           }
       }
    }

    static void pong() {
        System.out.print("pong");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
