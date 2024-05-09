package com.example.addressbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.addressbook.MainActivity;
import com.example.addressbook.R;
import com.example.addressbook.bean.PeoBean;
import com.example.addressbook.dao.PeoDao;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        PeoBean peo =  PeoDao.getOnePeo(id);//获取联系人

        TextView name = findViewById(R.id.up_name);
        TextView tel = findViewById(R.id.up_phone);
        RadioButton man = findViewById(R.id.man);
        RadioButton woman = findViewById(R.id.woman);
        TextView remark = findViewById(R.id.up_remark);

        name.setText(peo.getName());
        tel.setText(peo.getNum());
        if(peo.getSex().equals("男")){
            man.setChecked(true);
        }else {
            woman.setChecked(true);
        }
        remark.setText(peo.getRemark());

        Button change = findViewById(R.id.up_button);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否为空
                String nameT = name.getText().toString().trim();
                String telT = tel.getText().toString().trim();
                String remarkT = remark.getText().toString().trim();

                if (nameT.isEmpty()){
                    Toast.makeText(UpdateActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
                }else if (telT.isEmpty()){
                    Toast.makeText(UpdateActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }else if (remarkT.isEmpty()){
                    Toast.makeText(UpdateActivity.this,"请输入备注",Toast.LENGTH_SHORT).show();
                }else {
                    String sex = "女";
                    if (man.isChecked()){
                        sex = "男";
                    }
                    PeoDao.updatePeo(nameT,telT,sex,remarkT,id);
                    Toast.makeText(UpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent1);
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_up);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}