package com.oldking.user.request;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzhiyong
 */
@Slf4j
public class DelayTask implements Delayed {
    private final String taskContent;

    private final Long triggerTime;

    public DelayTask(String taskContent, Long delayTime) {
        this.taskContent = taskContent;
        this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null) {
            return -1;
        }
        return this.triggerTime.compareTo(((DelayTask) o).triggerTime);
    }

    public static void main(String[] args) {
        DelayQueue<DelayTask> delayTasks = new DelayQueue<>();
        new Thread(() -> {
            while (true) {
                try {
                    DelayTask take = delayTasks.take();
                    log.info(LocalDateTime.now() + take.taskContent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        delayTasks.offer(new DelayTask("1", 3L));
        delayTasks.offer(new DelayTask("3", 9L));
        delayTasks.offer(new DelayTask("2", 5L));
    }
}
