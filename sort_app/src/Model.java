/**
 * Created by leonv on 15-3-2017.
 */
public class Model {
    private int[] sortableObjects = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300,
            320, 340, 360, 380, 400};
    //variables for bubblesort
    int location, counter = 0;
    //variables for insertionsort
    int iCounter, iLocation = 1;
    //variables for quicksort
    int stack[] = new int[20];
    int top = -1, low = 0, high = 19, pivot;


    public Model() {
        stack[++top] = low;
        stack[++top] = high;
        shuffle(sortableObjects);
    }


    public boolean bubbleStep() {

        if (sortableObjects[location] > sortableObjects[location + 1]) {
            //swap
            swap(sortableObjects, location, location+1);
            location ++;
        } else {
            location ++;
        }

        if(location == sortableObjects.length-1-counter){
            location = 0;
            counter ++;
        }

        if(counter == sortableObjects.length-1){
            location = 0;
            counter = 0;
            return false;
        } else {
            return true;
        }
    }

    public boolean insertionStep(){
        if(iLocation > 0) {
            if (sortableObjects[iLocation] < sortableObjects[iLocation - 1]) {
                //swap
                swap(sortableObjects, iLocation, iLocation-1);
                iLocation--;
            }else{
                iCounter++;
                iLocation = iCounter;
            }
        }else{
            iCounter++;
            iLocation = iCounter;
        }

        if(iCounter == sortableObjects.length){
            iCounter = 1;
            iLocation = 1;
            return false;
        }else {
            return true;
        }
    }


    // quicksort based on http://www.geeksforgeeks.org/iterative-quick-sort/ variant
    // a stack is simulated to keep track of the divided array parts
    public boolean quickStep(){
        
        // return true if stack is empty
        if (top == -1) {
            return false;
        }

        // high and low are popped off the stack
        // top is decremented after the pop
        high = stack[top--];
        low = stack[top--];

        // pivot is gonna be put in the right spot by the partition method
        pivot = partition(low, high);

        // when there are still items on the sides of the pivots
        // then push them on the stack
        if (pivot-1 > low) {
            stack[++top] = low;
            stack[++top] = pivot - 1;
        }
        if (pivot+1 < high) {
            stack[++top] = pivot+1;
            stack[++top] = high;
        }

        // return true if array is not sorted yet
        return true;
    }

    //partition function used by quicksort
    private int partition (int low, int high) {
        int pivot = sortableObjects[high];
        //every number left of wall is smaller than pivot
        int wall = low - 1;

        //check amount of values smaller than pivot
        for (int i = low; i < high; i++) {
            if (sortableObjects[i] < pivot) {
                wall++;
                swap(sortableObjects, wall, i);
            }
        }
        // swap the pivot with the value on the index of the wall
        // the wall is de final index of the pivote
        swap(sortableObjects, wall+1, high);
        //return the final index of the pivot value
        return (wall+1);
    }

    public void print() {
        for(int i=0; i<this.sortableObjects.length; i++){
            System.out.println(this.sortableObjects[i]);
        }
    }

    private static void swap(int[] array, int firstElement, int secondElement) {
        int temp = array[firstElement];
        array[firstElement] = array[secondElement];
        array[secondElement] = temp;
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