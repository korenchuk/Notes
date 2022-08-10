package ru.korenchuk.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ListFragmentV2 extends Fragment {


    public static ListFragmentV2 newInstance() {
        return new ListFragmentV2();
    }

    public ListFragmentV2() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_v2, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);

        CardsSource data = new CardSourceImpl(getResources()).init();
        initRecyclerView(recyclerView, data);

        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, CardsSource data) {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ListAdapterV2 listAdapter = new ListAdapterV2(data);
        recyclerView.setAdapter(listAdapter);


        listAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getContext(), String.format("%s - %d",
                //        ((TextView) view).getText(), position), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), String.format("Позиция - %d",
                        position), Toast.LENGTH_SHORT).show();
            }
        });
    }

}