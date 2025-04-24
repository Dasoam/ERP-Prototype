package com.dadash.erpprototype;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ClubMembersFragment extends Fragment {

    private static final String ARG_CLUB_NAME = "clubName";

    private ListView listViewMembers;
    private ArrayAdapter<String> membersAdapter;
    private List<String> clubMembers;
    private FirebaseFirestore db;
    private String clubName;

    public static ClubMembersFragment newInstance(String clubName) {
        ClubMembersFragment fragment = new ClubMembersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CLUB_NAME, clubName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_members, container, false);

        listViewMembers = view.findViewById(R.id.listViewMembers);
        clubMembers = new ArrayList<>();
        membersAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, clubMembers);
        listViewMembers.setAdapter(membersAdapter);

        if (getArguments() != null) {
            clubName = getArguments().getString(ARG_CLUB_NAME);
        }

        db = FirebaseFirestore.getInstance();

        // Fetch club members from Firestore
        fetchClubMembers();

        return view;
    }

    private void fetchClubMembers() {
        db.collection("clubs").whereEqualTo("name", clubName).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Club club = document.toObject(Club.class);
                    clubMembers.addAll(club.getMembers());
                }
                membersAdapter.notifyDataSetChanged();
            } else {
                // Handle error
            }
        });
    }
}
