package app;

public class Experiment {

    private Sorter sorter = new Sorter();
    private Searcher searcher = new Searcher();

    public long measureSortTime(int[] arr, String type) {
        int[] copy = arr.clone();

        long start = System.nanoTime();

        if (type.equals("basic"))
            sorter.basicSort(copy);
        else
            sorter.advancedSort(copy);

        long end = System.nanoTime();

        return end - start;
    }

    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();

        searcher.search(arr, target);

        long end = System.nanoTime();

        return end - start;
    }

    public void runAllExperiments() {

        int[] sizes = {10, 100, 1000};

        for (int size : sizes) {

            System.out.println("\n===== SIZE: " + size + " =====");

            int[] random = sorter.generateRandomArray(size);
            int[] sorted = random.clone();

            sorter.advancedSort(sorted);

            System.out.println("Random Array:");
            System.out.println("Insertion: " + measureSortTime(random, "basic"));
            System.out.println("Merge: " + measureSortTime(random, "advanced"));

            System.out.println("Sorted Array:");
            System.out.println("Insertion: " + measureSortTime(sorted, "basic"));
            System.out.println("Merge: " + measureSortTime(sorted, "advanced"));

            int target = sorted[size / 2];

            System.out.println("Binary Search: " + measureSearchTime(sorted, target));
        }
    }
}