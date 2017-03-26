
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
    private TreeSet<String> treeSet;
    private String[][] playBoard;
    private int boardSize = 4;
    private ArrayList<String> results;
    private boolean history[][];
    private ArrayList<boolean[][]> resultMatrixes;
    private String[] vowels = {"a","e","i","o","u"};

    public Model(Controller controller){
        treeSet = new TreeSet<>();
        results  = new ArrayList<>();
        resultMatrixes = new ArrayList<>();
        this.addObserver(controller);
        dictonaryBuilder();
        boardBuilder();
    }

    public void dictonaryBuilder(){
        try{
            FileReader fileReader = new FileReader("boggle/src/dict.txt");
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
                if(random.nextDouble()<0.3){
                    playBoard[i][j] = vowels[random.nextInt(vowels.length-1)];
                }else {
                    playBoard[i][j] = Character.toString((char) (random.nextInt(122 - 97) + 97));
                    history[i][j] = false;
                }
                System.out.print(playBoard[i][j]);
            }
            System.out.println("");
        }


    }

    public void solver() {
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

        temp[x][y] = true;

        if(treeSet.contains(currentWord)){
            results.add(currentWord);
            resultMatrixes.add(temp);
            setChanged();
            notifyObservers();
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    System.out.print(temp[i][j]);
                }
                System.out.print("\n");
            }
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

    public int getSize(){
        return this.boardSize;
    }

    public String[][] getPlayBoard(){
        return playBoard;
    }

    public ArrayList getResults() {
        return results;
    }
}
