import heapq

def getSkyline(buildings):
    events = []
    for l, r, h in buildings:
        events.append((l, -h, r))  # start of building
        events.append((r, 0, 0))   # end of building
    events.sort()

    result = [[0, 0]]
    heap = [(0, float("inf"))]

    for x, neg_h, r in events:
        while heap and heap[0][1] <= x:
            heapq.heappop(heap)
        if neg_h:
            heapq.heappush(heap, (neg_h, r))
        if result[-1][1] != -heap[0][0]:
            result.append([x, -heap[0][0]])
    return result[1:]
