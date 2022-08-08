package ru.korenchuk.notes;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class CardSourceImpl implements CardsSource {

    private List<CardData> dataSource;
    private Resources resources;


    public CardSourceImpl(Resources resources){
        this.resources = resources;
        dataSource = new ArrayList<>(7);
    }

    public CardSourceImpl init(){

        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);

        return this;

    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
