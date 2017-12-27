package common.util;

import common.*;
import common.csource.common.MyException;
import common.csource.common.NameValuePair;
import common.csource.fastdfs.StorageClient1;
import common.csource.fastdfs.StorageServer;
import common.csource.fastdfs.TrackerServer;
import common.util.fdfs.ConnectionPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class FastDfsUtil {

    private static final String SEP = "______";

    public static String upload(byte[] fileBytes, String fileName) throws IOException, MyException {
        ConnectionPool pool = ConnectionPool.getPool();
        String extFileName = "";
        if (fileName.contains(".")) {
            extFileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            extFileName = "";
        }
        NameValuePair[] metaInfo = new NameValuePair[]{new NameValuePair("filename", fileName)};
        TrackerServer trackerServer = pool.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        String[] results = storageClient1.upload_file(fileBytes, extFileName, metaInfo);
        pool.release(trackerServer);
        return StringUtil.mkString(Arrays.asList(results), SEP);
    }

    public static byte[] download(String dfsPath) throws Exception {
        if (StringUtil.isEmpty(dfsPath) || !dfsPath.contains(SEP)) {
            throw new Exception("dfsPath :" + dfsPath + "is illegal");
        }
        ConnectionPool pool = ConnectionPool.getPool();
        TrackerServer trackerServer = pool.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        String[] pathPieces = dfsPath.split(SEP);
        byte[] bytes = storageClient1.download_file(pathPieces[0], pathPieces[1]);
        pool.release(trackerServer);
        return bytes;
    }

    public static void deleteFiles(Collection<String> filePathList) throws IOException, MyException {
        ConnectionPool pool = ConnectionPool.getPool();
        TrackerServer trackerServer = pool.getConnection();
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        for(String filePath:filePathList){
            if(StringUtil.isEmpty(filePath) || !filePath.contains(SEP)){
                continue;
            }
            String[] pathPieces = filePath.split(SEP);
            storageClient1.delete_file(pathPieces[0],pathPieces[1]);
        }
        pool.release(trackerServer);
    }
}
