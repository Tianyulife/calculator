package com.ty.ty;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  implements
        CompoundButton.OnCheckedChangeListener{
    private TextView hobby;
    private String hobbys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);

        CheckBox shuttlecock = findViewById(R.id.like_shuttlecock);
        CheckBox basketball = findViewById(R.id.like_basketball);
        CheckBox pingpang = findViewById(R.id.like_pingpang);
        shuttlecock.setOnCheckedChangeListener(this);
        pingpang.setOnCheckedChangeListener(this);
        basketball.setOnCheckedChangeListener(this);

        hobby = (TextView) findViewById(R.id.hobby);
        hobbys = new String();

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String motion = compoundButton.getText().toString();
        if (b){
            if (!hobbys.contains(motion)){
                hobbys = hobbys + motion;
                hobby.setText(hobbys);
            }
        }
        else {
            if(hobbys.contains(motion)){
                hobbys = hobbys.replace(motion,"");
                hobby.setText(hobbys);
            }
        }
    }
}
