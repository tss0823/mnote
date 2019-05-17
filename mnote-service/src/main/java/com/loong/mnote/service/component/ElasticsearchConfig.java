package com.loong.mnote.service.component;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * 暂时先不用
 * @Description: es搜索服务配置类
 *
 * @Author: zheng.yuan
 * @Date: 2019-01-10
 **/

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.loong.mnote.dal.elasticsearch")
//@EnableJpaRepositories(basePackages = "com.loong.mnote.dal.dao")
public class ElasticsearchConfig {

    @Value("${elasticsearch.cluster-nodes}")
    private String esHost;

//    @Value("${elasticsearch.port}")
//    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
//        System.setProperty("es.set.client.transport.sniff", "true");
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws
            UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name",esClusterName).build();

        // Can't resolve symbol "PreBuiltTransportClient"
        TransportClient client = new PreBuiltTransportClient(settings);

        String[] split = esHost.split(",");
        for (String s : split) {
            client.addTransportAddress(new TransportAddress(InetAddress.getByName(s.split(":")[0]), Integer.valueOf(s.split(":")[1])));
        }
        return new ElasticsearchTemplate(client);

    }
}
