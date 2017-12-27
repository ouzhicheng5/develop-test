package common.util.fdfs;


import common.csource.fastdfs.ClientGlobal;
import common.csource.fastdfs.TrackerClient;
import common.csource.fastdfs.TrackerServer;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static common.csource.fastdfs.ProtoCommon.activeTest;

public class ConnectionPool {

    private static ConnectionPool pool=new ConnectionPool();

/*    private static final Logger logger = LoggerFactory
            .getLogger(ConnectionPool.class);*/
    /**
     * 空闲的连接池
     */
    private LinkedBlockingQueue<TrackerServer> idleConnectionPool = null;
    /**
     * 连接池默认最小连接数
     */
    private long minPoolSize = 10;
    /**
     * 连接池默认最大连接数
     */
    private long maxPoolSize = 30;
    /**
     * 当前创建的连接数
     */
    private volatile long nowPoolSize = 0;
    /**
     * 默认等待时间（单位：秒）
     */
    private long waitTimes = 200;
    /**
     * fastdfs客户端创建连接默认1次
     */
    private static final int COUNT = 1;

    /**
     * 默认构造方法
     */
    private ConnectionPool() {
        try {
            ClientGlobal.init();
            /** 初始化连接池 */
            poolInit();
            /** 注册心跳 */
            HeartBeat beat = new HeartBeat(this);
            beat.beat();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void poolInit() {
        try {
            /** 初始化空闲连接池 */
            idleConnectionPool = new LinkedBlockingQueue<TrackerServer>();
            /** 往线程池中添加默认大小的线程 */
            for (int i = 0; i < minPoolSize; i++) {
                createTrackerServer(COUNT);
            }
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static ConnectionPool getPool(){
        return pool;
    }


    public void createTrackerServer(int flag) {
        TrackerServer trackerServer = null;
        try {

            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            while (trackerServer == null && flag < 5) {
                flag++;
                trackerServer = trackerClient.getConnection();
            }
            activeTest(trackerServer
                    .getSocket());
            idleConnectionPool.add(trackerServer);
            synchronized (this) {
                nowPoolSize++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public TrackerServer getConnection()  {
        TrackerServer trackerServer = idleConnectionPool.poll();
        if (trackerServer == null) {
            if (nowPoolSize < maxPoolSize) {
                createTrackerServer(COUNT);
                try {
                    trackerServer = idleConnectionPool.poll(waitTimes,
                            TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return trackerServer;

    }

    public void release(TrackerServer trackerServer) {
        if (trackerServer != null) {
            if (idleConnectionPool.size() < minPoolSize) {
                idleConnectionPool.add(trackerServer);
            } else {
                synchronized (this) {
                    if (nowPoolSize != 0) {
                        nowPoolSize--;
                    }
                }
            }
        }

    }



    public void drop(TrackerServer trackerServer) {
        if (trackerServer != null) {
            try {
                synchronized (this) {
                    if (nowPoolSize != 0) {
                        nowPoolSize--;
                    }
                }
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public LinkedBlockingQueue<TrackerServer> getIdleConnectionPool() {
        return idleConnectionPool;
    }

}
