package mario.rafeek.etba3ny;

/**
 * contains data for bible
 * Created by RAA on 02/10/2015.
 */
public class BibleData {
    // this variable for bible navigation (part,sefr,chapter)
    public static int Bp = 0, Ba = 0, Bc = 0;
    //public static int RBp, RBa,RBc;
    //array for stor parts names
    public static String bible_parts[] = {"القديم", "الجديد"};
    // arrays for store asfar names
    private static String bible_titles_old[] = {"تكوين", "خروج", "اللاويين", "عدد", "تثنيه", "يشوع", "قضاه", "راعوث", "صموئيل الاول", "صموئيل الثانى"
            , "ملوك الاول", "ملوك الثانى", "اخبار الايام الاول", "اخبار الايام الثانى", "عزرا", "نحميا", "طوبيا", "يهوديت", "استير"
            , "ايوب", "المزامير", "الامثال", "الجامعه", "نشيد الانشاد", "حكمه سليمان", "يشوع بن سيراخ", "اشعياء", "ارمياء"
            , "مراثى ارمياء", "باروك", "حزقيال", "دانيال", "هوشع", "يوئيل", "عاموس", "عوبيديا", "يونان", "ميخا", "ناحوم", "حبقوق"
            , "صفنيا", "حجى", "زكريا", "ملاخى", "المكابيين الاول", "المكابيين الثانى"};
    private static String bible_titles_new[] = {"انجيل متى", "انجيل مرقس", "انجيل لوقا", "انجيل يوحنا", "اعمال الرسل", "رساله بولس الرسول الى روميه"
            , "رساله بولس الرسول الى كورنثوس 1", "رساله بولس الرسول الى كورنثوس 2", "رساله بولس الرسول الى غلاطيه", "رساله بولس الرسول الى افسس"
            , "رساله بولس الرسول الى فيلبى", "رساله بولس الرسول الى كولوسى", "رساله بولس الرسول  الى تسالونيكى 1", "رساله بولس الرسول الى تسالونيكى 2"
            , "رساله بولس الرسول الى تيموثاوس 1", "رساله بولس الرسول الى تيموثاوس 2", "رساله بولس الرسول الى تيطس", "رساله بولس الرسول الى فيلمون"
            , "رساله بولس الرسول الى العبرانيين", "رساله يعقوب الرسول", "رساله بطرس الرسول 1", "رساله بطرس الرسول 2", "رساله يوحنا الرسول 1", "رساله يوحنا الرسول 2"
            , "رساله يوحنا الرسول 3", "رساله يهوذا الرسول", "رؤيا يوحنا"};
    public static String bible_titles[][] = {bible_titles_old, bible_titles_new};
    //arrays for store number of chapter for every sefr
    private static int bible_chapters_old[] = {50, 40, 27, 36, 34, 24, 21, 4, 31, 24, 22, 25, 29, 36, 10, 13, 14, 16, 16, 42, 151, 31, 12, 8, 19, 51, 66, 52, 5, 6, 48, 14, 14, 3, 9, 1, 4, 7, 3, 3, 3, 2, 14, 4, 16, 15};
    private static int bible_chapters_new[] = {28, 16, 24, 21, 28, 16, 16, 13, 6, 6, 4, 4, 5, 3, 6, 4, 3, 1, 13, 5, 5, 3, 5, 1, 1, 1, 22};
    public static int bible_chapters[][] = {bible_chapters_old, bible_chapters_new};
    // arrays to store file names shortcuts for asfar
    private static String bible_names_old[] = {"ta", "ex", "le", "nu", "de", "jo", "ju", "ru", "1sa", "2sa", "1ki", "2ki", "1ch", "2ch", "ez", "ne", "to", "jd"
            , "es", "jb", "ps", "pr", "ec", "ss", "we", "si", "is", "je", "la", "ba", "zk", "da", "ho", "jl", "am", "ob", "jn", "mi", "na", "ha", "ze", "hg", "zc", "ml", "1mc", "2mc"};
    private static String bible_names_new[] = {"mt", "mr", "lo", "yo", "ac", "ro", "1ko", "2ko", "ga", "ep", "ph", "co", "1ts", "2ts", "1ti", "2ti", "tt"
            , "pl", "hb", "js", "1pe", "2pe", "1in", "2in", "3in", "yh", "rt"};
    public static String bible_names[][] = {bible_names_old, bible_names_new};
}
