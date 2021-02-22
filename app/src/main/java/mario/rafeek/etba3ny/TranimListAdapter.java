package mario.rafeek.etba3ny;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mario.rafeek.etba3ny.modules.tranimModule;

/**
 * Created by Rafeek Awney Anwar on 11/6/2015.
 * adapter for the list of tranim
 */
public class TranimListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Context mcontext;
    private List<tranimModule> tranimModules;

    public TranimListAdapter(Context mcontext, List<tranimModule> tranimModules) {
        this.mcontext = mcontext;
        this.tranimModules = tranimModules;
    }

    /*
        public TranimListAdapter(Activity mainActivity, String[] IdColumn, String[] NameColumn) {
            // TODO Auto-generated constructor stub
            idColumn=IdColumn;
            nameColumn=NameColumn;
            mcontext=mainActivity;
            inflater = ( LayoutInflater )mcontext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return tranimModules.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return tranimModules.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = View.inflate(mcontext, R.layout.tranim_list_item, null);
        TextView idTV = (TextView) v.findViewById(R.id.idTV);
        TextView nameTv = (TextView) v.findViewById(R.id.nameTV);
        idTV.setText(tranimModules.get(position).getId());
        nameTv.setText(tranimModules.get(position).getName());
        return v;
    }

}
