package ru.korenchuk.notes;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class CardSourceImpl implements CardsSource {

    private List<CardData> dataSource;
    private Resources resources;


    public CardSourceImpl(){
        dataSource = new ArrayList<>();
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
    @Override
    public void setNewData(List<CardData> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CardData> getCardData() {
        return dataSource;
    }
}
