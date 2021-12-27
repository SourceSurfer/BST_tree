/**
 * 	Programmer:			Nikolai Moriakov
 * 	Project:			BST
 * 	Last Modified:		16.12.2021
 * 	Purpose:			To implement as BST in Java
  * This class represents a Generic Binary Tree that implements insert, find, and
 * delete operations.
 *
 * @author	Nikolai Moriakov
 * @date	16.12.2021
 * @param	<T>	Any generic object that implements Comparable
 */

package BST_AnyType;

import java.util.Iterator;
import java.util.Stack;


public class BST<T extends Comparable<T>> implements ISet<T> {

    protected BSTreeNode<T> root = null;
    TreeIterator<T> in;
    protected int numElements;

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public void clear() {
        root = null;
        numElements = 0;
    }

    @Override
    public int size() {
        return numElements;
    }

    // Удаление элемента с заданным ключом
    @Override
    public boolean remove(T value) {
        int oldSize = size();
        root = remove(value, root);
        return oldSize != size();
    }

    public BSTreeNode<T> remove(T value, BSTreeNode<T> node)
    {
        if (node == null) {
            return null;
        }
        if (value.compareTo(node.element) == 0) {
            if (node.left == null) {
                numElements--;
                return node.right;
            } else if (node.right == null) {
                numElements--;
                return node.left;
            } else {
                node.element = getRightmost(node.left);
                node.left = remove(node.element, node.left);
            }
        } else {
            if (value.compareTo(node.element) < 0) {
                node.left = remove(value, node.left);
            } else {
                node.right = remove(value, node.right);
            }
        }
        return node;
    }

    protected T getRightmost(BSTreeNode<T> node) {
        assert(node != null);
        BSTreeNode<T> right = node.right;
        if (right == null) {
            return node.element;
        } else {
            return getRightmost(right);
        }
    }

    @Override
    public int countNodes()
    {
        return sizeOfTree(root);
    }

    @Override
    public void print() {
        print(this.root, 0);

    }

    public void print(BSTreeNode<T> start, int level)
    {
        if (start != null)
        {
            print(start.right, level + 1);
            printTabs(level);
            System.out.println(start.element);
            print(start.left, level + 1);
        }
    }


    public void printTree(){
        Stack globalStack = new Stack();
        globalStack.push(root);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (!isRowEmpty) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                BSTreeNode<T> temp = (BSTreeNode<T>) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');

            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
    }

    private void printTabs(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
    }


    @Override
    public boolean insert(T value) {
        int oldSize = size();
        this.root = insert(value, root);
        return oldSize != size();
    }

    public BSTreeNode<T> insert(T value, BSTreeNode<T> node)
    {
        if (node == null) {
            numElements++;
            return new BSTreeNode<>(value);
        }
        else if (value.compareTo(node.element) == 0) {

            node.element = value;
        } else
            if (value.compareTo(node.element) < 0) {

            node.left = insert(value, node.left);
        } else {

            node.right = insert(value, node.right);
        }
        return node;
    }

    // Поиск элемента с заданным ключом
    @Override
    public T find(T elem) {

        BSTreeNode<T> found = find(root, elem);
        return (found == null) ? (T) "Not found" :  found.element;
    }

    public BSTreeNode<T> find(BSTreeNode<T> node, T elem)
    {
        if (node == null)
        {
            return null;
        }
        int comparison = node.element.compareTo(elem);
        if (comparison > 0)
        {
            return find(node.left, elem);
        }
        else if (comparison < 0)
        {
            return find(node.right, elem);
        }
        else
        {
            return node;
        }
    }

    public T min()
    {
        if (isEmpty())
            return null;
        else
        {
            BSTreeNode<T> node = root;
            while (node.getLeftChild() != null)
                node = node.getLeftChild();
            return node.getValue();
        }
    }

    public T max()
    {
        if (isEmpty())
            return null;
        else
        {
            BSTreeNode<T> node = root;
            while (node.getRightChild() != null)
                node = node.getRightChild();
            return node.getValue();
        }
    }

    // опрос размера дерева
    public int sizeOfTree(BSTreeNode<T> root){
        if( root == null ){
            return 0;
        }
        return sizeOfTree(root.getLeftChild()) + 1 + sizeOfTree(root.getRightChild());
    }

    public String toString() {
        return toString(root);
    }


    private String toString(BSTreeNode<T> node) {
        if (node == null) {
            return "";
        } else {
            return node + "" + toString(node.left)  +
                   "" + toString(node.right);
        }
    }

    public Iterator<T> iterator() {
        return  in.InIterator();
    }

    public boolean iteratorRoot() {
        in = new TreeIterator<T>(root);
        return true;
    }

    public boolean hasNext()
    {
        return in.hasNext();
    }

    public T currentElement()
    {
        return in.currentPos();
    }

    public T previous()
    {
        return in.previousPos();
    }

    public boolean nextPos(){
        return in.nextPos();
    }
}
