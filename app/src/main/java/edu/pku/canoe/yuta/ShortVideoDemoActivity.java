package edu.pku.canoe.yuta;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.visibility_utils.calculator.DefaultSingleItemCalculatorCallback;
import com.volokh.danylo.visibility_utils.calculator.ListItemsVisibilityCalculator;
import com.volokh.danylo.visibility_utils.calculator.SingleListViewItemActiveCalculator;
import com.volokh.danylo.visibility_utils.scroll_utils.RecyclerViewItemPositionGetter;

import java.util.ArrayList;
import java.util.List;

import edu.pku.canoe.yuta.adapters.OnlineVideoListItem;
import edu.pku.canoe.yuta.adapters.VideoListItem;
import edu.pku.canoe.yuta.adapters.VideoWatchAdapter;

public class ShortVideoDemoActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;

    //视频数据，相当于普通adapter里的datas
    private List<VideoListItem> mLists = new ArrayList<>();

    //它充当ListItemsVisibilityCalculator和列表（ListView, RecyclerView）之间的适配器（Adapter）。
    private RecyclerViewItemPositionGetter mItemsPositionGetter;

    //ListItemsVisibilityCalculator可以追踪滑动的方向并在过程中计算每个Item的可见度
    //SingleListViewItemActiveCalculator会在滑动时获取每个View的可见度百分比.
    //所以其构造方法里需要传入mLists，而mLists里的每个item实现了ListItem接口
    //的getVisibilityPercents方法，也就是返回当前item可见度的方法.
    //这样ListItemsVisibilityCalculator就可以计算当前item的可见度了.

    private final ListItemsVisibilityCalculator mVideoVisibilityCalculator =
            new SingleListViewItemActiveCalculator(new DefaultSingleItemCalculatorCallback(), mLists);


    //SingleVideoPlayerManager就是只能同时播放一个视频。
    //当一个view开始播放时，之前那个就会停止
    private final VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
        @Override
        public void onPlayerItemChanged(MetaData metaData) {
        }
    });

    private int mScrollState;
    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_watch_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.video_watch_list);


        //添加视频数据
        for (int i = 0; i < 3; ++i) {
            mLists.add(new OnlineVideoListItem(mVideoPlayerManager, "无声婚礼！", R.drawable.shortvideo1, R.raw.shortvideo1));
            mLists.add(new OnlineVideoListItem(mVideoPlayerManager, "办证大厅小姐姐与聋哑人无障碍交流", R.drawable.shortvideo2,R.raw.shortvideo2));
            mLists.add(new OnlineVideoListItem(mVideoPlayerManager, "聋哑外卖小哥勇救落水老人！", R.drawable.shortvideo3, R.raw.shortvideo3));
        }


        mRecyclerView.setLayoutManager(mLayoutManager);
        VideoWatchAdapter adapter = new VideoWatchAdapter(mLists);
        mRecyclerView.setAdapter(adapter);
        //////////////////////////////////////////////

        //这里是文档上默认的写法，直接复制下来。
        //查看了下源码其中VisibilityCalculator.onScrollStateIdle的这
        //个方法又调用了方法calculateMostVisibleItem，用来计算滑动状态改变时
        //的最大可见度的item.这个方法的计算方法是这样的：当view无论是向上还是
        //向下滚动时，在滚动的过程中，计算可见度最大的item。当滚动状态为空闲时
        //此时最后一个计算得出的可见度最大的item就是当前可见度最大的item
        //而onScroll方法是处理item滚出屏幕后的计算,用于发现新的活动item
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                mScrollState = scrollState;
                if (scrollState == RecyclerView.SCROLL_STATE_IDLE && !mLists.isEmpty()) {

                    mVideoVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition());
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!mLists.isEmpty()) {
                    mVideoVisibilityCalculator.onScroll(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition() - mLayoutManager.findFirstVisibleItemPosition() + 1,
                            mScrollState);
                }
            }
        });

        mItemsPositionGetter = new RecyclerViewItemPositionGetter(mLayoutManager, mRecyclerView);

    }
    @Override
    public void onResume() {
        super.onResume();
        if(!mLists.isEmpty()){
            // need to call this method from list view handler in order to have filled list

            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {

                    mVideoVisibilityCalculator.onScrollStateIdle(
                            mItemsPositionGetter,
                            mLayoutManager.findFirstVisibleItemPosition(),
                            mLayoutManager.findLastVisibleItemPosition());

                }
            });
        }
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onStop() {
        super.onStop();
        mVideoPlayerManager.resetMediaPlayer(); // 页面不显示时, 释放播放器
    }
}
