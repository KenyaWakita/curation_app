package kenyawakita.sssss;


public class FetchTicket {
        private String title; //タイトル
        private String url;
        private String loc; //場所
        private String imageurl;  //画像のURL
        private String date;
        private String price;

        public FetchTicket(String title, String url ,String loc, String imageurl, String date, String price) {
            this.title = title;
            this.url = url;
            this.loc = loc;
            this.imageurl = imageurl;
            this.date = date;
            this.price = price;
        }

        //タイトルの取得とset
        public String getTitle(){
            return this.title;
        }
        public void setTitle(String t){
            this.title=t;
        }

        //url先の取得とset
        public String getUrl(){
        return this.url;
    }
        public void setUrl(String u){
        this.url=u;
    }

        //開催地の取得とset
        public String getLoc(){
            return this.loc;
        }
        public void setLoc(String l){
            this.loc=l;
        }



    //画像のURLの取得とset
        public String getImageurl(){
            return this.imageurl;
        }
        public void setImageurl(String u){
            this.imageurl =u;
        }

        //日付の取得とset
        public String getDate(){
            return this.date;
        }
        public void setDate(String d){
            this.date=d;
        }

        //値段の取得とset
        public String getPrice(){
            String nodetail=this.price;
            String[] price = nodetail.split("\n", 0);//price[0]に改行される前の文字を格納
            return price[0];
        }
        public void setPrice(String p){
            this.title=p;
    }


    }
