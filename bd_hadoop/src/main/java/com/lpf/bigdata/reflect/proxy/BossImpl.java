package com.lpf.bigdata.reflect.proxy;

/**
 * @author lipengfei
 * @create 2018-09-28 18:51
 **/
public class BossImpl implements IBoss {
    @Override
    public Integer getPhoneNumById(String id) {
        System.out.println("BossImpl.getPhoneNumById | id=" + id + "的手机原价为：5000" );
        return 5000;
    }

    @Override
    public Integer getClothesNumById(String id) {
        System.out.println("BossImpl.getClothesNumById | 品牌=" + id + "的衣服原价为：1000" );
        return 1000;
    }


}
