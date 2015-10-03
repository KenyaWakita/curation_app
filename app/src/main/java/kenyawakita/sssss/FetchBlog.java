package kenyawakita.sssss;



public class FetchBlog {
    private String title; //タイトル
    private String date;

    public FetchBlog(String title, String date) {
        this.title = title;
        this.date = date;
    }

    //タイトルの取得とset
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String t){
        this.title=t;
    }

    //日付の取得とset
    public String getDate(){
        return this.date;
    }
    public void setDate(String d){
        this.date=d;
    }


}