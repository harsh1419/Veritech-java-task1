import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleSnakeGame extends JFrame {

  private static final int WIDTH = 400;
  private static final int HEIGHT = 400;
  private static final int UNIT_SIZE = 10;
  private int x = 0;
  private int y = 0;
  private int foodX;
  private int foodY;
  private char direction = 'R'; // R - Right, L - Left, U - Up, D - Down

  public SimpleSnakeGame() {
    setTitle("Simple Snake Game");
    setSize(WIDTH, HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    addKeyListener(new MyKeyAdapter());
    setBackground(Color.black);
    setFocusable(true);
    newFood();
    Timer timer = new Timer(
      100,
      e -> {
        move();
        checkFood();
        repaint();
      }
    );
    timer.start();
  }

  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.green);
    g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);
    g.setColor(Color.red);
    g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
  }

  public void move() {
    switch (direction) {
      case 'R' -> x += UNIT_SIZE;
      case 'L' -> x -= UNIT_SIZE;
      case 'U' -> y -= UNIT_SIZE;
      case 'D' -> y += UNIT_SIZE;
    }
  }

  public void newFood() {
    foodX = (int) (Math.random() * (WIDTH / UNIT_SIZE)) * UNIT_SIZE;
    foodY = (int) (Math.random() * (HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
  }

  public void checkFood() {
    if (x == foodX && y == foodY) {
      newFood();
    }
  }

  private class MyKeyAdapter extends KeyAdapter {

    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
          if (direction != 'R') direction = 'L';
          break;
        case KeyEvent.VK_RIGHT:
          if (direction != 'L') direction = 'R';
          break;
        case KeyEvent.VK_UP:
          if (direction != 'D') direction = 'U';
          break;
        case KeyEvent.VK_DOWN:
          if (direction != 'U') direction = 'D';
          break;
      }
    }
  }

  public static void main(String[] args) {
    new SimpleSnakeGame().setVisible(true);
  }
}
