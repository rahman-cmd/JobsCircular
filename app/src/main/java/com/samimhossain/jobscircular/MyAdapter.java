package com.samimhossain.jobscircular;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter <MyViewHolder> implements Filterable {

    private List <Myitem> Mylist;
    private List <Myitem> SearchList;
    private Context context;

    public MyAdapter(List<Myitem> mylist, Context context) {
        Mylist = mylist;
        this.SearchList = new ArrayList<>(mylist);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout,parent,false);
        MyViewHolder VH = new MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Myitem myitemPosition = Mylist.get(position);


        holder.titleText.setText(myitemPosition.getTitle());

        if(!myitemPosition.getDadeline().isEmpty()){
            holder.deadlineText.setText("Dadeline: "+myitemPosition.getDadeline());
            holder.deadlineText.setVisibility(holder.deadlineText.VISIBLE);
        }else {
            holder.deadlineText.setVisibility(holder.deadlineText.GONE);
        }
        if(!myitemPosition.getAppstart().isEmpty()){
            holder.appStartText.setText("Start: "+myitemPosition.getAppstart());
            holder.appStartText.setVisibility(holder.appStartText.VISIBLE);
        }else {
            holder.appStartText.setVisibility(holder.appStartText.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendTitle = myitemPosition.getTitle().toString();
                String sendDesc = myitemPosition.getDescription().toString();
                String sendAppStart = myitemPosition.getAppstart().toString();
                String sendDeadline = myitemPosition.getDadeline().toString();
                String sendImgUrl = myitemPosition.getFile().toString();
                String sendCategory = myitemPosition.getCatecgory().toString();
                String sendImg2 = myitemPosition.getImg2().toString() ;
                String sendImg3 = myitemPosition.getImg3().toString() ;
                String sendImg4 = myitemPosition.getImg4().toString() ;
                String sendImg5 = myitemPosition.getImg5().toString() ;
                String sendImg6 = myitemPosition.getImg6().toString() ;
                String sendImg7 = myitemPosition.getImg7().toString() ;
                String sendImg8 = myitemPosition.getImg8().toString() ;
                String sendImg9 = myitemPosition.getImg9().toString() ;
                String sendImg10 = myitemPosition.getImg10().toString() ;
                String sendPdf = myitemPosition.getPdf().toString() ;

                Intent i=new Intent(view.getContext(),SinglePost.class);
                i.putExtra("sendTitle",sendTitle);
                i.putExtra("sendDesc",sendDesc);
                i.putExtra("sendAppStart",sendAppStart);
                i.putExtra("sendDeadline",sendDeadline);
                i.putExtra("sendImgUrl",sendImgUrl);
                i.putExtra("sendCategory",sendCategory);
                i.putExtra("sendImg2",sendImg2);
                i.putExtra("sendImg3",sendImg3);
                i.putExtra("sendImg4",sendImg4);
                i.putExtra("sendImg5",sendImg5);
                i.putExtra("sendImg6",sendImg6);
                i.putExtra("sendImg7",sendImg7);
                i.putExtra("sendImg8",sendImg8);
                i.putExtra("sendImg9",sendImg9);
                i.putExtra("sendImg10",sendImg10);
                i.putExtra("sendPdf",sendPdf);
                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Mylist.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Myitem> filteredList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(SearchList);
            }if(charSequence.toString().contains("titleSearch")){
                for(Myitem srchitem  : SearchList){
                    String mainSearch=charSequence.toString().replace("titleSearch","");
                    if(srchitem.getTitle().toString().toLowerCase().contains(mainSearch.toLowerCase())){
                        filteredList.add(srchitem);
                    }
                }
        }if(charSequence.toString().contains("all")){
                for(Myitem allitem  : SearchList){
                    String mainSearch=charSequence.toString().replace("all","");
                    if(allitem.getTitle().toString().toLowerCase().contains(mainSearch.toLowerCase())){
                        filteredList.add(allitem);
                    }
                }
            }
            else{
                for(Myitem item  : SearchList){
                    if(item.getCatecgory().toString().contains(charSequence.toString())){
                         filteredList.add(item);
                    }
                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        public void publishResults(CharSequence charSequence, FilterResults filterResults) {
            Mylist.clear();
            Mylist.addAll((Collection<? extends Myitem>) filterResults.values);
            notifyDataSetChanged();
        }
    };



}
