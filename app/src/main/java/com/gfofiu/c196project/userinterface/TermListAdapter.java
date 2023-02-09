package com.gfofiu.c196project.userinterface;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gfofiu.c196project.R;
import com.gfofiu.c196project.entities.Term;

import java.util.List;

public class TermListAdapter extends RecyclerView.Adapter<TermListAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termListView;
        private final TextView termListView2;
        private final TextView termListView3;

        private TermViewHolder(View itemview) {
            super(itemview);
            termListView = itemview.findViewById(R.id.textViewTerm);
            termListView2 = itemview.findViewById(R.id.textViewTermStart);
            termListView3 = itemview.findViewById(R.id.textViewTermEnd);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetails.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("name", current.getTermTitle());
                    intent.putExtra("term start", current.getTermStart());
                    intent.putExtra("term end", current.getTermEnd());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list, parent, false);
        return new TermViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull TermListAdapter.TermViewHolder holder, int position) {
        if (mTerms != null) {
            Term current = mTerms.get(position);
            String name = current.getTermTitle();
            String start = current.getTermStart();
            String end = current.getTermEnd();
            holder.termListView.setText(name);
            holder.termListView2.setText(start);
            holder.termListView3.setText(end);
        } else {
            holder.termListView.setText("No Term Names");
        }
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }

    public void setTerms(List<Term> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }
}
