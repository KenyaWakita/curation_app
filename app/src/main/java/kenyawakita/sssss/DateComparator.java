package kenyawakita.sssss;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<FetchNews> {  //abstract class???

    //比較メソッド（データクラスを比較して-1, 0, 1を返すように記述する）
    public int compare(FetchNews a, FetchNews b) {
        Date no1 = null;
        try {
            no1 = a.getDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date no2 = null;
        try {
            no2 = b.getDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return(-1*(no1.compareTo(no2)));


        //��������ƎЈ��ԍ��̏����Ń\�[�g�����
//        if (no1 > no2) {
//            return 1;
//
//        } else if (no1 == no2) {
//            return 0;
//
//        } else {
//            return -1;
//
//        }
    }

}
