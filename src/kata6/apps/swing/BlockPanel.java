package kata6.apps.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import kata6.model.Block;
import kata6.view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay{

    private static final int SIZE = 100;
    private Block block;
    private int x;
    private int y;
    private Moved moved;
    private final int max;
    
    public BlockPanel(int max){
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        this.max = max;
    }

 
    @Override
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        
        g.setColor(Color.BLACK);
        int size = SIZE*block.MAX;
        for (int i = 0; i <= size; i+= SIZE){
            g.drawLine(i, 0, i, size);
            g.drawLine(0, i, size, i);
            
        }
        
        if(block==null) return;
        g.setColor(Color.red);
        g.fillRect((block.x()-1)*SIZE,(block.MAX - block.y())*SIZE, SIZE, SIZE);
    }
    /*
    @Override
    public void display(Block block) {
        this.block=block;
        this.block.register(this);
        repaint();
    }*/

    /*
    @Override
    public Block block() {
        return block;
    }*/

    @Override
    public void changed() {
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved=moved;
    }

    @Override
    public void paintBlock(int x, int y) {
        this.x=x;
        this.y=y;
        repaint();
    }
    
    private class MouseHandler implements MouseListener,MouseMotionListener {
        private boolean pressed = false;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getX() < x || e.getY() < y || e.getX() > x+SIZE || e.getY() > y+SIZE) return;
            pressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            moved.to(e.getX(),e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
