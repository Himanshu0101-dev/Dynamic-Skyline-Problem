#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int x, h;
} Event;

int cmp(const void* a, const void* b) {
    Event* e1 = (Event*)a;
    Event* e2 = (Event*)b;
    if (e1->x != e2->x) return e1->x - e2->x;
    return e1->h - e2->h;
}

int main() {
    int buildings[][3] = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
    int n = sizeof(buildings) / sizeof(buildings[0]);

    Event events[2*n];
    int idx = 0;

    // Create events
    for (int i = 0; i < n; i++) {
        events[idx++] = (Event){buildings[i][0], -buildings[i][2]}; // start
        events[idx++] = (Event){buildings[i][1], buildings[i][2]};  // end
    }

    qsort(events, 2*n, sizeof(Event), cmp);

    int heights[1000] = {0}; // simple array for heights
    int prevHeight = 0;

    for (int i = 0; i < 2*n; i++) {
        if (events[i].h < 0) {
            heights[-events[i].h]++;
        } else {
            heights[events[i].h]--;
        }

        int currHeight = 0;
        for (int h = 1000; h >= 0; h--) {
            if (heights[h] > 0) {
                currHeight = h;
                break;
            }
        }

        if (currHeight != prevHeight) {
            printf("[%d,%d]\n", events[i].x, currHeight);
            prevHeight = currHeight;
        }
    }

    return 0;
}
