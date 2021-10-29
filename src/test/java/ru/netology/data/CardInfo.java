package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CardInfo {
    private String cardNumber;
    private String year;
    private String month;
    private String owner;
    private String CVC;
}
