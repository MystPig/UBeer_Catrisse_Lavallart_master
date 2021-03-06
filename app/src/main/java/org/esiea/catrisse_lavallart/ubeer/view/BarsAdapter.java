package org.esiea.catrisse_lavallart.ubeer.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.esiea.catrisse_lavallart.ubeer.R;
import org.esiea.catrisse_lavallart.ubeer.controller.BarDetailActivity;
import org.esiea.catrisse_lavallart.ubeer.model.Bar;


public class BarsAdapter extends RecyclerView.Adapter<BarsAdapter.BarsHolder>{




    private Bar barsArray[];
    public BarsAdapter(Bar barsArray[])
    {
        this.barsArray = barsArray;
    }


    @Override
    public BarsAdapter.BarsHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_bar_element, parent, false);


        return new BarsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BarsAdapter.BarsHolder holder, int position) {

            holder.barName.setText(barsArray[position].getName());
        holder.barRate.setTextColor(Color.parseColor("#996633"));
            if(!barsArray[position].getRank().equals("?")){
                float rank = Float.parseFloat(barsArray[position].getRank());
                if(rank>3.9){
                    holder.barRate.setTextColor(Color.parseColor("#008000"));
                }else if(rank>3.4){
                    holder.barRate.setTextColor(Color.parseColor("#996633"));
                }else{
                    holder.barRate.setTextColor(Color.parseColor("#ff1a1a"));
                }
            }

            holder.barRate.setText(barsArray[position].getRank());

            if(barsArray[position].getIsOpen().equals("true")){
                holder.barIsOpen.setText(R.string.open);
            }else if(barsArray[position].getIsOpen().equals("false")){
                holder.barIsOpen.setText(R.string.closed);
            }else{
                holder.barIsOpen.setText("?");
            }

        
    }

    @Override
    public int getItemCount() {
        return barsArray.length;
    }

    public void setData(Bar data[]){
        this.barsArray = data;
        notifyDataSetChanged();
    }

    class BarsHolder extends RecyclerView.ViewHolder {

        public TextView barName;
        public TextView barRate;
        public TextView barIsOpen;
        public RelativeLayout layout;
        public BarsHolder(View itemView){

            super(itemView);
            final View iv = itemView;
            barName=(TextView) itemView.findViewById(R.id.rv_bar_element_name);
            barIsOpen=(TextView) itemView.findViewById(R.id.rv_bar_element_isOpen);
            barRate=(TextView) itemView.findViewById(R.id.rv_bar_element_rate);
            layout=(RelativeLayout) itemView.findViewById(R.id.rv_bar_element);

            Log.d("VIEW","holder");

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int itemPosition = getAdapterPosition();
                    Intent i = new Intent(context, BarDetailActivity.class);
                    i.putExtra("selectedBar",barsArray[itemPosition]);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
                            (View)iv.findViewById(R.id.rv_bar_element_rate), "element");
                    context.startActivity(i,options.toBundle());

                }
            });
        }

    }
}
