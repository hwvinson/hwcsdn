package com.es.hw.conf;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
public class ESDataSourceConfig {

    private JestClient client ;

    public JestClient getClient() {
        return client;
    }

    public ESDataSourceConfig(){
        client = getClientConfig() ;
    }

    public JestClient getClientConfig(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://127.0.0.1:9200")
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true)
                .readTimeout(10000)
                .connTimeout(500000)
                .build());
        JestClient client = factory.getObject();
        return client ;
    }
}
