import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ColorSquare extends JFrame {
    private JPanel squarePanel;
    private JButton leftTopButton, rightTopButton, leftBottomButton, rightBottomButton;

    public ColorSquare() {
        setTitle("Color Square");
        setSize(500, 500); // Adjusted window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("Color Square", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center container panel with border
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Add some padding

        // Square divided into four quadrants
        squarePanel = new JPanel();
        squarePanel.setLayout(new GridLayout(2, 2));
        squarePanel.add(new ColorPanel(Color.GREEN));
        squarePanel.add(new ColorPanel(Color.YELLOW));
        squarePanel.add(new ColorPanel(Color.RED));
        squarePanel.add(new ColorPanel(Color.BLUE));
        centerPanel.add(squarePanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Buttons to the right and left of the centered square
        JPanel leftButtonPanel = new JPanel();
        leftButtonPanel.setLayout(new GridLayout(2, 1));
        leftTopButton = new JButton("Change");
        leftBottomButton = new JButton("Change");
        leftButtonPanel.add(leftTopButton);
        leftButtonPanel.add(leftBottomButton);

        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setLayout(new GridLayout(2, 1));
        rightTopButton = new JButton("Change");
        rightBottomButton = new JButton("Change");
        rightButtonPanel.add(rightTopButton);
        rightButtonPanel.add(rightBottomButton);

        centerPanel.add(leftButtonPanel, BorderLayout.WEST);
        centerPanel.add(rightButtonPanel, BorderLayout.EAST);

        // Action listeners for buttons
        leftTopButton.addActionListener(e -> changeColor(0));
        rightTopButton.addActionListener(e -> changeColor(1));
        leftBottomButton.addActionListener(e -> changeColor(2));
        rightBottomButton.addActionListener(e -> changeColor(3));
    }

    private void changeColor(int quadrant) {
        Color newColor = new Color(new Random().nextInt(0xFFFFFF));
        ColorPanel panel = (ColorPanel) squarePanel.getComponent(quadrant);
        panel.changeColor(newColor);
        panel.repaint();
    }

    class ColorPanel extends JPanel {
        private Color color;

        public ColorPanel(Color color) {
            this.color = color;
        }

        public void changeColor(Color newColor) {
            this.color = newColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ColorSquare frame = new ColorSquare();
            frame.setVisible(true);
        });
    }
}
