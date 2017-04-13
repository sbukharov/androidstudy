package a00668095.comp3717.bcit.ca.lab8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class lab9 extends AppCompatActivity {

    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab9);

        init();
    }


    private void init() {
        imgView = (ImageView)findViewById(R.id.imageView);
    }

    public void flipImage(View view){
        float scale = imgView.getScaleY();
        if (scale>0) {
            imgView.setScaleY(-1);
        } else {
            imgView.setScaleY(1);
        }
    }

}
