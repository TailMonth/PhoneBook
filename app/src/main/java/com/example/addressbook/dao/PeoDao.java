package com.example.addressbook.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.addressbook.bean.PeoBean;
import com.example.addressbook.until.DBUntil;
import com.hankcs.hanlp.HanLP;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeoDao {
    public static SQLiteDatabase db = DBUntil.db;

    public static List<PeoBean> getALLPeo(){
        List<PeoBean> list = new ArrayList<>();

        Cursor res =  db.rawQuery("select * from d_peo",null);
        while (res.moveToNext()){
            PeoBean peoBean = new PeoBean(res.getString(0), res.getString(1), res.getString(2), res.getString(3),"" );
            String name = res.getString(1);//判断中英文 区分首字母
            String firstS = name.substring(0,1);//取出第一个字符
            boolean re =  firstS.matches("^[a-zA-Z]*");
            if(re){
                peoBean.setBeginZ(firstS.toUpperCase());//字母转化为大写
            }else{
                //全部都是汉字或者其他符号
                String regEX = "[\\u4e00-\\u9fa5]";
                Pattern p = Pattern.compile(regEX);
                Matcher m =  p.matcher(firstS);
                if (m.find()){
                    //将第一个字符转化为拼音
                    String py = HanLP.convertToPinyinString(firstS,"",false);
                    String xing = py.substring(0,1);//姓的首字母
                    peoBean.setBeginZ(xing.toUpperCase());

                }else {
                    peoBean.setBeginZ("#");//其他字符一律用#
                }
            }

            list.add(peoBean);
        }
        return list;
    }
    public static void delPeo(String id){
        db.execSQL("DELETE FROM d_peo where s_id=?",new String[]{id});
     }

     public static void updatePeo(String ...id){
        db.execSQL("update d_peo set s_name=?,s_phone=?,s_sex=?,s_remark=? where s_id=?",id);
     }

    public static void savePeo(String ...id){
        db.execSQL("INSERT INTO d_peo (s_name,s_phone,s_sex,s_remark) VALUES(?,?,?,?)",id);
    }

    public static List<PeoBean> getALLPeo(String id){
        List<PeoBean> List = new ArrayList<>();
        String query = "SELECT * FROM d_peo WHERE s_phone LIKE '%"+id+"%'OR s_name LIKE '%"+id+"%'";
        Cursor res =  db.rawQuery(query,null);
        while (res.moveToNext()){
            PeoBean peoBean = new PeoBean(res.getString(0), res.getString(1), res.getString(2), res.getString(3),"" );
            String name = res.getString(1);//判断中英文 区分首字母
            String firstS = name.substring(0,1);//取出第一个字符
            boolean re =  firstS.matches("^[a-zA-Z]*");
            if(re){
                peoBean.setBeginZ(firstS.toUpperCase());//字母转化为大写
            }else{
                //全部都是汉字或者其他符号
                String regEX = "[\\u4e00-\\u9fa5]";
                Pattern p = Pattern.compile(regEX);
                Matcher m =  p.matcher(firstS);
                if (m.find()){
                    //将第一个字符转化为拼音
                    String py = HanLP.convertToPinyinString(firstS,"",false);
                    String xing = py.substring(0,1);//姓的首字母
                    peoBean.setBeginZ(xing.toUpperCase());

                }else {
                    peoBean.setBeginZ("#");//其他字符一律用#
                }
            }

            List.add(peoBean);
        }
        return List;
    }

     public static PeoBean getOnePeo(String id){    /*查询一个人的联系方式*/

        Cursor res =  db.rawQuery("select * from d_peo where s_id=?",new String[]{id});
        PeoBean peoBean = null;
        while (res.moveToNext()){
            peoBean = new PeoBean(res.getString(0), res.getString(1), res.getString(2), res.getString(3),res.getString(4) );
        }
        return peoBean;
    }
}
