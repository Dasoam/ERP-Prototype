package com.dadash.erpprototype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventsMessagesAdapter extends RecyclerView.Adapter<EventsMessagesAdapter.ViewHolder> {

    private List<EventMessage> eventMessagesList;

    public EventsMessagesAdapter(List<EventMessage> eventMessagesList) {
        this.eventMessagesList = eventMessagesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventMessage eventMessage = eventMessagesList.get(position);
        holder.title.setText(eventMessage.getTitle());
        holder.message.setText(eventMessage.getMessage());
        holder.postedBy.setText(eventMessage.getPostedBy());
        holder.timestamp.setText(eventMessage.getFormattedTimestamp());
    }

    @Override
    public int getItemCount() {
        return eventMessagesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, message, postedBy, timestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);
            postedBy = itemView.findViewById(R.id.postedBy);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }
}
