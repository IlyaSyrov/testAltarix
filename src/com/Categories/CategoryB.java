package com.Categories;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class CategoryB {

    //Задание В1: скобочное выражение
    public static String taskOne(){

        //ввод скобочного выражения
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку со скобками:");
        String input = scanner.nextLine();

        int circle = 0; //кол-во круглых скобок
        int square = 0; //кол-во квадратных скобок
        int figure = 0; //кол-во фигурных скобок

        for (char symbol:input.toCharArray()) {


            //считается кол-во всех скобок по отдельности
            //открытая скобка - кол-во увеличивается, закрытая - уменьшается
            if (symbol == "(".charAt(0)){
                circle++;
            } else if (symbol == ")".charAt(0)){
                circle--;
            } else if (symbol == "[".charAt(0)){
                square++;
            } else if (symbol == "]".charAt(0)){
                square--;
            } else if (symbol == "{".charAt(0)){
                figure++;
            } else if(symbol == "}".charAt(0)){
                figure--;
            }

        }

        //если кол-во закрытых и открытых скобок равно, тогда выражение верное
        if (circle + square + figure == 0){
            return "SUCCESS";
        } else {
            return "FAIL";
        }

    }



    //Задание В2: вывод спиралью и змейкой отсортированного массива
    public static void taskTwo(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность матрицы:");
        int size = scanner.nextInt();
        int[] numbers = new int[size*size];

        for (int i = 0; i < numbers.length-1; i++){
            numbers[i] = new Random().nextInt(10);
        }

        System.out.println(Arrays.toString(numbers));

        numbers = bubbleSort(numbers); //сортировка

        int[][] numbersd = new int[size][size];

        for (int i = 0; i < size; i++) {
            numbersd[i] = Arrays.copyOfRange(numbers, i * numbers.length / size, (i + 1) * numbers.length / size);
        }

        System.out.println("\n" + "вывод спиралью" + "\n");
        spiralPrint(numbersd,size);
        System.out.println("\n" + "вывод змейкой" + "\n");
        snakePrint(numbersd, size);

    }

    private static void spiralPrint(int[][] array, int size){
        int row = 0;
        int col = 0;
        int dx = 1;
        int dy = 0;
        int dirChanges = 0;
        int visits = size;
        int[][] nArray = new int[size][size];


        for (int i = 0; i < size ; i++)
            for (int j = 0; j < size ; j++) {
                nArray[row][col] = array[i][j];
                if (--visits == 0) {
                    visits = size * (dirChanges % 2) +
                            size * ((dirChanges + 1) % 2) -
                            (dirChanges / 2 - 1) - 2;
                    int temp = dx;
                    dx = -dy;
                    dy = temp;
                    dirChanges++;
                }
                col += dx;
                row += dy;
            }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(nArray[i][j] + "\t");
            System.out.println();
        }
    }

    private static void snakePrint(int[][] array, int size) {

        for (int i = 0; i < size; i++) {

            //если строка четная, то прямой вывод
            if (i % 2 == 0){

                for (int j = 0; j < size; j++) {
                    System.out.print(array[i][j] + "\t");
                }
                System.out.println();
            }
            else{

                //если нечетная, то обратный вывод
                for (int j = size-1; j >= 0; j--) System.out.print(array[i][j] + "\t");
                System.out.println();
            }

        }

    }

    private static int[] bubbleSort(int[] array){

        for(int i = array.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if( array[j] > array[j+1] ){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
        return array;
    }


    //Задание В3: поиск кратчайшего пути
    public static void taskThree(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность матрицы:");
        int size = scanner.nextInt();

        int[][] matrix = new int[size][size];
        String[][] charmatrix = new String[size][size];

        for (int i = 0; i < size;i++){
            for (int j=0; j < size;j++){
                matrix[i][j] = new Random().nextInt(10);
            }
        }

        for (int i = 0; i < size;i++){
            for (int j=0; j < size;j++){
                charmatrix[i][j] = String.valueOf(matrix[i][j]);
            }
        }

        for (int i = 0; i < size;i++){
            System.out.println(Arrays.toString(charmatrix[i]));
        }

        //(x,y) - координаты точки, для которой выполняется проверка
        int x = 0;
        int y = 0;

        charmatrix[0][0] = "A";

        //пока x или y не выходят за пределы размеров матрицы
       while ((x != size - 1) | (y != size -1)) {

          if(x == size - 1){

              //если координата x на границе матрицы, то двигаемся вправо
              charmatrix[x][y+1] = "*";
              y = y + 1;

          }
          else if(y == size - 1){

              //если координата y на границе матрицы, то двигаемся вниз
              charmatrix[x+1][y] = "*";
              x = x + 1;

          }
          else{

              int pointOne = matrix[x+1][y]; //точка вниз
              int pointTwo = matrix[x][y+1]; //точка вправо

              if(pointOne < pointTwo){

                  //если точка внизу меньше точки справа, смещаемся вниз
                  charmatrix[x+1][y] = "*";
                  x = x + 1;

              }
              else if (pointOne > pointTwo){

                  //если точка внизу больше точки справа, смещаемся вправо
                  charmatrix[x][y+1] = "*";
                  y = y + 1;

              }
              else if (pointOne == pointTwo){

                  //если точки равны, проверяем соседние точки
                  if(x + 2 > size -1){

                      //если координата x+1 на границе матрицы, то двигаемся вправо
                      charmatrix[x][y+1] = "*";
                      y = y + 1;

                  }
                  else if (y + 2 > size - 1){

                      //если координата y+1 на границе матрицы, то двигаемся вниз
                      charmatrix[x+1][y] = "*";
                      x = x + 1;

                  }
                  else{

                      int pointOneN = matrix[x+2][y];
                      int pointTwoN = matrix[x][y+2];

                      if(pointOneN <= pointTwoN){

                          //если точка (x+2, y) меньше точки (x, y+2) или они равны, то выбираем точку (x+1, y)
                          charmatrix[x+1][y] = "*";
                          x = x + 1;

                      }
                      else if (pointOneN > pointTwoN){

                          //если точка (x+2, y) больше точки (x, y+2), то выбираем точку (x, y+1)
                          charmatrix[x][y+1] = "*";
                          y = y + 1;

                      }
                  }
              }
          }

       }

        charmatrix[size-1][size-1] = "B";

        System.out.println();

        for (int i = 0; i < size;i++){
            System.out.println(Arrays.toString(charmatrix[i]));
        }

    }


}


