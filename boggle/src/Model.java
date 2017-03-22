import javax.swing.*;
import java.io.*;
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

/*        Iterator it = treeSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

        }
*/    }

    public void boardBuilder(){
        playBoard = new String[boardSize][boardSize];
        Random random = new Random();

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                playBoard[i][j] = Character.toString((char)(random.nextInt(122-97)+97));
                System.out.printf(playBoard[i][j]);
            }
            System.out.println("");
        }


    }

    public void boggleSolver() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {

            }
        }
    }


    public void setSize(int size) {
        this.boardSize = size;
        boardBuilder();
    }


    
    /**
     * This method makes a "deep clone" of any Java object it is given.
     * SOURCE: http://alvinalexander.com/java/java-deep-clone-example-source-code
     */
    public static Object objectClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
