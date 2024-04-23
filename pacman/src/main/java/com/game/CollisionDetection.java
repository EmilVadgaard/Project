package com.game;

import java.util.Arrays;

public class CollisionDetection {
    final static int[] LEGAL_NORTH = {2,4,6,8,10,12,14};
    final static int[] LEGAL_SOUTH = {1,2,3,8,9,10,11};
    final static int[] LEGAL_EAST = {1,4,5,8,9,12,13};
    final static int[] LEGAL_WEST = {1,2,3,4,5,6,7};
    
    public static boolean collision(int playerX, int playerY, int ghostX, int ghostY){
        if (((playerX + 0.5) > ghostX) && 
            ((playerX - 0.5) < ghostX) && 
            ((playerY + 0.5) < ghostY) && 
            ((playerY - 0.5) > ghostY)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWall(Variant variant){
        if (variant == Variant.wall){
            return true;
        }
        return false;
    }
    
    // public static boolean wallCollide(Direction direction, int[] pos) {
    //     switch(direction)  {
    //         case north:
    //             if (Math.round(pos[0]))
    //             // return Arrays.binarySearch(LEGAL_NORTH, square) < 0;
    //         case south:
    //             return Arrays.binarySearch(LEGAL_SOUTH, square) < 0;
    //         case east:
    //             return Arrays.binarySearch(LEGAL_EAST, square) < 0;
    //         case west:
    //             return Arrays.binarySearch(LEGAL_WEST, square) < 0;
    //         default:
    //             return false;         
    //     }
    // }

    public static boolean turnableSection(float[] playerPos, int[] playerOffsetPos, Direction desiredDirection, int square){
        if (playerPos[0] > (playerOffsetPos[0] + 0.5) && playerPos[0] < (playerOffsetPos[0] - 0.5) && playerPos[1] < (playerOffsetPos[1] - 0.5) && playerPos[1] > (playerOffsetPos[1] + 0.5) ) {
            switch(desiredDirection){
                case north:
                    return Arrays.binarySearch(LEGAL_NORTH, square) >= 0;
                case south:
                    return Arrays.binarySearch(LEGAL_SOUTH, square) >= 0;
                case east:
                    return Arrays.binarySearch(LEGAL_EAST, square) >= 0;
                case west:
                    return Arrays.binarySearch(LEGAL_WEST, square) >= 0;
                default:
                    return false;
            }
        } else{
            return false;
        }
    }


}

