package com.Categories;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CategoryA {

    //Задание А1: принадлежность треугольнику
    public static String taskOne(){


        double[] pointA = new double[2];
        double[] pointB = new double[2];
        double[] pointC = new double[2];
        double[] pointD = new double[2];
        String[] pointLetters = {"A","B","C","D"};

        ArrayList<double[]> points = new ArrayList<>();
        points.add(pointA);
        points.add(pointB);
        points.add(pointC);
        points.add(pointD);

        Scanner scanner = new Scanner(System.in);

        //ввод координат точек
        for (int i = 0; i < 4; i++){
            System.out.println("Введите данные для точки " + pointLetters[i] + ":");
            for (int j = 0; j < 2; j++){
                points.get(i)[j] = scanner.nextDouble();
            }
        }

        //проверка на принадлежность методом площадей
        double ABC = Math.abs (points.get(0)[0] * (points.get(1)[1] - points.get(2)[1]) + points.get(1)[0] * (points.get(2)[1] - points.get(0)[1]) + points.get(2)[0] * (points.get(0)[1] - points.get(1)[1]));
        double ABP = Math.abs (points.get(0)[0] * (points.get(1)[1] - points.get(3)[1]) + points.get(1)[0] * (points.get(3)[1] - points.get(0)[1]) + points.get(3)[0] * (points.get(0)[1] - points.get(1)[1]));
        double APC = Math.abs (points.get(0)[0] * (points.get(3)[1] - points.get(2)[1]) + points.get(3)[0] * (points.get(2)[1] - points.get(0)[1]) + points.get(2)[0] * (points.get(0)[1] - points.get(3)[1]));
        double PBC = Math.abs (points.get(3)[0] * (points.get(1)[1] - points.get(2)[1]) + points.get(1)[0] * (points.get(2)[1] - points.get(3)[1]) + points.get(2)[0] * (points.get(3)[1] - points.get(1)[1]));

        boolean isInTriangle = ABP + APC + PBC == ABC;

        if (isInTriangle)
            return "IN";
        else
            return "OUT";

    }

    //Задание А2: разница диагоналей матрицы
    public static double taskTwo(){
        double[][] matrix = new double[3][3];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++){

            for (int j = 0; j < 3; j++){
                System.out.println("Введите данные для точки [" + (i+1) + "," + (j+1) + "]");
                matrix[i][j] = scanner.nextDouble();
            }

        }


        return (matrix[0][0] + matrix[2][2]) - (matrix[0][2] + matrix[2][0]);
    }

    //Задание А3: Лестница
    public static void taskThree(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число для лесенки:");
        int count = scanner.nextInt();

        for (int i=1;i<=count; i++){
            StringBuilder out = new StringBuilder();

            for (int j=1;j<=i;j++) {
                out.append("#");
            }

            System.out.println(out);
        }

    }

    //Задание А4: поиск пар
    public static int taskFour(){

        int count = 0; //счетчик пар элементов

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество элементов:");
        int countElements = scanner.nextInt();

        // ввод делителя
        System.out.println("Введите делитель:");
        int div = scanner.nextInt();

        int[] elements = new int[countElements]; //счетчик

        //ввод массива цисел
        for (int i = 1; i <= countElements;i++){
            System.out.printf("Введите %d-ый элемент \n",i );
            elements[i-1] = scanner.nextInt();
        }

        for (int element : elements) {
            for (int element1 : elements) {

                //если текущий елемент меньше соседнего
                if (element < element1) {
                    //тогда проверяется, делится ли нацело их сумма на делитель
                    if ((element + element1) % div == 0) {
                        count++; //если да, увеличиваем счетчик
                    }
                }

            }
        }

        return count;
    }


    //Задание А5: поиск окна
    public static void taskFive(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность матрицы:");
        System.out.println("Введите кол-во строк");
        int sizeN = scanner.nextInt();
        System.out.println("Введите кол-во столбцов");
        int sizeM = scanner.nextInt();

        int[][] matrix = new int[sizeN][sizeM]; //матрица

        for (int i = 0; i < sizeN;i++){
            for (int j=0; j < sizeM;j++){
                matrix[i][j] = new Random().nextInt(10);
            }
        }

        System.out.println("Введите размерность окна:");
        System.out.println("Введите кол-во строк");
        int sizeA = scanner.nextInt();
        System.out.println("Введите кол-во столбцов");
        int sizeB = scanner.nextInt();

        int[][] window = new int[sizeA][sizeB]; //окно, которое нужно найти

        if(sizeA >= sizeN || sizeB >= sizeM) {

            //если размер окна больше матрицы - ошибка
            System.out.println("Окно больше матрицы: \n FAIL \n");
        }
        else{

            //если размер окна меньше матрицы, то вводим элементы окна
        for (int i = 0; i < sizeA;i++){
            System.out.printf("Введите  элементы %d-й строки \n",i+1 );
            for (int j=0; j < sizeB;j++){
                System.out.printf("Введите %d-ый элемент \n",j+1 );
                window[i][j] = scanner.nextInt();
            }
        }

        System.out.println("матрица: \n");
        for(int i = 0; i < sizeN; i++){
            for(int j = 0; j < sizeM; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n окно: \n");
        for(int i = 0; i < sizeA; i++){
            for(int j = 0; j < sizeB; j++) {
                System.out.print(window[i][j] + " ");
            }
            System.out.println();
        }



            int flag = 2; //флаг совпадения
            int[] point = new int[2]; //точка, в которой находится курсор

            for (int i = 0; i <= sizeN - sizeA; i++) {
                //если найдено совпадение выходим из поиска
                if (flag == 1) {
                    break;
                }
                else{
                    //если нет совпадений выставляем флаг и продолжаем поиск
                    flag = 2;
                }
                for (int j = 0; j <= sizeM - sizeB; j++) {
                    if (flag == 1) {
                        //если найдено совпадение выходим из поиска
                        break;
                    }
                    //если найден первый элемент окна, начинается проверка совпадений элементов
                    if (matrix[i][j] == window[0][0]) {
                        for (int a = 0; a < sizeA; a++) {
                            if (flag == 0) {
                                //окно не найдено, выход из цикла
                                break;
                            }
                            else{
                                //найдено окно, устанавливается флаг
                                flag = 1;
                            }
                            for (int b = 0; b < sizeB; b++) {
                                if (matrix[i + a][j + b] != window[a][b]) {
                                    //если, хотя бы один элемент не совпадает, выходим из цикла
                                    flag = 0; //сбрасиваем флаг
                                    break;
                                }
                            }

                            point[0] = i;
                            point[1] = j;
                        }
                    }

                }
            }

            if (flag == 1) {
                System.out.println("\n (" + point[0] + "," + point[1] + ")\n");
            } else if (flag == 2) {
                System.out.println("\n FAIL \n");
            }
        }
    }
}
