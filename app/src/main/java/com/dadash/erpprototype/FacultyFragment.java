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
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView recyclerViewFaculty;
    private FacultyAdapter facultyAdapter;
    private List<FacultyItem> facultyItems;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        recyclerViewFaculty = view.findViewById(R.id.recyclerViewFaculty);
        recyclerViewFaculty.setLayoutManager(new LinearLayoutManager(getContext()));

        facultyItems = new ArrayList<>();
        facultyAdapter = new FacultyAdapter(facultyItems);
        recyclerViewFaculty.setAdapter(facultyAdapter);

        // Load faculty data
        loadFacultyData();

        return view;
    }

    private void loadFacultyData() {
        db.collection("faculty").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                facultyItems.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String name = document.getString("name");
                    String location = document.getString("location");
                    String contact = document.getString("contact");
                    FacultyItem facultyItem = new FacultyItem(name, location, contact);
                    facultyItems.add(facultyItem);
                }
                facultyAdapter.notifyDataSetChanged();
            } else {
                // Handle errors
            }
        });
    }
}
