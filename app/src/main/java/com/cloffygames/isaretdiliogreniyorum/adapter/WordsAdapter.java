package com.cloffygames.isaretdiliogreniyorum.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloffygames.isaretdiliogreniyorum.databinding.RecyclerRowBinding;
import com.cloffygames.isaretdiliogreniyorum.model.Words;
import com.cloffygames.isaretdiliogreniyorum.view.InfoActivity;

import java.util.ArrayList;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.wordsHolder> {


    private ArrayList<Words> wordsArrayList;

    public WordsAdapter(ArrayList<Words> wordsArrayList){
        this.wordsArrayList = wordsArrayList;
    }

    @NonNull
    @Override
    public wordsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new wordsHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull wordsHolder holder, int position) {
        holder.binding.recylerviewText.setText(wordsArrayList.get(position).name.toUpperCase());
        String descriptionss = wordsArrayList.get(position).descriptions;
        String imagepathh = wordsArrayList.get(position).image_path;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), InfoActivity.class);
                intent.putExtra("wordname", holder.binding.recylerviewText.getText());
                intent.putExtra("imagepathh", imagepathh);
                intent.putExtra("descriptionss", descriptionss);

                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return wordsArrayList.size();
    }

    public class wordsHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding;

        public wordsHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
