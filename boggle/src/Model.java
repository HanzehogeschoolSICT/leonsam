import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by leonv on 22-3-2017.
 */
public class Model {
private TreeSet<String> treeSet = new TreeSet();
private String[][] playBoard;
private int boardSize = 4;

    public Model(){
        boardBuilder(boardSize);
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

/*        Iterator it = treeSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

        }
*/    }

    public void boardBuilder(int size){
        playBoard = new String[size][size];
        Random random = new Random();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                playBoard[i][j] = Character.toString((char)(random.nextInt(122-97)+97));
                System.out.printf(playBoard[i][j]);
            }
            System.out.println("");
        }


    }

    public void setBoardSize(int size) {
        this.boardSize = size;
    }
}
