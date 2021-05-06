import javax.swing.*;
import java.awt.*;

public class JBrainTetris extends JTetris {

    private Brain brain;
    private JCheckBox brainMode;

    JBrainTetris(int pixels) {
        super(pixels);
        brain = new DefaultBrain();
    }

    public static void main(String[] args) {

        JBrainTetris tetris = new JBrainTetris(16);
        JFrame frame = JTetris.createFrame(tetris);
        frame.setVisible(true);
    }

    @Override
    public JComponent createControlPanel() {
        JPanel panel = (JPanel)super.createControlPanel();

        panel.add(new JLabel("Brain:"));
        brainMode = new JCheckBox("Brain active");
        panel.add(brainMode);

        return panel;
    }

    @Override
    public void tick(int verb){
        if(brainMode.isSelected() && verb == DOWN){
            Brain.Move move = null;
            board.undo();
            move = brain.bestMove(board, currentPiece, board.getHeight(), move);

            if(move!=null){
                if(!move.piece.equals(currentPiece))
                    super.tick(ROTATE);
                if(move.x > currentX)
                    super.tick(RIGHT);
                else if (move.x < currentX)
                    super.tick(LEFT);
            }
        }
        super.tick(verb);
    }


}