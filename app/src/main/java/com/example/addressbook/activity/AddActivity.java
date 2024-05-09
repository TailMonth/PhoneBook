package com.example.addressbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.addressbook.MainActivity;
import com.example.addressbook.R;
import com.example.addressbook.bean.PeoBean;
import com.example.addressbook.dao.PeoDao;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        TextView name = findViewById(R.id.add_name);
        TextView tel = findViewById(R.id.add_phone);
        RadioButton man = findViewById(R.id.add_man);
        RadioButton woman = findViewById(R.id.add_woman);
        man.setChecked(true);
        TextView remark = findViewById(R.id.add_remark);


        Button change = findViewById(R.id.add_button);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否为空
                String nameT = name.getText().toString().trim();
                String telT = tel.getText().toString().trim();
                String remarkT = remark.getText().toString().trim();

                if (nameT.isEmpty()){
                    Toast.makeText(AddActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
                }else if (telT.isEmpty()){
                    Toast.makeText(AddActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }else if (remarkT.isEmpty()){
                    Toast.makeText(AddActivity.this,"请输入备注",Toast.LENGTH_SHORT).show();
                }else {
                    String sex = "女";
                    if (man.isChecked()){
                        sex = "男";
                    }
                    PeoDao.savePeo(nameT,telT,sex,remarkT);
                    Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent1);
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_add);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}