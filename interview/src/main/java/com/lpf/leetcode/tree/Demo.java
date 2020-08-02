package com.lpf.leetcode.tree;

/**
 * @author lipengfei
 * @create 2020-07-28 11:35
 **/
public class Demo {
    public static int testFinally(){
        try {
            return 1;
        } catch (Exception e) {
            return 0;
        }finally{
            System.out.println("execute finally");
        }
    }
    public static void main(String[] args){
        int result = testFinally();
        System.out.println(result);
    }
}
