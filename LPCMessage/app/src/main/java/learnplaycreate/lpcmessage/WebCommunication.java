package learnplaycreate.lpcmessage;

import android.content.Context;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liam.c on 23/03/2015.
 */
public class WebCommunication {

    private Context C;
    public WebCommunication(Context c)
    {
        C = c;
    }


    public String GetJsonString()
    {
        String returnString = "";
        returnString = GetMessageFromWeb();
        return returnString;
    }
    public String GetMessageFromWeb() {
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        String WebMessage = "this is a test again";

        try {
            URL u = new URL(C.getString(R.string.base_url));
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(3000);
            c.setReadTimeout(3000);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        }

        return null;
    }
}
