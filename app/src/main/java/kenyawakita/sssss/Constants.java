package kenyawakita.sssss;

import org.json.JSONObject;

import java.util.ArrayList;

public class Constants {//static�̓R���X�^���X�����Ȃ��Ă��Q�Ƃł���

    //�u���O�^�u�̃u���O��URL
    public static final String AMEBA_URL="https://www.kimonolabs.com/api/9q0e7ovy?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //�u���O�^�u�̃t���O
    static boolean blogtflag = true;
    //�u���O���X�g���i�[
    static ArrayList<FetchBlog> Blog = new ArrayList<FetchBlog>();

    //�`�P�b�g�^�u�̃`�P�b�g��URL
    public static final String TICKET_CAMP_URL="https://www.kimonolabs.com/api/29egd7hm?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //�`�P�b�g�^�u�̃t���O
    static boolean ticketflag = true;
    //�`�P�b�g���X�g���i�[
    static ArrayList<FetchTicket> Ticket = new ArrayList<FetchTicket>();

    //�j���[�X�^�u�̃j���[�X��URL
    public static final String ORICON_URL ="https://www.kimonolabs.com/api/a5jver36?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    public static final String NAVER_URL="https://www.kimonolabs.com/api/95z4lq8w?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    public static final String NATARY_URL="https://www.kimonolabs.com/api/acrprn9c?apikey=O1LWGKfEhBnwnOmTTuxzTO5UiTYhLuLu";
    //Json�̕Ԃ�l��propaty
    public static final String PROPERTY = "property1";
    //�j���[�X�^�u�̃t���O
    static boolean Newsflag = true;
   //�j���[�X�T�C�g���i�[
   static JSONObject[] newsSites;


    //Youtube
    public static final String YOUTUBE_API_KEY = "AIzaSyCcfEgQ_6qLIV5STXnZnLo040NzEmZuVZ4";
    public static final String SEARCH_KEYWORD = "J%20Soul%20Brothers%22";
    public static final String YOUTUBE_URL ="https://www.googleapis.com/youtube/v3/search?key=" +
            Constants.YOUTUBE_API_KEY + "&q=" + Constants.SEARCH_KEYWORD +
            "&part=snippet&maxResults=20&order=viewCount";
    public static  String NEXT_TOKEN_URL;
    public static  boolean RENZOKUCANCEL=true;

    //youtube�^�u�̃t���O
    static boolean youtubeflag = true;
    //Youtube���X�g���i�[
    static ArrayList<FetchYoutube> Youtube = new ArrayList<FetchYoutube>();


    //���̑�
    public static final String OFFICIAL_WEBSITE =  "http://www.jsoulb.jp/";
    public static final String FB =  "https://www.facebook.com/jsoulb3";
    public static final String GOODS =  "http://www.exiletribestation.jp/onlineshop/";
    public static final String KASHI =  "http://www.uta-net.com/artist/10539/";
    public static final String OFFICIAL_TWITTER =  "https://twitter.com/jsb3_official";
    public static final String NAOTO=  "https://twitter.com/naoto_ex_3jsb_";
    public static final String ELLY =  "https://twitter.com/elly24soul";
    public static final String TOSAKA =  "https://twitter.com/hiroomi_3jsb_";


}
