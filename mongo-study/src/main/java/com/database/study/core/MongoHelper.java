package com.database.study.core;

import com.mongodb.*;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

public class MongoHelper {

    private static final Logger logger = LoggerFactory
            .getLogger(MongoHelper.class);

    static final String DBName = "mongo";
    static final String ServerIp= "192.168.1.113";
    static final int PORT = 27017;
    static final String USERNAME = "admin";
    static final String PAASWORD = "123456";

    static final ServerAddress serverAddress = new ServerAddress(ServerIp,PORT);


    public MongoHelper() {
    }

    public static MongoClient getMongoClient() {
        MongoClient mongoClient = null;
        try {
            MongoCredential credential=null;
            //要输入用户名、密码访问数据库
            if(Strings.isNotBlank(USERNAME)&&Strings.isNotBlank(PAASWORD)  ){
                credential= MongoCredential.createCredential(USERNAME, DBName, PAASWORD.toCharArray());
            }

            // 连接mongodb 服务
            if(credential!=null){
                MongoClientOptions mongoClientOptions = MongoClientOptions.builder().sslEnabled(false).build();
                mongoClient = new MongoClient(serverAddress,credential,mongoClientOptions);
            }else {
                mongoClient = new MongoClient(serverAddress);
            }

            logger.debug("Connect to mongodb successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return mongoClient;
    }

    public static MongoDatabase getMongoDataBase(MongoClient mongoClient) {
        MongoDatabase mongoDataBase = null;
        try {
            if (mongoClient != null) {
                // 连接到数据库
                mongoDataBase = mongoClient.getDatabase(DBName);
                logger.debug("Connect to DataBase successfully");
            } else {
                throw new RuntimeException("MongoClient不能够为null?");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongoDataBase;
    }

    public static MongoDatabase getMongoDataBase() {
        MongoDatabase mongoDataBase = null;
        try {
            // 连接到数据库
            mongoDataBase = getMongoDataBase(getMongoClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongoDataBase;
    }

    public static void closeMongoClient(MongoDatabase mongoDataBase,
                                 MongoClient mongoClient) {
        if (mongoDataBase != null) {
            mongoDataBase = null;
        }
        if (mongoClient != null) {
            mongoClient.close();
        }
        logger.debug("CloseMongoClient successfully");
    }
}
