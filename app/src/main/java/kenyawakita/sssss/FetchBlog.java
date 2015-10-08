package kenyawakita.sssss;



public class FetchBlog {
    private String title; //タイトル
    private String url;
    private String date;

    public FetchBlog(String title, String url ,String date) {
        this.title = title;
        this.url = url;
        this.date = date;
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

    //日付の取得とset
    public String getDate(){
        return this.date;
    }
    public void setDate(String d){
        this.date=d;
    }


}