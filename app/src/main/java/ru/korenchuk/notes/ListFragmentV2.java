package ru.korenchuk.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class ListFragmentV2 extends Fragment {

    private CardsSource data;
    private ListAdapterV2 adapter;
    private RecyclerView recyclerView;
    private static final int DURATION = 1000;
    private static final String KEY = "KEY";
    private SharedPreferences sharedPreferences;

    public static ListFragmentV2 newInstance() {
        return new ListFragmentV2();
    }

    public ListFragmentV2() {
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.cards_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        switch (item.getItemId()){
            case R.id.action_add:
                data.addCardData(new CardData("Заметка " + (data.size() + 1),
                        "Описание заметки " + (data.size() + 1)));
                adapter.notifyItemInserted(data.size());
                recyclerView.scrollToPosition(data.size());
                recyclerView.smoothScrollToPosition(data.size() - 1);

                String jsonCardDataAfterAdd = new GsonBuilder().create().toJson(data.getCardData());
                sharedPreferences.edit().putString(KEY, jsonCardDataAfterAdd).apply();
                return true;
            case R.id.action_clear:
                data.clearCardData();
                adapter.notifyDataSetChanged();
                String jsonCardDataAfterClear = new GsonBuilder().create().toJson(data.getCardData());
                sharedPreferences.edit().putString(KEY, jsonCardDataAfterClear).apply();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_v2, container, false);

        initView(view);
        setHasOptionsMenu(true);
        return view;
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycler_view_lines);
        data = new CardSourceImpl();
        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ListAdapterV2(data, this);
        recyclerView.setAdapter(adapter);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(DURATION);
        defaultItemAnimator.setRemoveDuration(DURATION);
        recyclerView.setItemAnimator(defaultItemAnimator);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedData = sharedPreferences.getString(KEY, null);
        if (savedData == null){
            Toast.makeText(getContext(), "Список заметок пуст", Toast.LENGTH_LONG).show();
        }
        else {
            try {
                Type type = new TypeToken<List<CardData>>() {}.getType();
                adapter.setNewData(new GsonBuilder().create().fromJson(savedData, type));
            }
            catch (Exception e){
                Toast.makeText(getContext(), "Ошибка", Toast.LENGTH_LONG).show();
            }
        }

        adapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), String.format("Выбрана заметка %d",
                        position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        switch (item.getItemId()){
            case R.id.action_update:
                CardData cardData = data.getCardData(position);
                data.updateCardData(position, new CardData(cardData.getTitle(),
                        "Измененная заметка"));
                adapter.notifyItemChanged(position);
                return true;
            case R.id.action_delete:
                data.deleteCardData(position);
                adapter.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }
}