package kenyawakita.musi_king;



public class FetchBlog {
    private String title; //�^�C�g��
    private String url;
    private String date;

    public FetchBlog(String title, String url ,String date) {
        this.title = title;
        this.url = url;
        this.date = date;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String t){
        this.title=t;
    }

    public String getUrl(){
        return this.url;
    }
    public void setUrl(String u){
        this.url=u;
    }

    public String getDate(){
        return this.date;
    }
    public void setDate(String d){
        this.date=d;
    }
}