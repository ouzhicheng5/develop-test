package common.util.fdfs;

import common.csource.fastdfs.TrackerServer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class HeartBeat {
    private ConnectionPool pool = null;

    public static int oneHour = 1000 * 60 * 60 * 1;

    public static int waitTimes = 200;

    public HeartBeat(ConnectionPool pool) {
        this.pool = pool;
    }

    /**
     *
     * @Description: 定时执行任务，检测当前的空闲连接是否可用，如果不可用将从连接池中移除
     *
     */
    public void beat() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LinkedBlockingQueue<TrackerServer> idleConnectionPool = pool
                        .getIdleConnectionPool();
                TrackerServer ts = null;
                for (int i = 0; i < idleConnectionPool.size(); i++) {
                    try {
                        ts = idleConnectionPool.poll(waitTimes,
                                TimeUnit.SECONDS);
                        if (ts != null) {
                            common.csource.fastdfs.ProtoCommon.activeTest(ts
                                    .getSocket());
                            idleConnectionPool.add(ts);
                        } else {
                            /** 代表已经没有空闲长连接 */
                            break;
                        }
                    } catch (Exception e) {
                        /** 发生异常,要删除，进行重建 */
                        pool.drop(ts);
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, oneHour, oneHour);
    }
}
