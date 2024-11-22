class Main {
  public static void main(String[] args) {

      // Step 1 - Create a BST tree object called lab5Tree
      BinarySearchTree lab5Tree = new BinarySearchTree();

      // Step 2 - Insert the following values: 13, 22, 36, 5, 48, 17, 39, 2, 26, 40, 29, 34, 10
      lab5Tree.insert(13);
      lab5Tree.insert(22);
      lab5Tree.insert(36);
      lab5Tree.insert(5);
      lab5Tree.insert(48);
      lab5Tree.insert(17);
      lab5Tree.insert(39);
      lab5Tree.insert(2);
      lab5Tree.insert(26);
      lab5Tree.insert(40);
      lab5Tree.insert(29);
      lab5Tree.insert(34);
      lab5Tree.insert(10);

      // Step 3 - Delete the value 17
      lab5Tree.delete(17);

      // Step 4 - Traverse and output the values using inorder (sorted)
      System.out.println("Inorder traversal:");
      lab5Tree.inorder();

      // Step 5 - Traverse and output the values using postorder
      System.out.println("\nPostorder traversal:");
      lab5Tree.postorder();

      // Step 6 - Traverse and output the values using preorder
      System.out.println("\nPreorder traversal:");
      lab5Tree.preorder();

      // Step 7 - Display the result of a search for the value 36
      System.out.println("\nSearch for 36: " + lab5Tree.search(36));

      // Step 8 - Display the result of a search for the value 37
      System.out.println("Search for 37: " + lab5Tree.search(37));

      // Step 9 - Using the path() method, display the path from the root to 2
      System.out.println("\nPath from root to 2: " + lab5Tree.path(2));

      // Step 10 - Display the path from the root to 34
      System.out.println("Path from root to 34: " + lab5Tree.path(34));
  }
}

class BinarySearchTree {
  private class Node {
      int value;
      Node left, right;

      public Node(int value) {
          this.value = value;
          left = right = null;
      }
  }

  private Node root;

  public BinarySearchTree() {
      root = null;
  }

  // Insert a value into the BST
  public void insert(int value) {
      root = insertRecursive(root, value);
  }

  private Node insertRecursive(Node node, int value) {
      if (node == null) {
          return new Node(value);
      }

      if (value < node.value) {
          node.left = insertRecursive(node.left, value);
      } else if (value > node.value) {
          node.right = insertRecursive(node.right, value);
      }

      return node;
  }

  // Delete a value from the BST
  public void delete(int value) {
      root = deleteRecursive(root, value);
  }

  private Node deleteRecursive(Node node, int value) {
      if (node == null) {
          return null;
      }

      if (value < node.value) {
          node.left = deleteRecursive(node.left, value);
      } else if (value > node.value) {
          node.right = deleteRecursive(node.right, value);
      } else {
          // Node to be deleted found

          // Case 1: Node has no children (leaf node)
          if (node.left == null && node.right == null) {
              return null;
          }

          // Case 2: Node has only one child
          if (node.left == null) {
              return node.right;
          } else if (node.right == null) {
              return node.left;
          }

          // Case 3: Node has two children
          node.value = findMin(node.right).value;
          node.right = deleteRecursive(node.right, node.value);
      }

      return node;
  }

  private Node findMin(Node node) {
      while (node.left != null) {
          node = node.left;
      }
      return node;
  }

  // Inorder traversal (sorted order)
  public void inorder() {
      inorderRecursive(root);
      System.out.println();
  }

  private void inorderRecursive(Node node) {
      if (node != null) {
          inorderRecursive(node.left);
          System.out.print(node.value + " ");
          inorderRecursive(node.right);
      }
  }

  // Postorder traversal
  public void postorder() {
      postorderRecursive(root);
      System.out.println();
  }

  private void postorderRecursive(Node node) {
      if (node != null) {
          postorderRecursive(node.left);
          postorderRecursive(node.right);
          System.out.print(node.value + " ");
      }
  }

  // Preorder traversal
  public void preorder() {
      preorderRecursive(root);
      System.out.println();
  }

  private void preorderRecursive(Node node) {
      if (node != null) {
          System.out.print(node.value + " ");
          preorderRecursive(node.left);
          preorderRecursive(node.right);
      }
  }

  // Search for a value in the BST
  public boolean search(int value) {
      return searchRecursive(root, value);
  }

  private boolean searchRecursive(Node node, int value) {
      if (node == null) {
          return false;
      }

      if (value == node.value) {
          return true;
      }

      return value < node.value
              ? searchRecursive(node.left, value)
              : searchRecursive(node.right, value);
  }

  // Find the path from root to a given value
  public String path(int value) {
      StringBuilder path = new StringBuilder();
      if (findPath(root, value, path)) {
          return path.toString();
      }
      return "No path found";
  }

  private boolean findPath(Node node, int value, StringBuilder path) {
      if (node == null) {
          return false;
      }

      path.append(node.value).append(" ");

      if (node.value == value) {
          return true;
      }

      if (value < node.value) {
          if (findPath(node.left, value, path)) {
              return true;
          }
      } else {
          if (findPath(node.right, value, path)) {
              return true;
          }
      }

      path.delete(path.length() - 2, path.length());  // Remove last value if not part of the path
      return false;
  }
}
