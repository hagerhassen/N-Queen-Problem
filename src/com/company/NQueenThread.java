package com.company;

import javax.swing.*;
import java.awt.*;

public class NQueenThread extends Thread{
    private int[][] board;
    private int N;
    private String name;
    private int number;

    public NQueenThread(int[][] board,String name,int number) {
        this.board = board;
        this.N=board[0].length;
        this.name=name;
        this.number=number;
    }


    String printSolution(int board[][])
    {
        String solution="<html>";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                solution+="&nbsp; "+ board[i][j] + "&nbsp; ";
            solution+="<br/>";
        }
        solution+="<html/>";
        return solution;
    }

    private boolean isSafe(int row, int col)
    {
        //check vertical line
        for(int i=0; i < N; i++) {
            if(i == row) continue;
            if(board[i][col] == 1) return false;
        }

        //check horizontal line
        for(int j=0; j < N; j++) {
            if(j == col) continue;
            if(board[row][j] == 1) return false;
        }

        //check north east
        for(int i=row-1, j=col+1; i >=0  && j < N; i--, j++) {
            if(board[i][j] == 1) return false;
        }

        //check south east
        for(int i=row+1, j=col+1; i < N && j < N; i++, j++) {
            if(board[i][j] == 1) return false;
        }

        //check north west
        for(int i=row-1, j=col-1; i >=0 && j >=0; i--,j--) {
            if(board[i][j] == 1) return false;
        }

        //check south west
        for(int i=row+1, j=col-1; i<N && j >=0; i++,j--) {
            if(board[i][j] == 1) return false;
        }

        return true;
    }


    public boolean solve(int row) {
        if(row == N) return true;

        for(int j=0; j < N; j++) {
            if(isSafe(row, j)) {
                board[row][j] = 1;

                boolean nextPlacement = solve(row + 1);
                if(nextPlacement) return true;
                board[row][j] = 0;
            }
        }
        return false;
    }


    @Override
    public void run() {
        Thread.currentThread().setName(name);
        JFrame frame = new JFrame(name);
        frame.setSize(300, 200);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - frame.getWidth();
        int y = number*frame.getHeight();
        if (number>3){
            x=(int) rect.getMaxX() - 2*frame.getWidth();
            y= (number%4) * frame.getHeight();
        }
        frame.setLocation(x, y);

        boolean result = solve(1);
        if(result) {
            String solution =   printSolution(board);
            JLabel label = new JLabel("Solution Label", JLabel.CENTER);
            label.setText(solution);
            frame.add(label);
            frame.setVisible(true);
        }
        else {
            JLabel label = new JLabel("Error Label", JLabel.CENTER);
            label.setText("Solution does not exist");
            frame.add(label);
            frame.setVisible(true);
            return;
        }
        System.out.println();
    }
}
