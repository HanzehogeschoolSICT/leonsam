/**
 * Created by leonv on 15-3-2017.
 */
public class Model {
    private int[] sortableObjects = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300,
            320, 340, 360, 380, 400};
    int location = 0;
    int counter = 0;


    public Model() {
        shuffle(sortableObjects);
    }


    public boolean bubbleStep() {

        if (this.sortableObjects[location] > this.sortableObjects[location + 1]) {
            int x = this.sortableObjects[location];
            this.sortableObjects[location] = this.sortableObjects[location + 1];
            this.sortableObjects[location + 1] = x;
            location ++;
        } else {
            location ++;
        }

        if(location == sortableObjects.length-1-counter){
            location = 0;
            counter ++;
        }

        if(counter == sortableObjects.length-1){
            return false;
        } else {
            return true;
        }
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