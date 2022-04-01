package com.ty.ty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class CommonDialog extends AlertDialog {
    private TextView titleTv;
    private TextView messageTv;
    private Button negtiveBn , positiveBn;

    protected CommonDialog(@NonNull Context context) {
        super(context);
    }
    private String message;
    private String title;
    private String positive,negative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        initView();
        initEvent();

    }
    private void initView(){
        negtiveBn = findViewById(R.id.negative);
        positiveBn = findViewById(R.id.positive);
        titleTv = findViewById(R.id.title);
        messageTv = findViewById(R.id.message);


    }
    private void refreshView(){

        if (!TextUtils.isEmpty(title)){
            titleTv.setText(title);
            titleTv.setVisibility(View.VISIBLE);
        }else {
            titleTv.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(message)){

            messageTv.setText(message);


        }
        if (!TextUtils.isEmpty(positive)){
            positiveBn.setText(positive);

        }
        else {
            positiveBn.setText("OK");

        }
        if (!TextUtils.isEmpty(negative)){
            negtiveBn.setText(negative);

        }
        else {
            negtiveBn.setText("No");

        }


    }
    private void initEvent(){

        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickBottomListener!=null){
                    onClickBottomListener.onPositiveClick();
                }

            }
        });
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickBottomListener!=null){
                    onClickBottomListener.onNegtiveClick();
                }
            }
        });

    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    public interface OnClickBottomListener{
        void onPositiveClick();
        void onNegtiveClick();
    }
    public OnClickBottomListener onClickBottomListener;
    public CommonDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener){
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public CommonDialog setMessage (String message){
        this.message = message;
        return this;
    }
    public CommonDialog setPositive(String positive) {
        this.positive = positive;
        return this;
    }
    public CommonDialog setNegative(String negative){
        this.negative = negative;
        return  this;
    }
}
