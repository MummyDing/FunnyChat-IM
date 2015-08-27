package Tookit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.security.MessageDigest;
public class PostData{
    public String sha1(String data){
        StringBuffer buf = new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for(int i = 0 ; i < bits.length;i++){
                int a = bits[i];
                if(a<0) a+=256;
                if(a<16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        }catch(Exception e){
            
        }
        return buf.toString();
    }
    public String getData(String url,List<NameValuePair> params){
        StringBuffer res = new StringBuffer();
        String App_Key = "lmxuhwagxyc8d";
        String App_Secret="wbDCDbTHmM";
        String Timestamp = System.currentTimeMillis()/1000+"";
        String Sign = sha1(App_Secret+Timestamp+Timestamp);
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("RC-App-Key",App_Key);
            httpPost.setHeader("RC-Timestamp",Timestamp);
            httpPost.setHeader("RC-Nonce",Timestamp);
            httpPost.setHeader("RC-Signature",Sign);

            httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String line = null;
            while((line = br.readLine())!=null){
                res.append(line);
            }
        }catch(Exception e){
           return  e.toString();
        }
        return res.toString();
    }
}
