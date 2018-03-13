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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class LoggerBase implements Serializable {

    public String name;

    public Level threshold;

    protected int limit;

    protected List<Record> buffer;

    protected abstract void flash() throws Exception;

    private void addRecord(Record r) throws Exception {
        /*
         * if the level of the record is less than the threshold
         * don't add it to buffer
         *
         * */
        if (r.level.verbose < threshold.verbose) {
            return;
        }
        buffer.add(r);
        if (buffer.size() >= limit) {
            flash();
        }
    }

    public void setLevel(Level newLevel) {
        this.threshold = newLevel;
    }

    public void debug(String msg) throws Exception {
        Record cur = new Record(name, new Date(), Level.Debug, msg);
        addRecord(cur);
    }

    public void info(String msg) throws Exception {
        Record cur = new Record(name, new Date(), Level.Info, msg);
        addRecord(cur);
    }

    public void warn(String msg) throws Exception {
        Record cur = new Record(name, new Date(), Level.Warn, msg);
        addRecord(cur);
    }

    public void error(String msg) throws Exception {
        Record cur = new Record(name, new Date(), Level.Error, msg);
        addRecord(cur);
    }


}