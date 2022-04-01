package com.ty.ty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Main_activity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup radioGroup;
    private TextView textView;
    private CharSequence[] item = new CharSequence[]{
            "好看点","漂亮点","离我远一点","温柔点"
    };
    private boolean[] checkdItems = new  boolean[]{
            false,false,false,false
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.radiobutton);
        findViewById(R.id.btn_mc).setOnClickListener(this);


        radioGroup = findViewById(R.id.rdg);
        textView = findViewById(R.id.tv);
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if(i ==  R.id.rbtn){
                            textView.setText("您的性别是男");
                        }
                        else
                        {
                            textView.setText("您的性别是女");
                        }
                    }
                }
        );
    }

    @Override
    public void onClick(View view) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("选第三个打死你 哼哼")
                .setIcon(R.mipmap.ic_launcher)
                .setMultiChoiceItems(item, checkdItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        checkdItems[i] = b ;}
                })
                .setPositiveButton("我想好了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for(int x = 0 ; x<checkdItems.length;x++){

                            if (checkdItems[x]){
                                stringBuffer.append(item[x]).append(" ");
                            }

                        }
                        if(stringBuffer != null) {
                            if (stringBuffer.toString().contains("离我远一点")) {
                                Toast.makeText(Main_activity.this, "你想挨打吗 😠😠", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(Main_activity.this, ""+ stringBuffer, Toast.LENGTH_LONG).show();
                            }

                        }
                        dialogInterface.dismiss();




                    }
                })
                .setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
}