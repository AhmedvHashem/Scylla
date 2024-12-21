print("Hi, I'm learning Python!")

tuple = (1, 2, 3, 4, 5)
print(tuple)


# Heap Push and Pop
import heapq
minHeap = []
heapq.heappush(minHeap, 5)
# heapq.heappop(minHeap)
print(minHeap)

# HeapSort
import heapq
nums = [1, 2, 3, 4, 5]
heapq.heapify(nums)     # O(n)
while nums:
    heapq.heappop(nums) # O(logn)
print(nums)
