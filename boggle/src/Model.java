
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by leonv on 22-3-2017.
 */
public class Model {
private TreeSet<String> treeSet = new TreeSet();

    public Model(){
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
}
