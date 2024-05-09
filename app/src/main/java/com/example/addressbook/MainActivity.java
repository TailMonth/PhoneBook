package com.example.addressbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.addressbook.activity.AddActivity;
import com.example.addressbook.activity.DetailsActivity;
import com.example.addressbook.activity.UpdateActivity;
import com.example.addressbook.adapter.PeoAdapter;
import com.example.addressbook.bean.PeoBean;
import com.example.addressbook.dao.PeoDao;
import com.example.addressbook.until.DBUntil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Object permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        //toolbar显示

        //数据库调用
        DBUntil dbUntil = new DBUntil(MainActivity.this);
        dbUntil.db = dbUntil.getWritableDatabase();

//        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
//        intent.putExtra("id","2");
//        startActivity(intent);

        //加载列表
        List<PeoBean> result = PeoDao.getALLPeo();
        ListView listView =  findViewById(R.id.booklist);
        if(result.isEmpty()){
            listView.setAdapter(null);//页面不显示
        }else {
            //运用比较器依照首字母对list排序
            result.sort(new Comparator<PeoBean>() {
                @Override
                public int compare(PeoBean o1, PeoBean o2) {
                    if(o1.getBeginZ().equals("#") || o2.getBeginZ().equals("#")){
                        return 1;
                    }else {
                        return o1.getBeginZ().compareTo(o2.getBeginZ());
                    }
                }
            });

            PeoAdapter peoAdapter = new PeoAdapter(MainActivity.this,result);
            listView.setAdapter(peoAdapter);
        }

        FloatingActionButton floatingActionButton = findViewById(R.id.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开添加界面
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        //搜索框内有内容就会执行搜索操作
        EditText id = findViewById(R.id.search_id);
        id.setOnTouchListener((v, event) -> {
            listView.setAdapter(null);
            String title = id.getText().toString();
            List<PeoBean> temp;
            if (title.isEmpty()) {
                temp = PeoDao.getALLPeo();
            } else {
                temp = PeoDao.getALLPeo(title);
            }
            // 运用比较器依照首字母对list排序
            temp.sort((o1, o2) -> {
                if (o1.getBeginZ().equals("#") || o2.getBeginZ().equals("#")) {
                    return 1;
                } else {
                    return o1.getBeginZ().compareTo(o2.getBeginZ());
                }
            });
            PeoAdapter peoAdapter = new PeoAdapter(MainActivity.this, temp);
            listView.setAdapter(peoAdapter);
            // 调用 performClick
            if (event.getAction() == MotionEvent.ACTION_UP) {
                v.performClick();
            }
            return false;
        });


        id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listView.setAdapter(null);
                String title = id.getText().toString();
                List<PeoBean> temp = null;
                if (title.isEmpty()) {
                    temp = PeoDao.getALLPeo();
                } else {
                    temp = PeoDao.getALLPeo(title);
                }
                //运用比较器依照首字母对list排序
                temp.sort(new Comparator<PeoBean>() {
                    @Override
                    public int compare(PeoBean o1, PeoBean o2) {
                        if (o1.getBeginZ().equals("#") || o2.getBeginZ().equals("#")) {
                            return 1;
                        } else {
                            return o1.getBeginZ().compareTo(o2.getBeginZ());
                        }
                    }
                });
                PeoAdapter peoAdapter = new PeoAdapter(MainActivity.this,temp);
                listView.setAdapter(peoAdapter);
            }
        });

    }
}