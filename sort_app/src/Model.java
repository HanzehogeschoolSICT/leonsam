import java.util.Random;

/**
 * Created by leonv on 15-3-2017.
 */
public class Model {
    private int[] sortableObjects = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300,
            320, 340, 360, 380, 400};

    public Model() {
        shuffle(sortableObjects);
    }

    public boolean bubbleStep(){
        int i;
        for(i=0; i<this.sortableObjects.length - 1; i++){
            if(this.sortableObjects[i] > this.sortableObjects[i+1]){
                int x = this.sortableObjects[i];
                this.sortableObjects[i] = this.sortableObjects[i+1];
                this.sortableObjects[i+1] = x;
                print();
                return true;
            }
        }
        print();
        return false;
    }

    public void print() {
        for(int i=0; i<this.sortableObjects.length; i++){
            System.out.println(this.sortableObjects[i]);
        }
    }



    public int[] getSortableObjects() {
        return sortableObjects;
    }

    //Fisher-Yates shuffle; source: https://www.dotnetperls.com/shuffle-java
    static void shuffle(int[] array) {
        int n = array.length;
        for (int i = 0; i < array.length; i++) {
            // Get a random index of the array past i.
            int random = i + (int) (Math.random() * (n - i));
            // Swap the random element with the present element.
            int randomElement = array[random];
            array[random] = array[i];
            array[i] = randomElement;
        }
    }

}