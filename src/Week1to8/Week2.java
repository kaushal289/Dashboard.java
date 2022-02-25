package Week1to8;

class LinkedList
{
    int data;
    LinkedList next;

    LinkedList(int key)
    {
        data = key;
        next = null;
    }
}

class LinkedListImplementation {
    static LinkedList mergeKLists(LinkedList linkedList[], int count) {
        for (int i = 1; i < count; i++) {
            while(true) {
                LinkedList current_head = linkedList[0];
                LinkedList next_head = linkedList[i];

                if (next_head == null)
                    break;

                if(current_head.data >= next_head.data) {
                    linkedList[i] = next_head.next;
                    next_head.next = current_head;
                    linkedList[0] = next_head;
                } else {
                    while (current_head.next != null) {
                        if (current_head.next.data >= next_head.data) {
                            linkedList[i] = next_head.next;
                            next_head.next = current_head.next;
                            current_head.next = next_head;
                            break;
                        }

                        current_head = current_head.next;

                        if (current_head.next == null) {
                            linkedList[i] = next_head.next;
                            next_head.next = null;
                            current_head.next = next_head;
                            current_head.next.next = null;
                            break;
                        }
                    }
                }
            }
        }
        return linkedList[0];
    }

    static void segregateElements(int arr[], int n) {
        int temp[] = new int[n];
        int j = 0;

        for (int i = 0; i < n; i++)
            if (arr[i] >= 0)
                temp[j++] = arr[i];

        if (j == n || j == 0)
            return;

        for (int i = 0; i < n; i++)
            if (arr[i] < 0)
                temp[j++] = arr[i];

        for (int i = 0; i < n; i++)
            arr[i] = temp[i];
    }


    static int findLength(LinkedList node) {
        LinkedList current = node;
        int count = 0;
        while (current != null)
        {
            count++;
            current = current.next;
        }
        return count;
    }


    static void convertToArray(LinkedList node) {
        int len = findLength(node);
        int []arr = new int[len];

        int index = 0;
        LinkedList current = node;

        while (current != null)
        {
            arr[index++] = current.data;
            current = current.next;
        }

        segregateElements(arr, len);
        printToArray(arr, len);
    }

    static void printToArray(int a[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(a[i]+" ");
    }


    public static void main (String[] args)
    {
        int number_of_linked_list = 3;
        LinkedList[] linkedLists = new LinkedList[number_of_linked_list];

        linkedLists[0] = new LinkedList(5);
        linkedLists[0].next = new LinkedList(7);
        linkedLists[0].next.next = new LinkedList(8);
        linkedLists[0].next.next.next = new LinkedList(9);

        linkedLists[1] = new LinkedList(1);
        linkedLists[1].next = new LinkedList(2);
        linkedLists[1].next.next = new LinkedList(3);
        linkedLists[1].next.next.next = new LinkedList(6);

        linkedLists[2] = new LinkedList(-5);
        linkedLists[2].next = new LinkedList(-10);
        linkedLists[2].next.next = new LinkedList(10);
        linkedLists[2].next.next.next = new LinkedList(11);

        LinkedList mergedList = mergeKLists(linkedLists, number_of_linked_list);
        convertToArray(mergedList);
    }
}