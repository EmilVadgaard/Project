package com.game;

import java.io.File;
import java.util.Scanner;

public class Grid {
    Square[][] map;

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
    }

    public Grid(File map) {
        this.map = build(map);
    }

    private int[] mapDimensions(File map) {
        Scanner sc = new Scanner(map);

        // Find the collums and rows of the map grid
        int collums = 0;
        int rows = 0;
        while (sc.hasNextLine()) {
            rows++;
            String line = sc.nextLine();
            for (int i = 0; i < line.length(); i++) {
                collums++;
            }
        }
        int[] result = {rows, collums};
        sc.close();
        return result;
    }

    private char[][] fileToCharMatrix(File map) {
        int[] dimensions = mapDimensions(map);
        Scanner sc = new Scanner(map);

        // Convert text file to a matrix of characters

        char[][] charMap = new char[dimensions[0]][dimensions[1]];
        int row = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (int i = 0; i < line.length(); i++) {
                charMap[row][i] = line.charAt(i);
            }
            sc.nextLine();
            row++;
        }
        sc.close();
        return charMap;
    }

    private Square[][] build(File map) {
        char[][] charMatrix = fileToCharMatrix(map);
        Square[][] world = new Square[charMatrix.length][charMatrix[0].length];
        
        for (int y = 0; y < charMatrix.length; y++) {
            for (int x = 0; x < charMatrix[0].length; x++) {
                int sumOfWalls;
                switch(charMatrix[y][x]) {
                    case 'W':
                        world[y][x] = new Square(new Wall(Variant.wall), -1);
                        break;
                    case 'E':
                        sumOfWalls = 0;
                        if (charMatrix[y-1][x] == 'W') {
                            sumOfWalls += 1;
                        }
                        if (charMatrix[y+1][x] == 'W') {
                            sumOfWalls += 4;
                        }
                        if (charMatrix[y][x-1] == 'W') {
                            sumOfWalls += 8;
                        }
                        if (charMatrix[y][x+1] == 'W') {
                            sumOfWalls += 2;
                        }
                        world[y][x] = new Square(new Empty(), sumOfWalls);
                        break;
                    case 'P':
                        sumOfWalls = 0;
                        if (charMatrix[y-1][x] == 'W') {
                            sumOfWalls += 1;
                        }
                        if (charMatrix[y+1][x] == 'W') {
                            sumOfWalls += 4;
                        }
                        if (charMatrix[y][x-1] == 'W') {
                            sumOfWalls += 8;
                        }
                        if (charMatrix[y][x+1] == 'W') {
                            sumOfWalls += 2;
                        }
                        world[y][x] = new Square(new Empty(), sumOfWalls);
                        break;
                }
            }
        }
        return world;
    }

    public Entity getEntity(int x, int y) {
        return map[y][x].getEntity();
    }

    public int getSquareType(int x, int y) {
        return map[y][x].getType();
    }
}
