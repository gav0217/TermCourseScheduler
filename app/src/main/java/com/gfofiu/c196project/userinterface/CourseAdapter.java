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
import com.gfofiu.c196project.entities.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    class CourseViewHolder extends RecyclerView.ViewHolder {
        public final TextView courseItemView;


        private CourseViewHolder(View itemview) {
            super(itemview);
            courseItemView = itemview.findViewById(R.id.courseItemtextView);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = mCourse.get(position);
                    Intent intent = new Intent(context, CourseDetails.class);
                    intent.putExtra("id", current.getCourseID());
                    intent.putExtra("course", current.getCourseTitle());
                    intent.putExtra("course start", current.getCourseStart());
                    intent.putExtra("course end", current.getCourseEnd());
                    intent.putExtra("mentor name", current.getMentorsName());
                    intent.putExtra("mentor phone", current.getMentorsNumber());
                    intent.putExtra("mentor email", current.getMentorsEmail());
                    intent.putExtra("term", current.getTermID());
                    //intent.putExtra("note", current.getNote);
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Course> mCourse;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourse != null) {
            Course current = mCourse.get(position);
            String name = current.getCourseTitle();
            holder.courseItemView.setText(name);
        } else {
            holder.courseItemView.setText("No Term Names");
        }
    }

    @Override
    public int getItemCount() {
        return mCourse.size();
    }

    public void setCourse(List<Course> course) {
        mCourse = course;
        notifyDataSetChanged();
    }
}
