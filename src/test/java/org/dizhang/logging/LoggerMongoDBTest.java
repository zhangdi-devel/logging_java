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

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoggerMongoDBTest {

    /*this test should be run on a server with a default mongodb running*/

    private static LoggerMongoDB loggerMongoDB;

    @BeforeClass
    public static void setUpClass() {
        loggerMongoDB = new LoggerMongoDB("test");
    }

    @Test
    public void flashToMongo() throws Exception {
        long before = loggerMongoDB.flashed();
        int num = 2 * loggerMongoDB.limit;
        while (num-- > 0) {
            loggerMongoDB.info(String.format("msg: %d\n", num));
        }
        long after = loggerMongoDB.flashed();
        assertEquals(before + 2 * loggerMongoDB.limit, after);
    }

    @Test
    public void noLoggingWhenThresholdIsHigher() throws Exception {
        long before = loggerMongoDB.flashed();
        loggerMongoDB.setLevel(Level.Error);
        int num = 2001;
        while (num-- > 0) {
            loggerMongoDB.debug("whatever");
        }
        long after = loggerMongoDB.flashed();
        loggerMongoDB.setLevel(Level.Debug);
        assertEquals(before, after);
    }
}
