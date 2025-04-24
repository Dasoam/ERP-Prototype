package com.dadash.erpprototype;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ClubsFragment extends Fragment {

    private ListView listViewClubs;
    private ArrayAdapter<String> clubsAdapter;
    private List<String> clubNames;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);

        listViewClubs = view.findViewById(R.id.listViewClubs);
        clubNames = new ArrayList<>();
        clubsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, clubNames);
        listViewClubs.setAdapter(clubsAdapter);

        db = FirebaseFirestore.getInstance();

        // Fetch clubs from Firestore
        fetchClubs();

        listViewClubs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedClubName = clubNames.get(position);

                // Pass the selected club name to the ClubMembersFragment
                ClubMembersFragment clubMembersFragment = ClubMembersFragment.newInstance(selectedClubName);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, clubMembersFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    private void fetchClubs() {
        db.collection("clubs").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Club club = document.toObject(Club.class);
                    clubNames.add(club.getName());
                }
                clubsAdapter.notifyDataSetChanged();
            } else {
                // Handle error
            }
        });
    }
}
