# HashMap

- Time complexity is almost constant for put and gets method until rehashing is not done.
- In case of collision, i.e. index of two or more nodes are the same, nodes are joined by link list i.e. the second node is referenced by the first node and third by second and so on.
- If the key given already exist in HashMap, the value is replaced with a new value.
- The hash code of the null key is 0.
- When getting an object with its key, the linked list is traversed until the key matches or null is found on the next field.