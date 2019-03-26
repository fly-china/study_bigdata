package com.lpf.interview.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取本机IP
 *
 * @author lipengfei
 * @create 2019-03-22 15:01
 **/
public class NetAddressDemo {


    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = Inet4Address.getLocalHost();

        byte[] address = localHost.getAddress();
        String hostAddress = localHost.getHostAddress();
        String hostName = localHost.getHostName();
        String canonicalHostName = localHost.getCanonicalHostName();

        System.out.println("address:" + new String(address));
        System.out.println("hostAddress:" + hostAddress);
        System.out.println("hostName:" + hostName);
        System.out.println("canonicalHostName:" + canonicalHostName);
    }

}
