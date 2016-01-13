package kenyawakita.musi_king;


public class FetchBoardType {
    private String title;
    private String url;

    public FetchBoardType(String title, String url) {
        this.title = title;
        this.url = url;
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

}

