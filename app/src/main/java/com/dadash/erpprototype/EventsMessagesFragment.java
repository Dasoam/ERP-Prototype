package com.dadash.erpprototype;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class EventsMessagesFragment extends Fragment {

    private RecyclerView recyclerViewEventsMessages;
    private EventsMessagesAdapter adapter;
    private List<EventMessage> eventMessagesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_messages, container, false);

        recyclerViewEventsMessages = view.findViewById(R.id.recyclerViewEventsMessages);
        recyclerViewEventsMessages.setLayoutManager(new LinearLayoutManager(getContext()));

        eventMessagesList = new ArrayList<>();
        adapter = new EventsMessagesAdapter(eventMessagesList);
        recyclerViewEventsMessages.setAdapter(adapter);

        loadEventMessagesFromFirestore();

        return view;
    }

    private void loadEventMessagesFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("eventsMessages")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        eventMessagesList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            EventMessage eventMessage = document.toObject(EventMessage.class);
                            eventMessagesList.add(eventMessage);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        // Handle errors here
                    }
                });
    }
}
