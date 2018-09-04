package com.loyanix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String startPosition = "slot_a";       //начальная машина с плитами
    private static String bufferposition = "slot_b";      //промежуточная машина
    private static String finalPosition = "slot_c";       //машина куда плиты должны быть переложены
    private static int StepsCounter = 0;
    public static void transferPlates(int quantity, String start, String buffer, String finall){
        if(quantity > 0){     // функция рекурсивная, условие выхода из рекурсии это закончились плиты
            transferPlates(quantity - 1,start,finall,buffer);     //перекладывает n-1 плит на промежуточную машину
            System.out.println("#" + quantity + " " + start + " -> " + finall);
            StepsCounter++;     // увеличиваем счетчик ходов
            transferPlates(quantity -1, buffer, start, finall);   // перекладывет n-1 плит на нужную машну машину
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input quantity of plates -> ");
        int platesQuantity = Integer.parseInt(reader.readLine());
        transferPlates(platesQuantity, startPosition, bufferposition, finalPosition);
        System.out.println("Steps:" + StepsCounter);
    }
}