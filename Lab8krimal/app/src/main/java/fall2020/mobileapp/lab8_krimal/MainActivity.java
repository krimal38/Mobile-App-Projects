package fall2020.mobileapp.lab8_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ListView(this);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1);

        list.setAdapter(adapter);
        setContentView(list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentItem =  (String) ((TextView) view).getText();

                int firstIndex = currentItem.indexOf("(");
                int secondIndex = currentItem.indexOf(")");
               String latLon = "";
              latLon = currentItem.substring(firstIndex +1, secondIndex);


                Uri loc = Uri.parse("geo:0,0?q="+ latLon +"&z=3");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(loc);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);

            }
        } );


        new HttpGetTask().execute("http://mason.gmu.edu/~white/earthquakes.json");
    }


    private void onFinishGetRequest(String result) {
        try {
            JSONArray earthquakes = (new JSONArray(result));
            int len = earthquakes.length();
            for (int i = 0;i<len;i++) {
                JSONObject quake = earthquakes.getJSONObject(i);
                String region = quake.getString("region");
             //  String location = quake.getString("location");
                JSONObject location = quake.getJSONObject("location");
                String mag = ""; String occurred = "";
                String lat = ""; String longitude = "";
                mag = quake.getString("magnitude");
                lat = location.getString("latitude");
                longitude = location.getString("longitude");
                occurred = quake.getString("occurred_at");

                adapter.add(region + " (" + lat + "," + longitude +
                        ") with magnitude = " + mag + " on " + occurred);

            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    private class HttpGetTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuffer data = new StringBuffer();
            BufferedReader br = null;
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String rawData;
                while ((rawData = br.readLine()) != null) {
                    data.append(rawData);
                }
            } catch (MalformedURLException e1) {e1.printStackTrace();
            } catch (Exception e1) {e1.printStackTrace();
            } finally {
                if (br != null)
                    try {  br.close();
                    } catch (IOException e) {e.printStackTrace();}
            }
            return data.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            onFinishGetRequest(result);
        }
    }



}
