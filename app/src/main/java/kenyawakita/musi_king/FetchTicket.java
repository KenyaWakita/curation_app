package kenyawakita.musi_king;


public class FetchTicket {
        private String title; //�^�C�g��
        private String url;
        private String loc; //�ꏊ
        private String imageurl;  //�摜��URL
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

        //�^�C�g���̎擾��set
        public String getTitle(){
            return this.title;
        }
        public void setTitle(String t){
            this.title=t;
        }

        //url��̎擾��set
        public String getUrl(){
        return this.url;
    }
        public void setUrl(String u){
        this.url=u;
    }

        //�J�Òn�̎擾��set
        public String getLoc(){
            return this.loc;
        }
        public void setLoc(String l){
            this.loc=l;
        }



    //�摜��URL�̎擾��set
        public String getImageurl(){
            return this.imageurl;
        }
        public void setImageurl(String u){
            this.imageurl =u;
        }

        //��t�̎擾��set
        public String getDate(){
            return this.date;
        }
        public void setDate(String d){
            this.date=d;
        }

        //�l�i�̎擾��set
        public String getPrice(){
            String nodetail=this.price;
            String[] price = nodetail.split("\n", 0);//price[0]�ɉ�s�����O�̕������i�[
            return price[0];
        }
        public void setPrice(String p){
            this.title=p;
    }


    }
