package BST_AnyType;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator<T extends Comparable<T>> implements Iterator<T> {

    protected BSTreeNode<T> root;
    protected Stack<BSTreeNode<T>> visiting;
    protected Stack<Boolean> visitingRightChild;

    boolean preorder;
    boolean inorder;
    boolean postorder;

    public TreeIterator<T> InIterator() {
        return new TreeIterator<>(root);
    }


    public TreeIterator(BSTreeNode<T> root) {
        this.root = root;
        visiting = new Stack<>();
        visitingRightChild = new Stack<>();
        preorder = false;
        inorder = true;
        postorder = false;
    }

    @Override
    public boolean hasNext() {
        return (root != null);
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("no more elements");
        }
        if (preorder) {
            return preorderNext();
        } else if (inorder) {
            return inorderNext();
        } else if (postorder) {
            return postorderNext();
        } else {
            assert (false);
            return null;
        }
    }

    public boolean previousPos()
    {
        return true;
    }


    private T preorderNext() {
        if (visiting.empty()) {
            visiting.push(root);
        }
        BSTreeNode<T> node = visiting.pop();

        if (node.right != null) {
            visiting.push(node.right);
        }
        if (node.left != null) {
            visiting.push(node.left);
        }

        if (visiting.empty()) {
            root = null;
        }
        return node.element;
    }
    private void pushLeftmostNode(BSTreeNode<T> node) {

        if (node != null) {
            visiting.push(node);
            pushLeftmostNode(node.left);
        }
    }

    private T inorderNext() {
        if (visiting.empty()) {

            pushLeftmostNode(root);
        }
        BSTreeNode<T> node = visiting.pop();
        T result = node.element;

        if (node.right != null) {
            BSTreeNode<T> right = node.right;
            pushLeftmostNode(right);
        }

        if (visiting.empty()) {
            root = null;
        }
        return result;
    }

    private void pushLeftmostNodeRecord(BSTreeNode<T> node) {

        if (node != null) {
            visiting.push(node);
            visitingRightChild.push(false);
            pushLeftmostNodeRecord(node.left);
        }
    }

    private T postorderNext() {
        if (visiting.empty()) {

            pushLeftmostNodeRecord(root);
        }

        if ((visiting.peek().right == null) ||
                (visitingRightChild.peek())) {

            T result = visiting.pop().element;
            visitingRightChild.pop();
            if (visiting.empty()) {
                root = null;
            }
            return result;
        } else {

            if (visitingRightChild.pop()) {
                assert (false);
            }
            visitingRightChild.push(true);
            BSTreeNode<T> right = visiting.peek().right;
            assert (right != null);
            pushLeftmostNodeRecord(right);
            return postorderNext();
        }
    }
}
