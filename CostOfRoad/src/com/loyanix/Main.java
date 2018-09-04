package com.loyanix;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int [] priceRoad = new int[10];     // стоимости пунктов дорог
        int [] inputCoin = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};  // "кошельк", монеты отданые водителю
        int [] outputCoin = new int[10];    //выходной массив в правильной сортировке монет относительно стоимости дорои
/*----------Заполнение исходного массива(стоимости каждого пункта дороги) рандомными стоимостями, сумма всех больше 55---------*/
        int summ=0;
        while (summ<=55) {
            summ=0;
            for (int i = 0; i < 10; i++) {
                priceRoad[i] = (int) (Math.random() * 10)+1;
                summ += priceRoad[i];
            }
        }
/*------------------Нахождение одинаковых стоимостей дороги и имеющихся монет без долга/переплаты--------------*/
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j<10; j++) {
                if (priceRoad[i] == inputCoin[j]) {
                    outputCoin[i] = inputCoin[j];       //выводим в соответсвующую позицию монеты относительно исходных стоимостей
                    inputCoin[j] = 0;                   //монеты которые были отсортированы(будут отданы в соответсвующем пукте дороги) убираем из исходного массива
                }
            }
        }
/*----------Нахождение самых близких стоимостей(без большой переплаты) из оставшихся монет в соответсвии с оставшимися пунктами--------*/
        int station = 0;
        for (int pos = 0; pos < 10; pos++) {
            if(outputCoin[pos] == 0){   // нахождение неоплаченых пунктов
                int delta , previousDelta = 10;
                for(int t = 0; t < 10; t++){
                    delta = Math.abs(priceRoad[pos] - inputCoin[t]);    //нахождене разности между стоимостью пункта и оставшимися монетами
                    if(delta<previousDelta){        // если найдена разность меньшая нежели была раньше, запоминаем ее
                        previousDelta = delta;
                        station = t;                // запоминаем положениее имеющейся монеты в нашем "кошельке"
                    }
                }
                outputCoin[pos] = inputCoin[station];   //записываем найденую подходящую монету
                inputCoin[station] = 0;     // использованую монету обнуляем
            }
        }
/*-----------------------------------------------------------------------------------*/
        System.out.println("Стоимость оплаты на кождом пункте дороги " + Arrays.toString(priceRoad) + " Сумарная стоимость = " + summ);
        System.out.println("Монеты в соответствии со стоимостью каждого пункта дороги " + Arrays.toString(outputCoin) + " Общая сумма = 55");
        int debt = 0;
        for(int q = 0; q<10; q++){                      //расчет долга
            int diff = priceRoad[q] - outputCoin[q];    //находим разность между требуддемой монетой и предоставленной
            if( diff < 0) debt +=0;                     //если была предоставленна монета большего номинала, то остаток сгорает
            if( diff > 0) debt +=diff;                  // есди меньшего номинала то насчитывается долг
        }
        System.out.println("Идеальный(минимальный) долг " + (summ - 55) + "\tРеальный долг " + debt);
    }
}