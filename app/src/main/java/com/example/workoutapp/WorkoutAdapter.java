package com.example.workoutapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder> {
    private final OnClickListener onClickListener;
    private ArrayList<ExercisesList> exercisesList;

    public WorkoutAdapter(ArrayList<ExercisesList> exercisesList,OnClickListener onClickListener){
        this.exercisesList = exercisesList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.exerciseName.setText(exercisesList.get(position).getExerciseName());
        holder.exerciseDescription.setText(exercisesList.get(position).getExerciseDescription());
    }

    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseName,exerciseDescription;
        public MyViewHolder(@NonNull View view) {
            super(view);
            exerciseName = view.findViewById(R.id.workoutNameTextView);
            exerciseDescription = view.findViewById(R.id.workoutDescriptionTextView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
