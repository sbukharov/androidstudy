package a00668095.comp3717.bcit.ca.assignment2bukharov;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Lorgus on 2017-03-30.
 */

public class DataRetriever extends AsyncTask<Void, Void, JSONArray>
{
    private static WeakReference<Activity> mActivityRef;

    public static void updateActivity(Activity activity) {
        mActivityRef = new WeakReference<Activity>(activity);
    }

    @Override
    protected JSONArray doInBackground(Void... params)
    {

        String str="http://max.bcit.ca/comp.json";
        //String str="http://karldiab.com/karl/empty.json";
        //http://karldiab.com/karl/empty.json
        URLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        try
        {
            URL url = new URL(str);
            urlConn = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }
            JSONArray jArr = new JSONArray(stringBuffer.toString());
            //JSONObject jObj = new JSONObject(stringBuffer.toString());
            //return jArr.getJSONObject(0);
            return jArr;
        }
        catch(Exception ex)
        {
            Log.e("App", "yourDataTask", ex);
            String msg = ex.getLocalizedMessage();
            JSONArray jArr = null;

            try {
                JSONObject jo = new JSONObject();
                jo.put("error", msg);
                JSONArray ja = new JSONArray();
                ja.put(jo);
                jArr = ja;
            } catch (JSONException jsonEx) {

            } finally {
                return jArr;
            }
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPostExecute(JSONArray response)
    {
        if(response != null)
        {
            try {
                Log.e("App", "Success: ");
                Assignment2.resultArray = response;
                Assignment2.returned = true;

                if (response.toString().contains("\"error\"")) {
                    ((TextView)Assignment2.resultCheck).setText(response.getJSONObject(0).getString("error"));
                } else if (response.length() == 0) {
                    ((TextView)Assignment2.resultCheck).setText("Empty JSON array returned.");
                } else {
                    ((Assignment2) mActivityRef.get()).populateList();
                    ((TextView)Assignment2.resultCheck).setText("");
                }
            } catch (Exception ex) {
                Log.e("App", "Failure", ex);
            }
        }
    }
}