package com.example.httppostasync_sample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpPostAsyncTaskTest extends AsyncTask<Void, Void, String> {


    private HashMap<String, String> nameValue;
//    public ProgressDialog mDialog;
    private URL url;
    private String receveSTR = "null";
    private CallBack callBack;
    private Context context;
    private HttpURLConnection http;

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }


    @Override
    protected void onPostExecute(String s) {
        Log.d("ttt","onpost...");
        if (context != null && !((Activity) context).isFinishing()) {
            //mProgressDialog.dismiss();
//            mDialog.dismiss();

        } callBack.run(receveSTR);
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        Log.d("ttt","onpre....");
        if (context != null && !((Activity) context).isFinishing()) {
          /*  mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();*/
//            mDialog = MyProgressDialog.show(context, "", "", true, true, null);
        }
        super.onPreExecute();
    }

    public HttpPostAsyncTaskTest(HashMap<String, String> namevalue, String tmp_url, CallBack callBack, Context context) {
        try {
            this.context = context;
            url = new URL(tmp_url);
            // Log.i("response", tmp_url);
            nameValue = namevalue;
            this.callBack = callBack;
            if (namevalue != null) {
                Log.d("ttt","생성자..");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d("ttt","doinbackgrond");
        try {
            //mHandler.sendEmptyMessage(0);
            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            //     Log.i("response", "now url  " + url);
            http = null;
            if (url.getProtocol().toLowerCase().equals("https")) { //https 연결
                Log.d("ttt","inHttps");
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
                https.setHostnameVerifier(DO_NOT_VERIFY);
                http = https;
            } else {
                Log.d("ttt","inHttp");
                http = (HttpURLConnection) url.openConnection();
            }
            //--------------------------
            //   전송 모드 설정 - 기본적인 설정이다
            //--------------------------
            http.setDefaultUseCaches(false);
            http.setDoInput(true);                         // 서버에서 읽기 모드 지정
            http.setDoOutput(true);                        // 서버로 쓰기 모드 지정
            http.setRequestMethod("POST");                // 전송 방식은 POST

            try {
                http.setConnectTimeout(15000); //접근 시간 제한 15초
                http.setReadTimeout(15000); //서버 로직 시간 제한 15초

            }catch (NullPointerException e){
                // url 리소스를 얻어오는 경우 위 if문에서 사용하는 데이터들이 nullpointerror가 나기때문에 에러처리
                Log.d("HTTP_LOG","FROM SplashActivity Process");
            }

            //인증
//            String baseAuthStr = "Mobile" + ":" + "parkland!Q@W142";
//            http.setRequestProperty("Authorization", "Basic " + Base64.encodeToString(baseAuthStr.getBytes(), 0));

            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            http.setRequestProperty("Connection", "Keep-Alive");

            //--------------------------
            //   서버로 값 전송
            //--------------------------
            StringBuffer buffer = new StringBuffer();
            // id, phonenum, devicenum 에 대한 정보를 보냄
            if (nameValue != null) {
                Set key = nameValue.keySet();
                for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                    String keyName = (String) iterator.next();
                    String valueName = (String) nameValue.get(keyName);
                    buffer.append(keyName).append("=").append(valueName).append("&");
                }
                buffer.deleteCharAt(buffer.length() - 1); //원하는 위치 문자 제거 , 마지막 &을 없애기 위해서
                Log.d("HttpPostAsync", "buffer:" + buffer);
            }

            Log.d("ttt","hmm.");

            try {
                OutputStream os = http.getOutputStream();
            } catch (NetworkOnMainThreadException e) {
                e.printStackTrace();
                Log.d("ttt","NetworkOnMainThreadException.");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("ttt","IOException.");
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("ttt","Exception.");
            }


            Log.d("ttt","hmm.. ");
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            //            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
            Log.d("ttt","hmm............");

            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            buffer.delete(0, buffer.length());
            writer.flush(); //stream에 남아 있는 데이터를 강제로 내보내는 역할을 함



            //--------------------------
            //   서버에서 전송받기
            //--------------------------
//             Log.i("receive !!", "!! " +receveSTR + " , value size : "+ nameValue.size());
            Log.d("ttt","receive");
            int status = http.getResponseCode();

            InputStream in;
            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {
                in = http.getErrorStream();
                Log.e("Bad ", "Bad request " + in.toString() + "    " + in);

            } else {
                in = http.getInputStream();

                InputStreamReader tmp = new InputStreamReader(in, "EUC-KR");
//                InputStreamReader tmp = new InputStreamReader(in, "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                String str;

                Log.d("ttt","inputstream");
                while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                    if (str.toString().indexOf("<") != 1 || !str.toString().isEmpty()) {

                        //태그가 아니고, 빈값이 아니라면
                        receveSTR = str;

                        // 에러메시지 잘라서 리턴!
                        if(receveSTR.indexOf("message") > 0){
                            int a = receveSTR.indexOf("message: ");
                            int b = receveSTR.indexOf(" (severity");
                            receveSTR = receveSTR.substring(a+9, b);
                            return receveSTR;
                        }

                    }
                }
                //   Log.i("receveS11111111TR ", receveSTR);
            }
            http.disconnect();
            writer.close();
            outStream.close();
        } catch (MalformedURLException e) {
            http.disconnect();
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            Log.d("timeout", "timeout");
            receveSTR = "timeout";
            http.disconnect();
        } catch (IOException e) {
            //
            e.printStackTrace();
            http.disconnect();
        }
        Log.d("ttt","..the..end..");
        return receveSTR;
    }

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
