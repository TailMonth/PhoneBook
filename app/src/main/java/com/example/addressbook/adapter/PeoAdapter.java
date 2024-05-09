package com.example.addressbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.addressbook.MainActivity;
import com.example.addressbook.R;
import com.example.addressbook.activity.DetailsActivity;
import com.example.addressbook.bean.PeoBean;

import java.util.List;

public class PeoAdapter extends ArrayAdapter<PeoBean> {//格式化数据
    List<PeoBean> items;//外部传入数据
    public PeoAdapter(Context context, List<PeoBean> items) {
        super(context, R.layout.book_view,items);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.book_view,parent,false);
        }

        ImageView image =  convertView.findViewById(R.id.image);//图片
        TextView name = convertView.findViewById(R.id.name);//名字
        TextView initial = convertView.findViewById(R.id.initial);//姓的首字母

        PeoBean peo = items.get(position);
        name.setText(peo.getName());        //读取名字

        if(peo.getSex().equals("男")){
            image.setImageResource(R.drawable.man);
        }else if (peo.getSex().equals("女")){
            image.setImageResource(R.drawable.wuman);
        }
        //首字母一样合并
        if(position == 0){
            initial.setText(peo.getBeginZ());
        }else {
            PeoBean temp = items.get(position-1);
            if (!temp.getBeginZ().equals(peo.getBeginZ())){
                initial.setText(peo.getBeginZ());
            }else {
                initial.setHeight(1);//0000000
            }
        }

        //点击跳转

        View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id",peo.getId());
                 getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
