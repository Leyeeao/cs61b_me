public class ModNCounter {

    private int myCount;
    public int n;

    public ModNCounter(int N) {
        n = N;
        myCount = 0;
    }

    public void increment() {
        myCount++;
    }

    public void reset() {
        myCount = 0;
    }

    public int value() {
        return myCount;
    }

}
