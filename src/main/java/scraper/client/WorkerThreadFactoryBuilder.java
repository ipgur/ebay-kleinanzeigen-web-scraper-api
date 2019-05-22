package scraper.client;/*
 * Copyright 2019 igur.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class WorkerThreadFactoryBuilder {

    private String namePrefix = null;
    private boolean daemon = false;
    private int priority = Thread.NORM_PRIORITY;

    public WorkerThreadFactoryBuilder name(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        namePrefix = name;
        return this;
    }

    public WorkerThreadFactoryBuilder daemon(boolean isDaemon) {
        daemon = isDaemon;
        return this;
    }

    public WorkerThreadFactoryBuilder priorty(int prio) {
        if (prio > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format("Thread prio (%s) must be <= %s",
                    prio, Thread.MAX_PRIORITY));
        }
        priority = prio;
        return this;
    }

    public ThreadFactory build() {
        return build(this);
    }

    private static ThreadFactory build(WorkerThreadFactoryBuilder builder) {
        final AtomicLong count = new AtomicLong();
        return r -> {
                Thread thread = new Thread(r);
                thread.setName(builder.namePrefix + "_" + count.getAndIncrement());
                thread.setDaemon(builder.daemon);
                thread.setPriority(builder.priority);
                return thread;
            };
    }

}
