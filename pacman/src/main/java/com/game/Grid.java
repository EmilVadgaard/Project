package com.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grid {
    Square[][] map;
    // int playerStartX;        kan tilføjes til txt filen som (p / g)
    // int playerStartY;
    // int ghostStartX;
    // int ghostStartY;

    private class Square{
        Entity entity;
        int type;
        Square(Entity entity, int type){
            this.entity = entity;
            this.type = type;

        }

        public Entity getEntity(){
            return entity;
        }

        public int getType(){
            return type;
        }

        public String toString() {
            return this.entity + " " + this.type;
        }
    }

    public Grid(File map) {
        try {
            this.map = build(map);
        }
        catch(FileNotFoundException ex) {
            System.out.println("No such file: " + map.toString());
            ex.printStackTrace();
        }
        //  for (int y = 0; y < this.map.length; y++) {
        //      for (int x = 0; x < this.map[0].length; x++) {
        //          System.out.print(this.map[y][x]);
        //      }
        //  }
    }

    private int[] mapDimensions(File map) throws FileNotFoundException {
        Scanner sc = new Scanner(map);

        // Find the collums and rows of the map grid
        int collums = 0;
        int rows = 1;
        String line = sc.nextLine();
        for (int i = 0; i < line.length(); i++) {
            collums++;
        }
        while (sc.hasNextLine()) {
            sc.nextLine();
            rows++;
        }

        int[] result = {rows,collums};
        sc.close();
        return result;
    }

    private char[][] fileToCharMatrix(File map) throws FileNotFoundException{
        int[] dimensions = mapDimensions(map);
        Scanner sc = new Scanner(map);

        // Convert text file to a matrix of characters

        char[][] charMap = new char[dimensions[0]][dimensions[1]];
        int row = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (int col = 0; col < line.length(); col++) {
                charMap[row][col] = line.charAt(col);
            }
            row++;
        }
        sc.close();
        return charMap;
    }

    private Square[][] build(File map) throws FileNotFoundException {
        char[][] charMatrix = fileToCharMatrix(map);
        Square[][] world = new Square[charMatrix.length][charMatrix[0].length];
        
        for (int y = 0; y < charMatrix.length; y++) {
            for (int x = 0; x < charMatrix[0].length; x++) {
                switch(charMatrix[y][x]) {
                    case 'W':
                        world[y][x] = new Square(new Wall(Variant.wall), -1);
                        break;
                    case 'E':
                        world[y][x] = new Square(new Empty(), sumOfWalls(x,y,charMatrix));
                        break;
                    case 'P':
                        world[y][x] = new Square(new Empty(), sumOfWalls(x,y,charMatrix));
                        break;
                }
            }
        }
        return world;
    }

    private int sumOfWalls(int x, int y, char[][] charMatrix) {
        int sumOfWalls = 0;
        if (x >= 1) {
             if (charMatrix[y][x-1] == 'W') {
            sumOfWalls += 8;
             }
        }
        if (y >= 1) {
            if (charMatrix[y-1][x] == 'W') {
            sumOfWalls += 1;
            }
        }
        if (x < charMatrix.length) {
            if (charMatrix[y][x+1] == 'W') {
            sumOfWalls += 2;
            }
        }
       if (y < charMatrix[1].length){
            if (charMatrix[y+1][x] == 'W') {
            sumOfWalls += 4;
            }
       }

        return sumOfWalls;
    }

    public Entity getEntity(int x, int y) {
        return map[y][x].getEntity();
    }

    public int getSquareType(int x, int y) {
        return map[y][x].getType();
    }

    public int getXLength(){
        return map[0].length;
    }

    public int getYLength(){
        return map.length;
    }
}
