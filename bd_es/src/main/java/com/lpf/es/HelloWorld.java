package com.lpf.es;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * Created by zx on 2017/8/15.
 */
public class HelloWorld {

    /**
     * es
     */
    public static void main(String[] args) {

        try {

            //设置集群名称
            Settings settings = Settings.builder()
                    .put("cluster.name", "biyao-docker-search")
                    .put("client.transport.sniff", true)
                    .build();
            //创建client
            TransportClient client = new PreBuiltTransportClient(settings).addTransportAddresses(
                    //用java访问ES用的端口是9300
                    new InetSocketTransportAddress(InetAddress.getByName("10.6.73.242"), 9300)
//                    new InetSocketTransportAddress(InetAddress.getByName("192.168.66.102"), 9300)
//                    new InetSocketTransportAddress(InetAddress.getByName("192.168.66.103"), 9300)
            );
            //搜索数据（.actionGet()方法是同步的，没有返回就等待）
            GetResponse response = client.prepareGet("geo_index_test", "geo", "10008").execute().actionGet();
//            GetResponse response = client.prepareGet("test_geo_test", "testGeoTable_test", "10008").execute().actionGet();
            //输出结果
            System.out.println(response);
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
