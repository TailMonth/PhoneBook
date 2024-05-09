package com.example.addressbook.activity;

import static com.example.addressbook.MainActivity.permission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.Manifest;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.addressbook.MainActivity;
import com.example.addressbook.R;
import com.example.addressbook.bean.PeoBean;
import com.example.addressbook.dao.PeoDao;
import com.example.addressbook.until.DBUntil;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String id = getIntent().getStringExtra("id");
        TextView num = findViewById(R.id.de_phone);

        Button call_phone = findViewById(R.id.de_call);
        call_phone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int flag = ContextCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE);
                if(flag == PackageManager.PERMISSION_GRANTED){
                    //CALL
                    Log.d("AAA","true");
                    makePhoneCall(num.getText().toString().trim());
                }else {
                    ActivityCompat.requestPermissions(DetailsActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                    Log.d("AAA","false");
                }
            }
        });

        Button opean_message = findViewById(R.id.de_message);
        opean_message.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                //设置URI，指定短信的“smsto”:"协议
                Uri uri = Uri.parse("smsto:"+Uri.encode(num.getText().toString().trim()));
                intent.setData(uri);
                startActivity(intent);
            }
        });
        //返回功能
        Button back = findViewById(R.id.de_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button del = findViewById(R.id.de_del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeoDao.delPeo(id);
                Toast.makeText(DetailsActivity.this,"删除成果",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button update = findViewById(R.id.de_up);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,UpdateActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        //详情功能
        PeoBean peo =  PeoDao.getOnePeo(id);
        /*根据性别设置头像*/
        ImageView head =  findViewById(R.id.de_head);
        if (peo.getSex().equals("男")){
            head.setImageResource(R.drawable.peo_man);
        }else {
            head.setImageResource(R.drawable.peo_woman);
        }
        /*设置名字*/
        TextView name = findViewById(R.id.de_name);
        name.setText(peo.getName());
        /*设置联系方式*/
        TextView tel = findViewById(R.id.de_phone);
        tel.setText(peo.getNum());
        /*设置性别*/
        TextView sex = findViewById(R.id.de_sex);
        sex.setText("性别："+peo.getSex());
        /*设置备注*/
        TextView remark = findViewById(R.id.de_remark);
        remark.setText(peo.getRemark());

    }



    private void makePhoneCall(String num){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+num));
        startActivity(callIntent);
    }
}
