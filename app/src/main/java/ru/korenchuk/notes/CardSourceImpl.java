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

        for (int i = 0; i < 7; i++){
            dataSource.add(new CardData(titles[i], descriptions[i]));
        }
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

    @Override
    public void deleteCardData(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateCardData(int position, CardData cardData) {
        dataSource.set(position, cardData);
    }

    @Override
    public void addCardData(CardData cardData) {
        dataSource.add(cardData);
    }

    @Override
    public void clearCardData() {
        dataSource.clear();
    }
}
