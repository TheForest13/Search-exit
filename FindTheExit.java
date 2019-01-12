package JavaCode.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FindTheExit {
    private MatrixElements matrix[][];
    private int x1;
    private int y1;
    public int x2;
    public int y2;
    private Cell currentCell = new Cell(x1, y1);

    //TODO: ЧЕТКО
    public MatrixElements[][] getMatrix() {
        return matrix;
    }

//TODO: ЧЕТКО
    FindTheExit(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        matrix = new MatrixElements[][];
    }

    /**
     * основная логика поиска пути
     */
    public void findExit(){
        Cell currentCell = new Cell(x1, y1);    //начальная ячейка
        matrix[currentCell.getX()][currentCell.getY()] = MatrixElements.VISI;   //отмечаем как посещенная
        Cell neighbourCell; //ячейка сосед
        List<Cell> listNeighbours;  //Список соседей
        Stack<Cell> stack = new Stack<>();

        do {
            listNeighbours = getNeighbours(currentCell);    // Проверяем соседей (количество, расположение)
            if (listNeighbours.size() != 0) {   //Если у клетки есть непосещённые соседи
                int randN = (int) (Math.random() * listNeighbours.size());  //Рандомно выбираем куда пойдем
                neighbourCell = listNeighbours.get(randN);  //List возращает случчайно выбранного соседа
                stack.push(currentCell);    //Помещаем ячейку, где возник выбор пути
                removePoint(currentCell, neighbourCell);    //Смена позиции
                currentCell = neighbourCell;    //Произошло перемещение ячейки
                matrix[currentCell.getX()][currentCell.getY()] = MatrixElements.VISI;   //Помечаем как посещенную
            } else if (!stack.empty()) {    //Если стек НЕ пуст
                currentCell = stack.pop();  //Достаем ячейку, на которой мы делали выбор
            } else {
                //Если нет соседей и точек в стеке, но не все точки посещены
                //выбираем случайную из непосещенных
                System.out.println("Выхода нет((\n");
                break;
            }
        } while (isExit(!isExit(currentCell)));

    }

    /**
     *метод, который проверяет, является ли переданная ячейка Cell(напоминаю,
     *что Cell содержит координаты Х и У) выходом из лабиринта.
     *Метод возвращает true, если выход найден
     */
    private boolean isExit(Cell cell){
        
        return true;
    }

    /**
     * в качестве параметра вы передаёте ячейку.
     * Метод проводит некоторые действия и выясняет,
     * какие НЕПОСЕЩЁННЫЕ соседи есть у этой ячейки и
     * возвращает List<Cell> - список со всеми непосещёнными соседями ROAD
     * @param c
     * @return
     */

//TODO: ЧЕТКО
    private List<Cell> getNeighbours(Cell c) {
        List<Cell> cell_list = new ArrayList<>();
        int i = c.getX();
        int j = c.getY();

        if (i - 2 > 0 && matrix[i - 2][j] == MatrixElements.ROAD)               //Проверка на верхнего соседа
            cell_list.add(new Cell(i - 2, j));
        if (j + 2 < ((matrix[0].length * 2 + 1) * 2) && matrix[i][j + 2] == MatrixElements.ROAD)     //Проверка на правого соседа
            cell_list.add(new Cell(i, j + 2));
        if (i + 2 < ((matrix.length * 2 + 1) * 2) && matrix[i + 2][j] == MatrixElements.ROAD)    //Проверка на нижнего соседа
            cell_list.add(new Cell(i + 2, j));
        if (j - 2 > 0 && matrix[i][j - 2] == MatrixElements.ROAD)               //Проверка на левого соседа
            cell_list.add(new Cell(i, j - 2));

        return cell_list;
    }

    /**
     * Смена позиции
     * @param first
     * @param second
     */
//TODO: ЧЕТКО
    private void removePoint(Cell first, Cell second) {
        int x_1 = first.getX();
        int y_1 = first.getY();
        int x_2 = second.getX();
        int y_2 = second.getY();
        int newX = x_1 - x_2;
        int newY = y_1 - y_2;

        if (newX != 0) {
            matrix[(x_1 + x_2) / 2][y_1] = MatrixElements.VISI;
        } else if (newY != 0) {
            matrix[x_1][(y_1 + y_2) / 2] = MatrixElements.VISI;
        }
    }

/*
    *//**
     * @return - Возвращает true, если остались непосещённые клетки
     *//*
    private boolean getUnvisitedCount() {
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if ((i % 2 != 0 && j % 2 != 0) && (i < matrix.length - 1 //Если ячейка нечётная по x и y
                        && j < matrix[i].length - 1))
                    if (matrix[i][j] == MatrixElements.CELL)
                        return true;
            }
        }
        return false;
    }*/
}

