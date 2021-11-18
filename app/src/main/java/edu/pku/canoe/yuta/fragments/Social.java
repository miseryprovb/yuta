package edu.pku.canoe.yuta.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;

import java.util.ArrayList;
import java.util.List;

import edu.pku.canoe.yuta.R;
import edu.pku.canoe.yuta.adapters.SocialTextAdapter;


public class Social extends Fragment {
    List<String> text_list = new ArrayList<>();
    private int mScrollState;
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate =  inflater.inflate(R.layout.activity_social, container, false);
        RecyclerView mRecyclerView = (RecyclerView) inflate.findViewById(R.id.text_list);

        text_list.add("管理员提示：请大家遵守规则 ，文明言行！");
        text_list.add("大家好！");
        text_list.add("本周日将在xx社区举办残障人士交流会，欢迎大家参加");
        text_list.add("!");
        text_list.add("没有更多的消息");

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new SocialTextAdapter(text_list));

    return inflate;
    }
}