

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by leonv on 22-3-2017.
 */
public class Model extends Observable {
    private TreeSet<String> treeSet = new TreeSet();
    private String[][] playBoard;
    private int boardSize = 4;
    private ArrayList<String> results = new ArrayList<String>();
    private boolean history[][];
    private int finishCheck;

    public Model(Controller controller){
        this.addObserver(controller);
        dictonaryBuilder();
        boardBuilder();
    }

    public void dictonaryBuilder(){
        try{
            FileReader fileReader = new FileReader("/home/samikroon/IdeaProjects/leonsam/boggle/src/dict.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String word;
            while((word = bufferedReader.readLine()) != null){
                treeSet.add(word);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"File not found!","Error",JOptionPane.INFORMATION_MESSAGE);
            return;
        }

//        System.out.println(treeSet.subSet("arb","arb"+Character.toString(Character.MAX_VALUE)));

/*        Iterator it = treeSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

        }
*/    }

    public void boardBuilder(){
        playBoard = new String[boardSize][boardSize];
        Random random = new Random();
        history = new boolean[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                playBoard[i][j] = Character.toString((char)(random.nextInt(122-97)+97));
                history[i][j] = false;
                System.out.print(playBoard[i][j]);
            }
            System.out.println("");
        }


    }

    public void solver() {
        finishCheck=0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                recursiveSolver(playBoard[i][j], history.clone(), i, j);
            }
        }
    }

    public void recursiveSolver(String currentWord, boolean[][] history, int x, int y){
        boolean[][] temp = new boolean[boardSize][];
        for (int i = 0; i < boardSize; i++) {
            temp[i] = history[i].clone();
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(temp[i][j]);
            }
            System.out.print("\n");
        }
        temp[x][y] = true;

        if(treeSet.contains(currentWord)){
            results.add(currentWord);
            setChanged();
            notifyObservers();
            System.out.println(currentWord);
        }

        if((treeSet.subSet(currentWord,currentWord+Character.toString(Character.MAX_VALUE))).isEmpty()){
           return;
        }

        if(x-1 > 0 && y-1 > 0 && !temp[x-1][y-1]){
            recursiveSolver(currentWord+playBoard[x-1][y-1], temp, x-1, y-1);
        }

        if(x-1 > 0 && !temp[x-1][y]){
            recursiveSolver(currentWord+playBoard[x-1][y], temp, x-1, y);
        }

        if(x-1 > 0 && y+1 < boardSize && !temp[x-1][y+1]){
            recursiveSolver(currentWord+playBoard[x-1][y+1], temp, x-1, y+1);
        }

        if(y-1 > 0 && !temp[x][y-1]){
            recursiveSolver(currentWord+playBoard[x][y-1], temp, x, y-1);
        }

        if(y+1 < boardSize && !temp[x][y+1]){
            recursiveSolver(currentWord+playBoard[x][y+1], temp, x, y+1);
        }

        if(x+1 < boardSize && y-1 > 0 && !temp[x+1][y-1]){
            recursiveSolver(currentWord+playBoard[x+1][y-1], temp, x+1, y-1);
        }

        if(x+1 < boardSize &&!temp[x+1][y]){
            recursiveSolver(currentWord+playBoard[x+1][y], temp, x+1, y);
        }

        if(x+1 < boardSize && y+1 < boardSize && !temp[x+1][y+1]){
            recursiveSolver(currentWord+playBoard[x+1][y+1], temp, x+1, y+1);
        }

    }


    public void setSize(int size) {
        this.boardSize = size;
        boardBuilder();
    }

    public ArrayList getResults() {
        return results;
    }
}
