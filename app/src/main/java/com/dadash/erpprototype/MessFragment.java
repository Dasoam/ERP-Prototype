package com.dadash.erpprototype;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MessFragment extends Fragment {

    private RecyclerView recyclerViewMenu;
    private TabLayout tabLayoutDays;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mess, container, false);

        recyclerViewMenu = view.findViewById(R.id.recyclerViewMenu);
        tabLayoutDays = view.findViewById(R.id.tabLayoutDays);

        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(getContext()));

        menuItems = new ArrayList<>();
        menuAdapter = new MenuAdapter(menuItems);
        recyclerViewMenu.setAdapter(menuAdapter);

        db = FirebaseFirestore.getInstance();

        // Set up tabs for the 7-day menu
        setupTabLayout();

        // Load the menu for the default tab (e.g., Today)
        loadMenuForDay("Mon");  // Load default day (e.g., Monday)

        return view;
    }

    private void setupTabLayout() {
        // Add 7-day tabs (you can customize the day names)
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (String day : days) {
            tabLayoutDays.addTab(tabLayoutDays.newTab().setText(day));
        }

        tabLayoutDays.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String selectedDay = tab.getText().toString();
                loadMenuForDay(selectedDay);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void loadMenuForDay(String day) {
        db.collection("mess_menu")
                .document(day)  // Assuming each day's menu is stored as a document named after the day
                .collection("meals")  // Collection within the day's document
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        menuItems.clear();
                        QuerySnapshot querySnapshot = task.getResult();
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            MenuItem menuItem = document.toObject(MenuItem.class);
                            menuItems.add(menuItem);
                        }
                        menuAdapter.notifyDataSetChanged();
                    } else {
                        // Handle the error
                        Log.d(day, "Menu Will be added");
                    }
                });
    }
}
