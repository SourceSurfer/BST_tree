package BST_AnyType;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean ifString;
        System.out.print("Choice data type integer or string: ");

        BST bstMy;
        Iterator it;
        String val = scan.nextLine();
        if ("string".equals(val))
        {
            bstMy = new BST<String>();
            ifString= true;
        }
        else
        {
            bstMy = new BST<Integer>();
            ifString= false;
        }
        char ch;

        do {
            System.out.println("\nBinary Search Tree Operations");
            // Заполнение дерева по умолчанию
            System.out.println("1. default insert ");
            // вставка элемента с заданным ключом
            System.out.println("2. manual insert ");
            // удаление элемента с заданным ключом
            System.out.println("3. delete element");
            // поиск элемента с заданным ключом
            System.out.println("4. search element");
            // опрос размера дерева
            System.out.println("5. count nodes");
            // проверка дерева на пустоту
            System.out.println("6. check empty");
            // очистка дерева
            System.out.println("7. clear tree");
            // вывод структуры дерева на экран горизонтально
            System.out.println("8. print tree");
            // установка на корень дерева
            System.out.println("9. place iterator on root");
            // проверка конца дерева
            System.out.println("10. has next");
            // доступ к данным текущего элемента дерева
            System.out.println("11. current element");
            // переход к предыдущему по значению ключа элемента дерева
            System.out.println("12. previous position");
            // операции обхода
            System.out.println("13. tree traversals");

            System.out.print("Your choice is: ");
            int choice;
            do {
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    scan.next();
                }

                choice = scan.nextInt();
            } while (choice <= 0);

            switch (choice) {
                case 1:
                    if ("string".equals(val))
                    {
                        Random r = new Random();
                        String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM";
                        for (int i = 0; i < 15; i++) {
                            bstMy.insert(String.valueOf(alphabet.charAt(r.nextInt(alphabet.length()))));
                        }
                    }
                    else {
                        for (int i = 0; i < 15; i++) {
                            bstMy.insert(getRandomNumberInRange(1, 10));
                        }
                    }
                    System.out.print(bstMy);
                    break;
                case 2:
                    System.out.print("Enter element to insert: ");
                    bstMy.insert((ifString)?scan.next():scan.nextInt());
                    System.out.println(bstMy);
                    break;
                case 3:
                    System.out.print("Enter element to delete: ");
                    System.out.println("Element has been removed: "+ bstMy.remove((ifString)?scan.next():scan.nextInt()));
                    System.out.println(bstMy);
                    break;
                case 4:
                    System.out.print("Enter element to search: ");
                    System.out.println("Result: " +
                            bstMy.find((ifString)?scan.next():scan.nextInt()));
                    break;
                case 5:
                    System.out.println("Nodes = " + bstMy.countNodes());
                    break;
                case 6:
                    System.out.println("Empty status = " + bstMy.isEmpty());
                    break;
                case 7:
                    bstMy.clear();
                    System.out.println("Nodes = " + bstMy.countNodes());
                    break;
                case 8:
                    // bstMy.print(); // vertical
                    bstMy.printTree(); // horizontal
                    break;
                case 9:
                    System.out.println("Installation on the root of a tree: "+ bstMy.iteratorRoot());
                    break;
                case 10:
                    System.out.println("Has next: " + bstMy.hasNext());
                    break;
                case 11:
                    System.out.println("Current position is: " + bstMy.currentElement());
                    break;
                case 12:
                    System.out.println("Previous position: " + bstMy.previous());
                    break;
                case 13:
                    it = bstMy.iterator();
                    System.out.print("in-order Lt->t->Rt: ");
                    while (it.hasNext()) {
                        System.out.print(it.next() + ", ");
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.print("\nDo you want to continue (Type y or n): ");
            ch = scan.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }
}

