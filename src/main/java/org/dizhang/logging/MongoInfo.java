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

public class MongoInfo {
    public String host;
    public int port;
    public String db;
    public String collection;

    public MongoInfo(String h, int p, String d, String c) {
        this.host = h;
        this.port = p;
        this.db = d;
        this.collection = c;
    }

    public MongoInfo() {
        this("localhost", 27017, "logdb", "logs");
    }

    @Override
    public String toString() {
        return String.format("host: %s\nport: %d\ndb: %s\ncollection: %s\n", host, port, db, collection);
    }
}
