package edu.pku.canoe.yuta.adapters;

import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.pku.canoe.yuta.R;

public class SocialTextAdapter  extends RecyclerView.Adapter<SocialTextAdapter.ViewHolder>{
    private final List<String> textList;
    private final List<Time> timeList;

    public SocialTextAdapter(List<String> mList, List<Time> tList){
        textList = mList;
        timeList = tList;
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
        Time time = timeList.get(position);
        holder.socialText.setText(text);
        holder.timeText.setText(time.format("%Y.%m.%d %H:%M:%S"));
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView timeText;
        Button socialText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            socialText = (Button) itemView.findViewById(R.id.social_text);
            timeText = (TextView) itemView.findViewById(R.id.time_text);
        }
    }
}
