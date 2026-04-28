Sorting and Searching Algorithm Analysis System
Project Overview

This project analyzes the performance of sorting and searching algorithms using Java.
Selected Algorithms:
Insertion Sort (Basic)
Merge Sort (Advanced)
Binary Search (Searching)
Purpose:
To compare execution time of algorithms and understand their efficiency depending on input size and data type.


Algorithm Descriptions
Insertion Sort
Insertion Sort builds a sorted array one element at a time by inserting elements into their correct position.
Best Case: O(n)
Worst Case: O(n²)
Space Complexity: O(1)


Merge Sort
Merge Sort divides the array into smaller parts, sorts them, and merges them back together.
Time Complexity: O(n log n)
Space Complexity: O(n)


Binary Search
Binary Search finds an element by repeatedly dividing the array in half.
Time Complexity: O(log n)
Requirement: array must be sorted


Experimental Results
Test Conditions:
Array sizes: 10, 100, 1000
Input types: Random and Sorted



Analysis
Merge Sort is significantly faster on large arrays.
For example, at size 1000:
Insertion: 4,315,800 ns
Merge: 268,200 ns
Insertion Sort performs much better on sorted arrays:
At size 1000 sorted: only 10,900 ns
This matches its best-case complexity O(n)
As array size increases, Insertion Sort becomes much slower due to O(n²), while Merge Sort scales better with O(n log n)
For medium arrays (size 100), performance is closer, but Merge still slightly better on random data
Results match expected Big-O complexity
Searching:
Binary Search is extremely fast (around 1800–4000 ns)
Time remains almost constant because of logarithmic complexity O(log n)
Binary Search requires a sorted array because it divides the array into halves and compares values based on order


Reflection
This project helped me understand how algorithm efficiency changes depending on input size and structure.
Insertion Sort is efficient for small or already sorted arrays, but performs poorly on large random data.
Merge Sort provides stable and predictable performance regardless of input.
One challenge was correctly measuring execution time and ensuring fair comparisons using cloned arrays.


Conclusion
The experiment shows that algorithm efficiency strongly depends on input size and data structure.
Choosing the right algorithm is critical: simple algorithms may work well for small data, but advanced algorithms are
necessary for scalability.



<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/ca312cf9-4019-47a1-940a-20be7ed39e90" />
