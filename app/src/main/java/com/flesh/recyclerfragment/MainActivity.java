package com.flesh.recyclerfragment;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.flesh.library.RecyclerFragment;
import com.flesh.library.itemAnimators.ScaleInOutItemAnimator;
import com.flesh.library.itemAnimators.SlideInOutBottomItemAnimator;
import com.flesh.library.itemAnimators.SlideScaleInOutRightItemAnimator;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MyFragment())
                    .commit();
        }
    }


    public static class MyFragment extends RecyclerFragment {

        private String[] mDataSet = {"boom"};
        private SimpleStringRecyclerViewAdapter adapter;

        public MyFragment() {
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            //set the Layout manager
//            setLayoutManger(new LinearLayoutManager(getActivity()));
            adapter = new SimpleStringRecyclerViewAdapter(mDataSet);
            //Set Adapter
            setRecyclerAdapter(adapter);
//            // Set empty_view
//            setEmptyView(R.layout.empty_view);
//            setItemAnimator(new SlideScaleInOutRightItemAnimator(getRecyclerView()));
//            setEmptyViewAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            // Inflate the menu; this adds items to the action bar if it is present.
            inflater.inflate(R.menu.menu_main, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_add) {
                adapter.addItem("Item " + adapter.getItemCount(), adapter.getItemCount());
                return true;
            }
            if (id == R.id.action_sub) {
                if (adapter.getItemCount() != 0) {
                    adapter.removeItem(adapter.getItemCount() - 1);
                }
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


    }
}
