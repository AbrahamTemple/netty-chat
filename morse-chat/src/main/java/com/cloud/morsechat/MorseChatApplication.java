package com.cloud.morsechat;

import com.cloud.morsechat.netty.WebsocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@EnableJpaRepositories //开启JPA，这里为了使用Hibernate
@SpringBootApplication
public class MorseChatApplication implements CommandLineRunner{

    @Resource
    private WebsocketServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(MorseChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        InetAddress.getLocalHost().getHostAddress() //如果网卡是移动网卡则换成localhost
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",7000);
        nettyServer.start(address);
    }
}
