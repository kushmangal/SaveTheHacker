package kmzenon.savethehacker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Naray on 07-05-2017.
 */
public class BidListAdapter extends RecyclerView.Adapter<BidListAdapter.MyViewHolder> {

    private List<Bid> bidList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quantitytv, pricetv;

        public MyViewHolder(View view) {
            super(view);
            quantitytv = (TextView) view.findViewById(R.id.bid_quantity);
            pricetv = (TextView) view.findViewById(R.id.bid_price);
        }
    }

    public BidListAdapter(List<Agency> agencyList) {
        this.bidList = bidList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bid_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bid bid = bidList.get(position);
        holder.quantitytv.setText(bid.getQuantity());
        holder.pricetv.setText(bid.getPrice());
    }

    @Override
    public int getItemCount() {
        return bidList.size();
    }

}
