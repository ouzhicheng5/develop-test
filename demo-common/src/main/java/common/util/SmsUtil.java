package common.util;


import common.common.constant.Constant;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

//import org.apache.http.conn.ssl.X509HostnameVerifier;


/**
 * @Author Froid_Li
 * @Email 269504518@qq.com
 * @Date 2017/8/29  10:46
 */


/**
 * Created by hp on 2017/5/13.
 */
public class SmsUtil {

    public static final Logger log = LoggerFactory.getLogger(SmsUtil.class);

    public static final int CONN_TIMEOUT = 50000;

    public static final int READ_TIMEOUT = 50000;

    public static final String CHARSET = "UTF-8";

    private static HttpClient client = null;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static StringBuilder vCodecontent(String verifyCode) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("【成都农贷通】您的验证码为:")
                .append(verifyCode)
                .append("，为保证您的账号安全，请勿向任何人提供此验证码。感谢您使用成都农贷通！");
        return contentBuilder;
    }

    //TODO to be completed
    public static StringBuilder smscontent(String content) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("【成都农贷通】您的验证码为:")
                .append(content)
                .append("，为保证您的账号安全，请勿向任何人提供此验证码。感谢您使用成都农贷通！");

        return contentBuilder;
    }

    public static Boolean sendSms(List<String> phones, StringBuilder content) throws Exception {
        for (String phone : phones) {
            StringBuilder url = new StringBuilder();
            url.append(Constant.SMS_URL).append("&Mobile=").append(phone).append("&Content=").append(URLEncoder.encode(content.toString(), "GBK"));
            get(url.toString(), CHARSET, CONN_TIMEOUT, READ_TIMEOUT);

        }
        return true;
    }

//    public static void main(String[] args) throws Exception {
//        List<String> list = new ArrayList<>();
//        list.add("13568968462");
//        list.add("15681868757");
//        sendSms(list, Smscontent("22222"));
//    }

    public static String get(String url, String charset, Integer connTimeout, Integer readTimeout)
            throws Exception {

        HttpClient client = null;
        HttpGet get = new HttpGet(url);
        String result;
        try {
            // 设置参数
            RequestConfig.Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            get.setConfig(customReqConf.build());

            HttpResponse res;

            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
                res = client.execute(get);
            } else {
                // 执行 Http 请求.
                client = SmsUtil.client;
                res = client.execute(get);
            }

            result = IOUtils.toString(res.getEntity().getContent(), charset);
            System.out.println("result:" + result);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            get.releaseConnection();
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }

    /**
     * 创建 SSL连接
     *
     * @return
     * @throws GeneralSecurityException
     */
    private static CloseableHttpClient createSSLInsecureClient()
            throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }


            });

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (GeneralSecurityException e) {
            return null;
        }
    }
}
