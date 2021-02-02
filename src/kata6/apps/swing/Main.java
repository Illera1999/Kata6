package kata6.apps.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata6.control.BlockPresenter;
import kata6.control.Command;
import kata6.control.DownCommand;
import kata6.control.LeftCommand;
import kata6.control.RightCommand;
import kata6.control.UpCommand;
import kata6.model.Block;
import kata6.view.BlockDisplay;

public class Main extends JFrame{

    public static void main(String[] args) {
        new Main().execute();
    }

    private BlockPanel blockDisplay;
    private Map<String,Command> commands = new HashMap<>();
    private Block block;
    private BlockPresenter blockPresenter;
    
    public Main() {
        this.setTitle("Block shifter");
        this.setSize(700,722);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        this.commands = createCommands();
    }

    private void execute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel(Block.MAX);
        this.blockDisplay=blockPanel;
        return blockPanel;
    }

    private JMenuBar toolbar() {
        JMenuBar toolbar = new JMenuBar();
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolbar.add(button("left"));
        toolbar.add(button("right"));
        toolbar.add(button("up"));
        toolbar.add(button("down"));
        return toolbar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                commands.get(name).execute();
            }
        });
        return button;
    }

    private Map<String, Command> createCommands() {
        Map<String , Command> commands = new HashMap<>();
        commands.put("Up",new UpCommand(block));
        commands.put("Down",new DownCommand(block));
        commands.put("Left",new LeftCommand(block));
        commands.put("Right",new RightCommand(block));
        return commands;
    }
}
