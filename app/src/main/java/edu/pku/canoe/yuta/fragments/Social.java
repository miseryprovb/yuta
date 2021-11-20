package edu.pku.canoe.yuta.fragments;

import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import edu.pku.canoe.yuta.R;
import edu.pku.canoe.yuta.adapters.SocialTextAdapter;


public class Social extends Fragment {
    List<String> textList = new ArrayList<>();
    List<Time> timeList = new ArrayList<>();
    private int mScrollState;
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate =  inflater.inflate(R.layout.activity_social, container, false);
        RecyclerView mRecyclerView = (RecyclerView) inflate.findViewById(R.id.text_list);

        textList.add("管理员提示：请大家遵守规则 ，文明言行！");
        textList.add("大家好！");
        textList.add("本周日将在xx社区举办残障人士交流会，欢迎大家参加");
        textList.add("!");
        textList.add("没有更多的消息");

        for(int i = 0; i < 5; ++i) {
            Time time = new Time();
            time.set(System.currentTimeMillis() - (int)((5 - i) * 50000 * new Random().nextDouble()));
            timeList.add(time);
        }

        timeList.sort(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.before(o2) ? 1 : -1;
            }
        });

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new SocialTextAdapter(textList, timeList));

    return inflate;
    }
}