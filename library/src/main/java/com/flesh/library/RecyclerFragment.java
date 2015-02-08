package com.flesh.library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/**
 * Created by aaronfleshner on 2/7/15.
 */
public class RecyclerFragment extends Fragment {

    private RecyclerView list;
    private View emptyView;
    private RecyclerView.Adapter mAdapter;
    private RelativeLayout layout;
    private Animation mEnterAnimation, mExitAnimation;
    boolean listEmpty = true;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_layout, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        list = (RecyclerView) v.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout = (RelativeLayout) v.findViewById(R.id.recylcerLayout);
    }

    public RecyclerView getRecyclerView() {
        return list;
    }

    public void setLayoutManger(RecyclerView.LayoutManager layout) {
        list.setLayoutManager(layout);
    }

    public void setRecyclerAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        list.setAdapter(mAdapter);
        checkIfRecyclerViewIsEmpty();
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                checkIfRecyclerViewIsEmpty();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                checkIfRecyclerViewIsEmpty();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                checkIfRecyclerViewIsEmpty();
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                checkIfRecyclerViewIsEmpty();
            }

            @Override
            public void onChanged() {
                super.onChanged();
                checkIfRecyclerViewIsEmpty();
            }
        });
    }

    public void setEmptyView(int layout) {
        emptyView = LayoutInflater.from(getActivity()).inflate(layout, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.layout.addView(emptyView);
        checkIfRecyclerViewIsEmpty();
    }

    private boolean mAdding, mRemoving;

    public void setEmptyViewAnimations(int enterAnimation, int exitAnimation) {
        mEnterAnimation = AnimationUtils.loadAnimation(getActivity(), enterAnimation);
        mExitAnimation = AnimationUtils.loadAnimation(getActivity(), exitAnimation);
        mEnterAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (listEmpty) {
                    if (emptyView != null)
                        emptyView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mExitAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!listEmpty) {
                    if (emptyView != null)
                        emptyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void setItemAnimator(RecyclerView.ItemAnimator animation) {
        list.setItemAnimator(animation);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void animExit(View logo) {
        logo.startAnimation(mExitAnimation);
    }

    private void animEnter(View logo) {
        if (listEmpty)
            logo.startAnimation(mEnterAnimation);
    }


    public void checkIfRecyclerViewIsEmpty() {
        if (emptyView != null) {
            if (mAdapter.getItemCount() == 0) {
                listEmpty = true;
                if (mEnterAnimation != null) {
                    animEnter(emptyView);
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                }
            } else {
                listEmpty = false;
                if (mExitAnimation != null) {
                    animExit(emptyView);
                } else {
                    emptyView.setVisibility(View.GONE);
                }
            }
        }
    }
}