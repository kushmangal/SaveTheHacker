package kmzenon.savethehacker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Naray on 06-05-2017.
 */
public class AgencyListAdapter extends RecyclerView.Adapter<AgencyListAdapter.MyViewHolder> {

    private List<Agency> agencyList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nametv, msptv, totalprodtv, remainingtv;

        public MyViewHolder(View view) {
            super(view);
            nametv = (TextView) view.findViewById(R.id.agency_name);
            msptv = (TextView) view.findViewById(R.id.agency_msp);
            totalprodtv = (TextView) view.findViewById(R.id.agency_total_prod);
        }
    }

    public AgencyListAdapter(List<Agency> agencyList) {
        this.agencyList = agencyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.agency_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Agency agency = agencyList.get(position);
        holder.nametv.setText(agency.getName());
        holder.msptv.setText("MSP:"+agency.getMsp() + "");
        holder.totalprodtv.setText("Total Production:"+agency.getTotal_prod()+"");
    }

    @Override
    public int getItemCount() {
        return agencyList.size();
    }
}
