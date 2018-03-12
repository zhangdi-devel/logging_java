/*
 *    Copyright 2018 Zhang Di
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.dizhang.logging;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class LoggerMongoDB extends LoggerBase {


    private MongoCollection<Record> collection;


    public LoggerMongoDB(String name, Level level, int bufferSize, MongoInfo mongoInfo) {

        this.name = name;
        this.threshold = level;
        this.limit = bufferSize;

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient(mongoInfo.host, mongoInfo.port);

        MongoDatabase database = mongoClient.getDatabase(mongoInfo.db).withCodecRegistry(pojoCodecRegistry);

        this.collection = database.getCollection(mongoInfo.collection, Record.class);

        this.buffer = new ArrayList<Record>();

    }

    public LoggerMongoDB(String name, MongoInfo mongoInfo) {
        new LoggerMongoDB(name, Level.Debug, 1000, mongoInfo);
    }


    public LoggerMongoDB(String name) {
        new LoggerMongoDB(name, new MongoInfo());
    }

    protected void flash() {
        this.collection.insertMany(new ArrayList<Record>(this.buffer));
        this.buffer = new ArrayList<Record>();
    }

}
