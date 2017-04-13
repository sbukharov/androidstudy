package a00668095.comp3717.bcit.ca.lab7;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView label;
    static int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {
        input = (EditText)findViewById(R.id.editText);
        label = (TextView)findViewById(R.id.label);
    }

    public void startTimer(View view) {
        new MyTaskClass().execute(Integer.parseInt(input.getText().toString()));
    }


    private class MyTaskClass extends AsyncTask<Integer, String, String> {
        /**
         * Before starting background do some work.
         * */
        @Override
        protected void onPreExecute() {
            val = 0;
        }

        @Override
        protected String doInBackground(Integer... params) {
            try {
                for (int i = 0; i<params[0]; i++) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            String text = "";
                            text+=label.getText().toString();
                            label.setText(text +", "+ (val + 1));
                            val++;
                        }
                    });
                }
            } catch (Exception e) {
            }

            return null;
        }

        /**
         * Update list ui after process finished.
         */
        protected void onPostExecute(String file_url) {
        }
    };
}


