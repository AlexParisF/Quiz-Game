package edu.uoc.uocquizgame;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.uoc.uocquizgame.placeholder.PlaceholderContent.PlaceholderItem;
import edu.uoc.uocquizgame.databinding.FragmentUnitBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyUnitRecyclerViewAdapter extends RecyclerView.Adapter<MyUnitRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;
    private Context context;

    public MyUnitRecyclerViewAdapter(List<PlaceholderItem> items, Context context) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentUnitBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mItem = mValues.get(position);
        holder.mIcon.setImageResource(mValues.get(position).icon);
        holder.mUnitName.setText(mValues.get(position).description);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(),
                        "Press unit:" + mValues.get(position).description,
                        Toast.LENGTH_LONG).show();
            } });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mIcon;
        public final TextView mUnitName;
        public PlaceholderItem mItem;
        public final CardView mCardView;

        public ViewHolder(FragmentUnitBinding binding) {
            super(binding.getRoot());
            mIcon = binding.imgIcon;
            mUnitName = binding.txtUnitName;
            mCardView=binding.cardView;
        }

        @Override public String toString() {
            return super.toString() + " '" + mUnitName.getText() + "'";
        }
    }
}