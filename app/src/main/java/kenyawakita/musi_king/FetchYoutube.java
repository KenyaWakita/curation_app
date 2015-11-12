package kenyawakita.musi_king;


public class FetchYoutube {
    private String title; //�^�C�g��
    private String url;  //URL
    private String image;
    private String date;


    public FetchYoutube(String title, String url, String image, String date) {
        this.title = title;
        this.url = url;
        this.image = image;
        this.date=date;
    }

    //�^�C�g���̎擾��set
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String t){
        this.title=t;
    }

    //URL�̎擾��set
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String u){
        this.url=u;
    }

    //�摜URL�̎擾��set
    public String getImageUrl(){
        return this.image;
    }
    public void setImageUrl(String u){
        this.image=u;
    }


    //Date�̎擾��set
    public String getDate(){
        String date=this.date;
        String[] datebox = date.split("T", 0);//price[0]�ɉ�s�����O�̕������i�[
        return datebox[0];
    }

    public void setData(String d){
        this.date=d;
    }

}
