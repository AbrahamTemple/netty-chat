package com.cloud.morsechat;

import com.cloud.morsechat.netty.WebsocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@SpringBootApplication
public class MorseChatApplication implements CommandLineRunner{

    @Resource
    private WebsocketServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(MorseChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",7000);
        nettyServer.start(address);
    }
}
