package com.simplecity.amp_library.ui.modelviews;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.simplecityapps.recycler_adapter.adapter.ViewModelAdapter;
import com.simplecityapps.recycler_adapter.model.BaseViewModel;
import com.simplecityapps.recycler_adapter.model.ViewModel;
import com.simplecityapps.recycler_adapter.recyclerview.BaseViewHolder;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;
import static com.simplecity.amp_library.R.layout.recycler_header;
import static com.simplecity.amp_library.ui.adapters.ViewType.HORIZONTAL_RECYCLERVIEW;
import static com.simplecity.amp_library.utils.ThemeUtils.themeRecyclerView;

public class HorizontalRecyclerView extends BaseViewModel<HorizontalRecyclerView.ViewHolder> {

    public ViewModelAdapter ViewModelAdapter;

    public HorizontalRecyclerView() {
        this.ViewModelAdapter = new ViewModelAdapter();
    }

    public void setItems(List<ViewModel> items) {
        ViewModelAdapter.setItems(items);
    }

    public int getCount() {
        return ViewModelAdapter.getItemCount();
    }

    @Override
    public int getViewType() {
        return HORIZONTAL_RECYCLERVIEW;
    }

    @Override
    public int getLayoutResId() {
        return recycler_header;
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);

        ((RecyclerView) holder.itemView).setAdapter(ViewModelAdapter);
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        return new ViewHolder(createView(parent));
    }

    public static class ViewHolder extends BaseViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), HORIZONTAL, false);
            layoutManager.setInitialPrefetchItemCount(4);
            ((RecyclerView) itemView).setLayoutManager(layoutManager);
            //noinspection RedundantCast
            ((RecyclerView) itemView).setNestedScrollingEnabled(false);
            themeRecyclerView(((RecyclerView) itemView));

            ((RecyclerView) itemView).addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    themeRecyclerView(recyclerView);
                    super.onScrollStateChanged(recyclerView, newState);
                }
            });
        }

        @Override
        public String toString() {
            return "HorizontalRecyclerView.ViewHolder";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorizontalRecyclerView that = (HorizontalRecyclerView) o;

        return ViewModelAdapter != null ? ViewModelAdapter.equals(that.ViewModelAdapter) : that.ViewModelAdapter == null;
    }

    @Override
    public int hashCode() {
        return ViewModelAdapter != null ? ViewModelAdapter.hashCode() : 0;
    }
}