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
import java.util.*;

public class Level implements Serializable {

    private static final Map<String, Integer> valid = createLevels();
    private static Map<String, Integer> createLevels() {
        Map<String, Integer> levels = new HashMap<String, Integer>();
        levels.put("DEBUG", 1);
        levels.put("INFO", 2);
        levels.put("WARN", 3);
        levels.put("ERROR", 4);
        levels.put("OFF", 5);
        return levels;
    }

    private String level;
    public int verbose;

    public static final Level Off = new Level("OFF");
    public static final Level Debug = new Level("DEBUG");
    public static final Level Info = new Level("INFO");
    public static final Level Warn = new Level("WARN");
    public static final Level Error = new Level("ERROR");

    public Level() {
        level = "DEBUG";
    }

    private Level(String lvl) {

        if (lvl == null) {
            this.level = "DEBUG";
            this.verbose = 1;
        } else if (valid.containsKey(lvl)) {
            this.level = lvl;
            this.verbose = valid.get(lvl);
        } else {
            this.level = "DEBUG";
            this.verbose = 1;
        }

    }


}
