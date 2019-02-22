package com.lpf.bigdata.hive.udf;
//
//import org.apache.hadoop.hive.ql.exec.UDF;
//import org.apache.hadoop.io.Text;

/**
 * 第一个Udf程序
 *
 * @author lipengfei
 * @create 2019-01-22 13:04
 **/
public class HelloWorldUDF  {
//    public Text evaluate(String input) {
//        return new Text("Hello: " + input);
//    }


    public static void main(String[] args) {
//        HelloWorldUDF UDF = new HelloWorldUDF();
        int[] arr = new int[3];
        arr[2] =1;

        ++arr[2];
        "aaa".length();
        System.out.println(arr[2]);

        arr[2] += 10;
        System.out.println(arr[2]);

    }
}
