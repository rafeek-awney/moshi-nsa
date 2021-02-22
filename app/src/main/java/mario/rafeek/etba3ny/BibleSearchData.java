package mario.rafeek.etba3ny;

import java.util.ArrayList;
import java.util.List;

/**
 * contains search data and variables
 * Created by RAA on 06/10/2015.
 */
public class BibleSearchData {
    ////////////////////////
    //data for search class
    public static String main_parts[] = {"الكل", "العهد القديم", "العهد الجديد", "اختر سفر"};
    public static List<String> results = new ArrayList<>();
    public static List<Integer> go_result_p = new ArrayList<>();
    public static List<Integer> go_result_a = new ArrayList<>();
    public static List<Integer> go_result_c = new ArrayList<>();
    /////////////////////////////////////
    //data for feel class
    ////////////////////////////////////
    public static String feelings[] = {"الفرح", "الشكر و الحمد", "الذنب", "الاكتئاب", "القلق", "الخوف", "الضعف", "عدم الايمان", "الاضطهاد", "الالم", "الحزن", "الغضب", "الحقد"
            , "الاحتياج", "الوحده", "خيبه الامل", "الفشل", "الخطر", "الغيره", "التعب", "العجز", "النبذ", "التدنى", "الاحباط", "التشويش", "الضيق", "الكبرياء"
            , "الاضطراب", "الكمال", "الياس", "الظلم", "الحسد", "المحبه", "الغفران"};
    public static int feelings_director[][] = {{20, 20, 20, 20, 20, 20, 20, 26, 20, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 26, 20, 20, 27, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20}
            , {125, 137, 31, 41, 22, 26, 144, 42, 141, 4, 115, 3, 108, 62, 24, 17, 30, 90, 72, 39, 37, 42, 0, 12, 54, 17, 17, 130, 138, 87, 139, 36, 132, 31}};
}
