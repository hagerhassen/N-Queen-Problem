package com.company;


import javax.swing.*;

public class MyFrame extends JFrame {
    private JPanel mainpanel;
    private JComboBox numofqueens;
    private JButton submit;


    public MyFrame(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainpanel);

        this.pack();
        numofqueens.addActionListener(e -> {
            if(e.getSource()==numofqueens){
                int numberOfQueens= Integer.parseInt((String) numofqueens.getSelectedItem());
                int [][][] board=Board.Generate(numberOfQueens);
                NQueenThread[] queensThreads=new NQueenThread[numberOfQueens];
                for(int i=0;i<numberOfQueens;i++){
                    queensThreads[i]=new NQueenThread(board[i],"Thread"+i,i);
                }
                // start threads
                for(int i=0;i<numberOfQueens;i++){
                    queensThreads[i].start();
                }
            }

        });
    }
    }

