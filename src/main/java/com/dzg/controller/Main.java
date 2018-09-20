package com.dzg.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] array={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int m, d;
        int v = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println();
//        String[] sp = s.split(" ");
        m = Integer.parseInt(s);
        String s1= br.readLine();
        d = Integer.parseInt(s1);
        if(m<1 || m>12){
//            System.out.println("month error");
            return;
        }else {
            if(d<1 || d>array[m-1]){
//                System.out.println("day error");
                return;
            }else {
                for(int i = 0; i < m-1; i++){
                    v += array[i];
                }
                v += (d - 1);
                System.out.println(v%7+1);
            }
        }
    }
}
