package com.dadash.erpprototype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {

    private List<FacultyItem> facultyItems;

    public FacultyAdapter(List<FacultyItem> facultyItems) {
        this.facultyItems = facultyItems;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_faculty, parent, false);
        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder holder, int position) {
        FacultyItem facultyItem = facultyItems.get(position);
        holder.name.setText(facultyItem.getName());
        holder.location.setText(facultyItem.getLocation());
        holder.contact.setText(facultyItem.getContact());
    }

    @Override
    public int getItemCount() {
        return facultyItems.size();
    }

    static class FacultyViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, contact;

        FacultyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.faculty_name);
            location = itemView.findViewById(R.id.faculty_location);
            contact = itemView.findViewById(R.id.faculty_contact);
        }
    }
}
