package kata6.view;

import kata6.model.Block;

public interface BlockDisplay extends Block.Observer{
    
    public static final int SIZE = 100;
    public void paintBlock(int x,int y);
    public void on(Moved moved);
    /*
    void display(Block block);
    Block block();
    */
    public interface Moved {
        void to (int x, int y);
    }
}
