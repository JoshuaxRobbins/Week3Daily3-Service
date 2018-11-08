package com.example.josh.week3daily3_service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josh.week3daily3_service.model.Person;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<Person> personList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recview_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person person = personList.get(position);
        viewHolder.tvPersonName.setText(person.getName());
        viewHolder.tvPersonAge.setText(person.getAge());
        viewHolder.tvPersonGender.setText(person.getGender());
        viewHolder.ivPersonPicture.setImageBitmap(person.getPicture());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPersonName;
        private TextView tvPersonAge;
        private TextView tvPersonGender;
        private ImageView ivPersonPicture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPersonName = itemView.findViewById(R.id.tvPersonName);
            tvPersonAge = itemView.findViewById(R.id.tvPersonAge);
            tvPersonGender = itemView.findViewById(R.id.tvPersonGender);
            ivPersonPicture = itemView.findViewById(R.id.ivPersonPicture);

        }
    }
}
