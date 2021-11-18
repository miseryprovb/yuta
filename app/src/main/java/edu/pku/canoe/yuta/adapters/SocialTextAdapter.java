package edu.pku.canoe.yuta.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.pku.canoe.yuta.R;

public class SocialTextAdapter  extends RecyclerView.Adapter<SocialTextAdapter.ViewHolder>{
    private final List<String> textList;

    public SocialTextAdapter(List<String> mList){
        textList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.social_text_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = textList.get(position);
        holder.socialText.setText(text);
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        Button socialText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            socialText = (Button) itemView.findViewById(R.id.social_text);
        }
    }
}
