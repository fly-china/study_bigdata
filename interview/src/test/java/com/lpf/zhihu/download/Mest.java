package com.lpf.zhihu.download;

/**
 * @author lipengfei
 * @date 2019-06-19 19:43
 **/
public class Mest {

    public static void main(String[] args) {
        String a = "\"https://pic2.zhimg.com/v2-0d3c0d4f01a05b3eabaf340e636669e4_r.jpg;https://pic2.zhimg.com/v2-dd0f7bbd556b59b5949c2fbfb73044c5_r.jpg;https://pic4.zhimg.com/v2-3d19200b941bf8e42bf8046e6cf42f7c_r.jpg;https://pic4.zhimg.com/v2-a4d6863ec53a28a7a1bd851c6753a06f_r.jpg;https://pic2.zhimg.com/v2-0c0c9a7e3ed0df001679201ee6e4015c_r.jpg;https://pic4.zhimg.com/v2-a78fb998ada30ba63d9a956db5534460_r.jpg;https://pic4.zhimg.com/v2-838b2cd7f7ec1c1263f484c6b25ed205_r.jpg;https://pic4.zhimg.com/v2-b37204a086db4fb7877237fab9850f87_r.jpg;https://pic4.zhimg.com/v2-491c9bed33f846c301cb86dfc6fac355_r.jpg;https://pic4.zhimg.com/v2-2590032539ab37a9b5fcb60baebacc77_r.jpg;https://pic1.zhimg.com/v2-e3d1d5dfe79817b2038dac057f6b7aa3_r.jpg\"" ;

        System.out.println(a);
        System.out.println(a.substring(1, a.length()-1));
        System.out.println(a.substring(1, a.length()-1).split(";").length);
    }
}
