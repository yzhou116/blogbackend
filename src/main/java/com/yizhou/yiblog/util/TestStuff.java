package com.yizhou.yiblog.util;

public class TestStuff {
    public static int getOuJi(int m, int n){
        int r = m%n;
        m = n;
        n = r;
        if(r == 0){
            return m;
        }
        return  getOuJi(m, n);



    }

    public static void main(String[] args) {
        System.out.println(getOuJi(1112,655));

    }
}
