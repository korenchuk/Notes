package ru.korenchuk.notes;


import java.util.List;

public interface CardsSource {

    CardData getCardData(int position);
    int size();
    void deleteCardData(int position);
    void updateCardData(int position, CardData cardData);
    void addCardData(CardData cardData);
    void clearCardData();
    void setNewData(List<CardData> dataSource);
    List<CardData> getCardData();

}
