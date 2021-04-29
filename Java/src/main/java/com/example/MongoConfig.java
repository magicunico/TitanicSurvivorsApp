package com.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

import java.net.UnknownHostException;

@Configuration
@ConditionalOnClass(MongoClient.class)
@EnableConfigurationProperties(MongoProperties.class)
@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")
public class MongoConfig {

    @Bean
    @ConditionalOnMissingBean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(new SimpleMongoClientDbFactory(new ConnectionString("mongodb://mongo_db:27017/db")));
    }
}