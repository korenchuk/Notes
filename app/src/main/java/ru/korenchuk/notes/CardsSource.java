package ru.korenchuk.notes;


public interface CardsSource {

    CardData getCardData(int position);

    int size();

}
