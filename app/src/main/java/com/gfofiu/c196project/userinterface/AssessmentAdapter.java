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
import com.gfofiu.c196project.entities.Assessments;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        public final TextView courseAssessmentName;
        public final TextView courseAssessmentStart;
        public final TextView courseAssessmentDue;

        private AssessmentViewHolder(View itemview) {
            super(itemview);
            courseAssessmentName = itemview.findViewById(R.id.assessmentName);
            courseAssessmentStart = itemview.findViewById(R.id.assessmentStartDate);
            courseAssessmentDue = itemview.findViewById(R.id.assessmentEndDate);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final com.gfofiu.c196project.entities.Assessments current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("assessmentName", current.getAssessmentName());
                    intent.putExtra("assessmentStart", current.getAssessmentStart());
                    intent.putExtra("assessmentEnd", current.getAssessmentDue());
                    intent.putExtra("courseID", current.getCourseID());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<com.gfofiu.c196project.entities.Assessments> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context) {
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.course_assessment_item, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessments!=null){
            com.gfofiu.c196project.entities.Assessments current=mAssessments.get(position);
            String name=current.getAssessmentName();
            String start=current.getAssessmentStart();
            String due=current.getAssessmentDue();
            holder.courseAssessmentName.setText(name);
            holder.courseAssessmentStart.setText(start);
            holder.courseAssessmentDue.setText(due);
        }
        else {
            holder.courseAssessmentName.setText("No Assessment Names");
        }
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    public void setAssessments(List<com.gfofiu.c196project.entities.Assessments> assessments) {
        mAssessments = assessments;
        notifyDataSetChanged();
    }
}
