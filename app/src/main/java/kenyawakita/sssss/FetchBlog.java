package kenyawakita.sssss;



public class FetchBlog {
    private String title; //�^�C�g��
    private String date;

    public FetchBlog(String title, String date) {
        this.title = title;
        this.date = date;
    }

    //�^�C�g���̎擾��set
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String t){
        this.title=t;
    }

    //���t�̎擾��set
    public String getDate(){
        return this.date;
    }
    public void setDate(String d){
        this.date=d;
    }


}