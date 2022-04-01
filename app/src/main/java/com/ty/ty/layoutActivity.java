package com.ty.ty;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class layoutActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_two;
    private TextView textview ;
    private int [] textSizeArr = {10,20,25,30,40};
    int textsize =2 ;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast);
        init();
        findViewById(R.id.btn_one).setOnClickListener(this);
        textview = (TextView) findViewById(R.id.btn_one);



        btn_two = findViewById(R.id.button_two);

        btn_two.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Toast.makeText(layoutActivity.this,
                        "坏人，痒，不理你了😒",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        Button btn_dialog = findViewById(R.id.button_three);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CommonDialog dialog = new CommonDialog(layoutActivity.this);
                dialog.setTitle("WARNING");
                dialog.setMessage("您正在触发危险行为");
                dialog.setPositive("继续冲");
                dialog.setNegative("怂一下");
                dialog.setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {

                        Toast.makeText(layoutActivity.this,
                                "居然没骗到你",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegtiveClick() {
                        Toast.makeText(layoutActivity.this,
                                "哈哈，被我骗到了吧",Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("我要变形啦")
                .setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(new String[]{"Q版", "迷你", "一般般啦", "大了点", "好大哦哦OOO"},
                        textsize, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                textsize = i;
                            }
                        })
                .setPositiveButton("变身", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textview.setTextSize(textSizeArr[textsize]);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("想想哈", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("确定要走吗？？？等等嘛")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("看我可不可爱啊 嘻嘻 😆")
                .setPositiveButton("可爱哒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        layoutActivity.this.finish();
                    }
                })
                .setNegativeButton("丑拒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();

    }
}
