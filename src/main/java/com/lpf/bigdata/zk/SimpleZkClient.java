package com.lpf.bigdata.zk;

import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zk的API测试
 *
 * @author lipengfei
 * @create 2018-09-27 11:30
 **/
public class SimpleZkClient {

    private static String ZK_IPS = "hadoop01:2181,hadoop02:2181,hadoop03:2181";
    private static int session_time_out = 2000;
    private CountDownLatch cdl = new CountDownLatch(1);

    private static ZooKeeper zkClient = null;

    @Before
    public void initZk() {
        try {
            System.out.println("*************************");
            zkClient = new ZooKeeper(ZK_IPS, session_time_out, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        System.out.println("zookeeper init() sucess...state=" + event.getState());
                        System.out.println("event.getType()=" + event.getType());
                        System.out.println("event.getPath()=" + event.getPath());
                        cdl.countDown();
                    }
                }
            });
            // 在zookeeper连接创建成功前，使用cdl.await()一直在此阻塞
            cdl.await();
            System.out.println("*************************");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testQuery() throws Exception {

        List<String> children = zkClient.getChildren("/", false);
        JSONObject dataJson = null;
        for (String child : children) {

            byte[] data = zkClient.getData("/" + child, false, null);
//            dataJson = JSONObject.parseObject(new String(data));
            System.out.println("节点：" + child + "------数据：" + new String(data));

        }
    }

    @Test
    public void create() throws Exception {

        String s = zkClient.create("/lpf/srver", "xixi".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("-----" + s);

    }

    /**
     * 这是默认的watcher，什么也没有，也不需要有什么<br>
     * 因为按照功能需求，服务器端并不需要监控zk上的任何目录变化事件
     *
     * @author fei
     */
    private class MyDefaultWatcher implements Watcher {
        public void process(WatchedEvent event) {


        }
    }
}
