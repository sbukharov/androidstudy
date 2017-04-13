package a00668095.comp3717.bcit.ca.assignment2bukharov;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static a00668095.comp3717.bcit.ca.assignment2bukharov.DataRetriever.updateActivity;

public class Assignment2 extends AppCompatActivity {

    public static TextView resultCheck;
    public static boolean returned = false;
    public static JSONArray resultArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);
        DataRetriever dr = new DataRetriever();

        DataRetriever.updateActivity(this);
        resultCheck = (TextView)findViewById(R.id.defaulttext);
        dr.execute();

        /*
        Intent intent = new Intent(getBaseContext(), SignoutActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", sessionId);
        startActivity(intent);
        */
    }

    public void populateList(){
        try
        {
            JSONArray jsonArray = resultArray;
            int length = jsonArray.length();
            List<String> listContents = new ArrayList<String>(length);
            for (int i = 0; i < length; i++)
            {
                JSONObject jObj = jsonArray.getJSONObject(i);
                listContents.add(jObj.getString("name"));
            }

            ListView myListView = (ListView) findViewById(R.id.my_list);
            myListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listContents));

            setListListener(myListView);
        }
        catch (Exception e)
        {
            // this is just an example
        }
    }

    public void setListListener(final ListView listView){
        listView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                String selectedFromList =(String) (listView.getItemAtPosition(position));
                Intent intent = new Intent(getBaseContext(), WebSelection.class);
                intent.putExtra("position", position);
                intent.putExtra("value", selectedFromList);
                String url = "ERROR RETRIEVING URL";
                try {
                    url = resultArray.getJSONObject(position).getString("url");
                } catch(Exception ex) {
                } finally {
                    intent.putExtra("url", url);
                }
                startActivity(intent);
            }
        });
    }

}
