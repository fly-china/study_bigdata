package test;

import java.util.concurrent.Executors;

/**
 * 枚举测试
 *
 * @author lipengfei
 * @create 2020-06-03 11:42
 **/
public class EnumTest {

    public static void main(String[] args) {

        String currentVersion = "V3_0_3_SNAPSHOT";
        VersionEnum currentVersionEnum = VersionEnum.valueOf(currentVersion);
        System.out.println("versionEnum.toString=" + currentVersionEnum.toString());
        System.out.println("versionEnum.toString=" + currentVersionEnum.name());
        System.out.println(" versionEnum.ordinal=" + currentVersionEnum.ordinal());

        if (currentVersionEnum.ordinal() >= VersionEnum.V3_0_3_SNAPSHOT.ordinal()) {
            System.out.println("当前版本号大于等于V3_0_4_SNAPSHOT");
        } else {
            System.out.println("当前版本号小于V3_0_4_SNAPSHOT");
        }

        Executors.newFixedThreadPool(8);


    }
}
