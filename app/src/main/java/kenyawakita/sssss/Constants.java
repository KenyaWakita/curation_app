package kenyawakita.sssss;

import org.json.JSONObject;

import java.util.ArrayList;

public class Constants {//staticはコンスタンスを作らなくても参照できる

    //ブログタブのブログのURL
    public static final String AMEBA_URL="https://www.kimonolabs.com/api/9q0e7ovy?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //ブログタブのフラグ
    static boolean blogtflag = true;
    //ブログリストを格納
    static ArrayList<FetchBlog> Blog = new ArrayList<FetchBlog>();

    //チケットタブのチケットのURL
    public static final String TICKET_CAMP_URL="https://www.kimonolabs.com/api/29egd7hm?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //チケットタブのフラグ
    static boolean ticketflag = true;
    //チケットリストを格納
    static ArrayList<FetchTicket> Ticket = new ArrayList<FetchTicket>();

    //ニュースタブのニュースのURL
    public static final String ORICON_URL ="https://www.kimonolabs.com/api/a5jver36?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    public static final String NAVER_URL="https://www.kimonolabs.com/api/95z4lq8w?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    public static final String NATARY_URL="https://www.kimonolabs.com/api/acrprn9c?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //Jsonの返り値のpropaty
    public static final String PROPERTY = "property1";
    //ニュースタブのフラグ
    static boolean Newsflag = true;
   //ニュースサイトを格納
   static JSONObject[] newsSites;


    //Youtube
    public static final String YOUTUBE_API_KEY = "AIzaSyCcfEgQ_6qLIV5STXnZnLo040NzEmZuVZ4";
    public static final String SEARCH_KEYWORD = "J%20Soul%20Brothers%22";
    public static final String YOUTUBE_URL ="https://www.googleapis.com/youtube/v3/search?key=" +
            Constants.YOUTUBE_API_KEY + "&q=" + Constants.SEARCH_KEYWORD +
            "&part=snippet&maxResults=20&order=viewCount";
    public static  String NEXT_TOKEN_URL;
    public static  boolean RENZOKUCANCEL=true;

    //youtubeタブのフラグ
    static boolean youtubeflag = true;
    //Youtubeリストを格納
    static ArrayList<FetchYoutube> Youtube = new ArrayList<FetchYoutube>();


    //その他
    public static final String OFFICIAL_WEBSITE =  "http://www.jsoulb.jp/";
    public static final String FB =  "https://www.facebook.com/jsoulb3";
    public static final String GOODS =  "http://www.exiletribestation.jp/onlineshop/";
    public static final String KASHI =  "http://www.uta-net.com/artist/10539/";
    public static final String OFFICIAL_TWITTER =  "https://twitter.com/jsb3_official";
    public static final String NAOTO=  "https://twitter.com/naoto_ex_3jsb_";
    public static final String ELLY =  "https://twitter.com/elly24soul";
    public static final String TOSAKA =  "https://twitter.com/hiroomi_3jsb_";


}
