package kenyawakita.sssss;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FetchNews {
    private String title; //タイトル
    private String url;  //URL
    private String date;//日付
    private String site; //サイト名


    public FetchNews(String title, String url, String date, String site) {
        this.title = title;
        this.url = url;
        this.date=date;
        this.site=site;
    }

    //タイトルの取得とset
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String t){
        this.title=t;
    }

    //URLの取得とset
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String u){
        this.url=u;
    }


    //Dateの取得とset
    public Date getDate() throws ParseException {
        String certainDay =this.date;
        String[] year = certainDay.split("年", 0);//year[0]に年をyear[1]に月と日を格納(フォーマットが"yyyy-MM-dd"であれば，すべてyear[0]に格納される)
        String[] time = certainDay.split(":",0);//time[0]に時間をtime[1]に分を格納(フォーマットが"yyyy-MM-dd"であれば，すべてtime[0]に格納される)


         /*
        date型のフォーマットを""yyyy-MM-dd"に統一したい

         */
        if (year.length==2)//”yyyy年MM月dd日”であるとき，year.length==2であるはず
        {
            String[] month = year[1].split("月", 0); //month[0]に月を，month[1]に"○○日"
            String[] day = month[1].split("日", 0); //day[0]に日を

            certainDay =year[0]+"-"+month[0]+"-"+day[0];
        }

        //今日，更新された記事は時間(例:8:00)しか記述されていない
        if (time.length == 2)
            {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                certainDay = sdf.format(cal.getTime());

            }



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date cdDate = sdf.parse(certainDay);

        return cdDate;
    }
    public void setData(String d){
        this.date=d;
    }


    //サイト名の取得とset
    public String getSite(){
        return this.site;
    }
    public void setSite(String s){
        this.url=s;
    }


}
