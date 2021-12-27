/**
 * Узел в двоичном дереве
 * @author         Nikolai Moriakov
 * @date           December 17, 2021
 */

package BST_AnyType;
// Шаблонный класс-контейнер
public class BSTreeNode<T extends Comparable<? super T>> {


    protected T element;    // данные, хранящиеся на узле
    protected BSTreeNode<T> left;     // ссылка на левое поддерево
    protected BSTreeNode<T> right;    // ссылка на правое поддерево


    // конструктор для построения узла без поддеревьев

    public BSTreeNode(T elem) {
        this(elem, null, null);
    }


    /**
     * конструктор для создания узла с указанными поддеревьями
     *
     * @param elem узел дерева
     * @param lt   левое поддерево для этого узла
     * @param rt   правое поддерево для этого узла
     */
    public BSTreeNode(T elem, BSTreeNode<T> lt, BSTreeNode<T> rt) {
        this.element = elem;
        this.left = lt;
        this.right = rt;
    }

    public int size(){
        return 0;
    }

    public BSTreeNode<T> clear(){
        return this;
    }

    public boolean isEmpty(){
        return true;
    }

    public T getValue() {
        return this.element;
    }

    public BSTreeNode<T> add(T element){
        return new BSTreeNode<>(element);
    }


    public void setLeftChild(BSTreeNode<T> leftChild) {
        this.left = leftChild;
    }

    public void setRightChild(BSTreeNode<T> rightChild) {
        this.right = rightChild;
    }

    public BSTreeNode<T> getLeftChild() {
        return this.left;
    }

    public BSTreeNode<T> getRightChild() {
        return this.right;
    }

    @Override
    public String toString() {
        return "[" + element.toString() + "]";
    }
}

