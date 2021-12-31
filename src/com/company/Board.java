package com.company;

public abstract class Board {
    public static int[][][] Generate(int n){
        int[][][] matrix=new int[n][n][n];
        for(int m=0;m<n;m++){
            matrix[m][0][m]=1;
        }
        return matrix;
    }
}
