package kenyawakita.sssss;


public class FetchYoutube {
    private String title; //タイトル
    private String url;  //URL
    private String image;
    private String date;


    public FetchYoutube(String title, String url, String image, String date) {
        this.title = title;
        this.url = url;
        this.image = image;
        this.date=date;
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

    //画像URLの取得とset
    public String getImageUrl(){
        return this.image;
    }
    public void setImageUrl(String u){
        this.image=u;
    }


    //Dateの取得とset
    public String getDate(){
        String date=this.date;
        String[] datebox = date.split("T", 0);//price[0]に改行される前の文字を格納
        return datebox[0];
    }

    public void setData(String d){
        this.date=d;
    }

}
