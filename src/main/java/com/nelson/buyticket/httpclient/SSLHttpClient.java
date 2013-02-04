package com.nelson.buyticket.httpclient;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.*;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

/**
 *
 * @author Administrator
 */
public class SSLHttpClient {

    private static DefaultHttpClient hc;

    public static DefaultHttpClient getSSLHttpClient() {
        if (hc == null) {
            DefaultHttpClient base = new DefaultHttpClient();
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                X509TrustManager tm = new X509TrustManager() {

                    @Override
                    public void checkClientTrusted(X509Certificate[] xcs,
                            String string) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] xcs,
                            String string) throws CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                ctx.init(null, new TrustManager[]{tm}, null);
                SSLSocketFactory ssf = new SSLSocketFactory(ctx);
                ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                ClientConnectionManager ccm = base.getConnectionManager();
                SchemeRegistry sr = ccm.getSchemeRegistry();
                sr.register(new Scheme("https", ssf, 443));
                hc = new DefaultHttpClient(ccm, base.getParams());
                hc.addRequestInterceptor(new HttpRequestInterceptor() {

                    @Override
                    public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
                        if (!request.containsHeader("Accept-Encoding")) {
                            request.addHeader("Accept-Encoding", "gzip");
                        }
                    }
                });
                hc.addResponseInterceptor(new HttpResponseInterceptor() {

                    @Override
                    public void process(final HttpResponse response,final HttpContext context) throws HttpException, IOException {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            Header ceheader = entity.getContentEncoding();
                            if (ceheader != null) {
                                HeaderElement[] codecs = ceheader.getElements();
                                for (int i = 0; i < codecs.length; i++) {
                                    if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                        response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                                        return;
                                    }
                                }
                            }
                        }
                    }
                });
                HttpHost proxy = new HttpHost("127.0.0.1", 8087, "http");
                hc.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            } catch (Exception ex) {
                return null;
            }
        }
        return hc;

    }
}
