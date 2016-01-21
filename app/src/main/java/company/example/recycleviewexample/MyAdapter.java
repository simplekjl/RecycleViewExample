package company.example.recycleviewexample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import company.example.recycleviewexample.models.Contact;

/**
 * Created by admin on 1/19/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{

    private List<Contact> mDataset;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTextView;
        public TextView mSurName;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.info_text);
            mSurName =  (TextView)v.findViewById(R.id.surname);
            v.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),String.valueOf(getPosition()),Toast.LENGTH_LONG).show();
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Contact> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int position = viewType;
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        v.setClickable(true);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getName());
        Log.d("DATA", String.valueOf(mDataset.get(position).getPhoneNumber()));
        holder.mSurName.setText(String.valueOf(mDataset.get(position).getPhoneNumber()));//mDataset.get(position).getPhoneNumber());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}