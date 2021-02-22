package mario.rafeek.etba3ny;

import java.util.List;

import mario.rafeek.etba3ny.modules.tranimModule;

/**
 * Created by rifo on 12/7/15.
 */
public class TranimData {
    static public final String letters[] = {"ا", "ب", "ت", "ث", "ج", "ح", "خ", "د", "ذ", "ر", "ز", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "م", "ن", "ه", "و", "ى", "شعارات مهرجان الكرازه"};
    static public final String lettersFolders[] = {"01alef", "02beh", "03teh", "04theh", "05geem", "06haa", "07khaa", "08dal", "09zal", "10reh", "11zeen", "12seen"
            , "13sheen", "14saad", "15daad", "16taa", "17zaa", "18een", "19gheen", "20faa", "21qaf", "22kaf", "23lam", "24meem", "25noon", "26haa", "27waw", "28yeh", "29mhrgan"};
    ////////////////////////////////////////////////////
    static public int MainPosition = 0;
    static public String currentID;

    static public List<tranimModule> tranimModules;
}
