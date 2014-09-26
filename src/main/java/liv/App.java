package liv;

import java.util.Arrays;
import java.util.Random;

/**
 * Program entry point. Reads a file and runs a set of tests on it.
 */
public class App {
    public static void main(String[] args) {
        int length = 1_000_000;
        String key = "mykey";
        KeyValue[] arr = generateArray(length, key);

        long t1 = find(arr, key);
        long t2 = parallelFind(arr, key);
        System.out.println("Normal find:\t" + t1);
        System.out.println("Parallel find:\t" + t2);
    }

    /**
     * Generate an array of a certain length containing random keys and values
     * AND the given key with a given value right at the end.
     *
     * @param length
     *            Length of the generated array.
     * @param key
     *            Key to ensure present. An element with this key will be added
     *            at the end of the array.
     * @return Array with length elements in it, the last one having the key set
     *         to the given key.
     */
    public static KeyValue[] generateArray(int length, String key) {
        Random rnd = new Random();
        KeyValue[] arr = new KeyValue[length];
        for (int i = 0; i < length - 1; i++) {
            arr[i] = new KeyValue(String.valueOf(rnd.nextInt()), String.valueOf(rnd.nextInt()));
        }
        arr[length - 1] = new KeyValue(key, String.valueOf(rnd.nextInt()));
        return arr;
    }

    public static long find(KeyValue[] arr, String key) {
        long start = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            if (key.equals(arr[i].getKey())) {
                break;
            }
        }
        long end = System.nanoTime();
        return (end - start);
    }

    public static long parallelFind(KeyValue[] arr, String key) {
        long start = System.nanoTime();
        Arrays.stream(arr).filter(x -> key.equals(x.getKey())).findFirst();
        long end = System.nanoTime();
        return (end - start);
    }
}
