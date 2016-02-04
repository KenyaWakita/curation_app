package kenyawakita.musi_king;

import org.json.JSONObject;

import java.util.ArrayList;

public class Constants {

    //ブログのURL
    public static final String AMEBA_URL="https://www.kimonolabs.com/api/9q0e7ovy?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //�u���O�^�u�̃t���O
    static boolean blogtflag = true;
    static boolean ticketflag = true;
    static boolean Newsflag = true;
    static boolean bordflag = true;
    static boolean boardtypeflag = true;

    //リストに表示させる情報を格納しておく，arraylist
    static ArrayList<FetchBlog> Blog = new ArrayList<FetchBlog>();
    static ArrayList<FetchNews> Bord = new ArrayList<FetchNews>();
    static ArrayList<FetchNews> news_sites = new ArrayList<FetchNews>();
    static ArrayList<FetchTicket> Ticket = new ArrayList<FetchTicket>();
    static ArrayList<FetchYoutube> youtube = new ArrayList<FetchYoutube>();
    static ArrayList<FetchYoutube> youtube_channel = new ArrayList<FetchYoutube>();
    static JSONObject[] newsSites;

    //JsoulのチケットキャンプのURL
    public static final String TICKET_CAMP_URL="https://www.kimonolabs.com/api/29egd7hm?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";

    //モンハンのニュースサイトのURL
    public static final String NEWS_SITE1 = Constants.kimono_prev + "d0f2oxje" + Constants.kimono_next;


    public static final String ORICON_URL ="https://www.kimonolabs.com/api/a5jver36?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    public static final String NAVER_URL="https://www.kimonolabs.com/api/95z4lq8w?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    public static final String NATARY_URL="https://www.kimonolabs.com/api/acrprn9c?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //Jsonのpropaty
    public static final String PROPERTY = "property1";

    public static final String MEDIA_ID = "MEDIA-d830a33c";
    public static final String BANNER_MEDIA_ID = "MEDIA-2b84dc9e";
    public static final int SPOT = 1;

    //Bord
    public static final String BORD_URL = "https://www.kimonolabs.com/api/4rwljkca?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";

    //Youtube
    public static final String YOUTUBE_API_KEY = "AIzaSyCcfEgQ_6qLIV5STXnZnLo040NzEmZuVZ4";
    public static final String SEARCH_KEYWORD = "モンハンクロス%20実況";
    public static final String SEARCH_KEYWORD2 = "96猫%20歌ってみた";
    public static final String SEARCH_KEYWORD3 = "96猫%20ゲーム実況";
    public static final String CHANNEL_ID = "UCwu15I0KWOI6Xbe2Uziee4A";

    public static final String YOUTUBE_SEARCH_URL ="https://www.googleapis.com/youtube/v3/search?key=" + Constants.YOUTUBE_API_KEY + "&q=" + Constants.SEARCH_KEYWORD + "&part=snippet&maxResults=20&order=relevance";
    public static final String YOUTUBE_SEARCH_URL2 ="https://www.googleapis.com/youtube/v3/search?key=" + Constants.YOUTUBE_API_KEY + "&q=" + Constants.SEARCH_KEYWORD2 + "&part=snippet&maxResults=20&order=relevance";
    public static final String YOUTUBE_SEARCH_URL3 ="https://www.googleapis.com/youtube/v3/search?key=" + Constants.YOUTUBE_API_KEY + "&q=" + Constants.SEARCH_KEYWORD3 + "&part=snippet&maxResults=20&order=relevance";

    public static final String YOUTUBE_CHANNEL_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=" + Constants.CHANNEL_ID + "&maxResults=30&key=" + Constants.YOUTUBE_API_KEY + "&order=date";


    public static  String NEXT_TOKEN_URL;
    public static  boolean RENZOKUCANCEL=true;

    //youtubeタブを開いたかどうか
    static boolean youtubeflag = true;


    //webviewfragmentに表示するURL
    public static final String APP_RANK_ANDROID_URL ="https://androider.jp/official/applist/ranking/total/daily/1/";
    public static final String APP_RANK_IPHONE_URL = "http://topappranking300.appios.net/top-free-iphone/index.html";
    public static final String APP_RANK_ANDROID_ARTICLE_URL ="http://weekly.ascii.jp/rank/AndroidDaily.html";
    public static final String APP_RANK_IPHONE_ARTICLE_URL ="http://weekly.ascii.jp/rank/AppleDaily.html";

    //BoardType アプリのアクセスランキング記事
    public static final String BoardType_JSON = "https://www.kimonolabs.com/api/d0f2oxje?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    public static final String BoardType_JSON2 = "https://www.kimonolabs.com/api/dyrwfamm?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";//攻略
    public static final String BoardType_JSON3 = "https://www.kimonolabs.com/api/6kym55ty?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";//武器
    public static final String BoardType_JSON4 = "https://www.kimonolabs.com/api/45xfk0zs?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";//モンスター

    //twitterタブで取得したいid名(アカウント名) @の後の文字列
    public  static final String TWITTER_ACCOUNT = "96__neko";

    //その他
    public static final String OFFICIAL_WEBSITE =  "http://www.jsoulb.jp/";
    public static final String FB =  "https://www.facebook.com/jsoulb3";
    public static final String GOODS =  "http://www.exiletribestation.jp/onlineshop/";
    public static final String KASHI =  "http://www.uta-net.com/artist/10539/";
    public static final String OFFICIAL_TWITTER =  "https://twitter.com/jsb3_official";
    public static final String NAOTO=  "https://twitter.com/naoto_ex_3jsb_";
    public static final String ELLY =  "https://twitter.com/elly24soul";
    public static final String TOSAKA =  "https://twitter.com/hiroomi_3jsb_";


    public static final String kimono_prev = "https://www.kimonolabs.com/api/";
    public static final String kimono_next = "?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";

}
