package com.ecommerce.productcatalogueservices.test;

import static java.util.Collections.swap;

public class Test {

    public static void main(String args []){
        //input
        int s[] = {8,10,14};
        int e[] = {10,12,19};

        for(int i=0; i<s.length; i++){
            for(int j=i+1; j<s.length; j++){
                if(s[i] > s[j]){
                    swap(s, i , j);
                    swap(e, i, j);
                }
            }
        }


        int x =  s[0];
        int y = e[0];

        for(int i=1; i< s.length; i++){
            if(y<=s[i]){
                x=s[i];
                y = Math.max(y, e[i]);
            }else{
                x= s[i];
                y = e[i];
            }
            System.out.println(x+" "+y);
        }
    }

    static void swap(int [] s, int i , int j){
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;


    }
}
