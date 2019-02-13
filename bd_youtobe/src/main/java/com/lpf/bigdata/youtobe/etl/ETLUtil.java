package com.lpf.bigdata.youtobe.etl;

/**
 * 清洗基础数据至结构化数据
 *
 * @author lipengfei
 * @create 2019-01-28 11:15
 **/
public class ETLUtil {

    /**
     * 1、过滤不合法数据
     * 2、去掉&符号左右两边的空格
     * 3、\t 换成&符号
     *
     * @param ori * @return
     */
    public static String getETLString(String ori) {
        String[] splits = ori.split("\t"); //1、过滤不合法数据
        if (splits.length < 9) return null; //2、去掉&符号左右两边的空格

        splits[3] = splits[3].replaceAll(" ", "");
        StringBuilder sb = new StringBuilder(); //3、\t 换成&符号
        for (int i = 0; i < splits.length; i++) {
            sb.append(splits[i]);
            if (i < 9) {
                if (i != splits.length - 1) {
                    sb.append("\t");
                }
            } else {
                if (i != splits.length - 1) {
                    sb.append("&");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "LKh7zAJ4nwo\tTheReceptionist\t653\tEntertainment\t424\t13021\t4.34\t1305\t744\tDjdA-5oKYFQ\tNxTDlnOuybo\tc-8VuICzXtU\tDH56yrIO5nI\tW1Uo5DQTtzc\tE-3zXq_r4w0\t1TCeoRPg5dE\tyAr26YhuYNY\t2ZgXx72XmoE\t-7ClGo-YgZ0\tvmdPOOd6cxI\tKRHfMQqSHpk\tpIMpORZthYw\t1tUDzOp10pk\theqocRij5P0\t_XIuvoH6rUg\tLGVU5DsezE0\tuO2kj6_D8B4\txiDqywcDQRM\tuX81lMev6_o";
        s = "MEvoy_owET8\tsmpfilms\t736\tTravel & Places\t921\t109673\t4.25\t1181\t774\tYtX2nwowMtU\tA5dp02FXDmM\tbGoUu4gAHaI\tfaDB-ToajhM\tsrcg9xLjtuE\t9aE4eMVeUEw\tG5fZky7Nm1k\tUEXvMJo3ZAY\tsCTbH-VP7mA\tWiriPTfpIP8\tW-s_e61hkys\tcQWtiU6d99w\t93LHxjgQ4LE\tJEiATJFBWO0\tJzjnhpqWIPs\tG_qfXiOkYPU\tGd6M-B3FOaQ\tY5pMgbhyb18\t7jdAdCmMRkg\tyxbLFd6Y38E";
        String etlString = ETLUtil.getETLString(s);
        System.out.println(etlString);


    }
}
