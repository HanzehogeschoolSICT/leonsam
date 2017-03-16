import java.util.Random;

/**
 * Created by leonv on 15-3-2017.
 */
public class Model {
    private int[] list = new int[10];
    public  boolean notSorted = true;

    public Model(int[] list) {
        this.list = list;
        int i;
        Random num = new Random();

        for(i=0; i<10; i++){
            list[i] = num.nextInt(100);
        }
    }

    public boolean bubbleStep(){
        int i;
        for(i=0; i<this.list.length; i++){
            if(this.list[i] > this.list[i+1]){
                int x = this.list[i];
                this.list[i] = this.list[i+1];
                this.list[i+1] = x;
                System.out.println("Swap!\n");
                this.print();
                return true;
            }
            System.out.println("No Swap!\n");
        }
        this.print();
        System.out.println("Sorted :)\n");
        return false;
    }

    public void print() {
        for(int i=0; i<this.list.length; i++){
            System.out.println(this.list[i]);
        }
    }
}