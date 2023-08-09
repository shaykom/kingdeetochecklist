package com.chenmasoft.kingdeetofeishu;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class KingdeetofeishuApplication {

    public static void main(String[] args) {
        SpringApplication.run(KingdeetofeishuApplication.class, args);
    }

}
