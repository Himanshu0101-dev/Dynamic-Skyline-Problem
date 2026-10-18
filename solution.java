import java.util.*;

public class DynamicSkyline {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> events = new ArrayList<>();

        // Create events: start (-height) and end (0 height)
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // start
            events.add(new int[]{b[1], b[2]});  // end
        }

        // Sort events by x, then by height
        events.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // Max-heap for heights
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        heap.add(0);
        int prevHeight = 0;

        for (int[] e : events) {
            if (e[1] < 0) { // start of building
                heap.add(-e[1]);
            } else {        // end of building
                heap.remove(e[1]);
            }

            int currHeight = heap.peek();
            if (currHeight != prevHeight) {
                result.add(new int[]{e[0], currHeight});
                prevHeight = currHeight;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DynamicSkyline ds = new DynamicSkyline();
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        List<int[]> skyline = ds.getSkyline(buildings);

        for (int[] point : skyline) {
            System.out.println(Arrays.toString(point));
        }
    }
}
