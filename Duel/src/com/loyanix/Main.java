package com.loyanix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("");
        double [] startProbability = {0, 0};    // входные данные, начальные вероятноти
        double [] stepProbability = {0, 0};         // шаг увеличения вероятности на каждом раунде дуэли
        System.out.println("Ведите начальную вероятность 1 игрока -> ");
        startProbability[0] = Double.valueOf(bufferedReader.readLine());
        System.out.println("Ведите начальную вероятность 2 игрока -> ");
        startProbability[1] = Double.valueOf(bufferedReader.readLine());
        System.out.println("Какой игрок первый ходит (1/2) ?");
        int playerTurn  = Integer.valueOf(bufferedReader.readLine());
        int turnHigh = (playerTurn-1)%2;
        int turnLow = (playerTurn)%2;
        double [][] probabilityOnStep = {{startProbability[0],0,0,0,0,0,0,0,0,0,1},   // вероятности попадания первого игрока на каждом шаге
                {startProbability[1],0,0,0,0,0,0,0,0,0,1}}; // вероятности попадания второго игрока на каждом шаге
        int t=1, k=1;
        for(int i = 19; i > 0; i--){       // пересчитываем вероятности для каздого игрока, учитывая то что при каждом ходе противника
                                           // расстояние меняется и соответственно менется и вероятность попадение
            if (i % 2 == 0){               // для первого ходившего игрока
                stepProbability[turnHigh] = ( probabilityOnStep[turnHigh][10] - probabilityOnStep[turnHigh][t-1])/i;     // расчет шага увеличения вероятности
                probabilityOnStep[turnHigh][t]= probabilityOnStep[turnHigh][t-1] + stepProbability[turnHigh];            // расчет вероятности непосредственно на каждом шагу
                t++;
            } else if (i % 2 == 1) {       // для второго ходившего игрока
                stepProbability[turnLow] = (probabilityOnStep[turnLow][10]-probabilityOnStep[turnLow][k-1])/i;
                probabilityOnStep[turnLow][k]= probabilityOnStep[turnLow][k-1] + stepProbability[turnLow];
                k++;
            }
        }
        System.out.print("Первый игрок ");                                                                                  //Вывод вероятностей игрока на каждом шаге
        for (double probability:probabilityOnStep[0]) System.out.print(String.format("%.2f", probability) + "\t");
        System.out.print("\nВторой игрок ");
        for (double probability:probabilityOnStep[1]) System.out.print(String.format("%.2f", probability) + "\t");
        System.out.print("\n");
        int counter = 0;
        while(probabilityOnStep[1][counter+1] < 0.5) counter++;
        System.out.println("Игрок 1 с начальной вероятностью " + probabilityOnStep[0][0] +
                            ", должен произвести выстрел после " + (counter + turnLow) +
                               " шага, вероятность попадения " + String.format("%.2f",probabilityOnStep[0][counter + turnLow]));
        counter = 0;
        while(probabilityOnStep[0][counter+1] < 0.5) counter++;
        System.out.println("Игрок 2 с начальной вероятностью " + probabilityOnStep[1][0] +
                            ", должен произвести выстрел после " + (counter + turnHigh) +
                               " шага, вероятность попадения " + String.format("%.2f",probabilityOnStep[1][counter + turnHigh]));
    }
}
