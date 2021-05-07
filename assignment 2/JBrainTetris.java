import javax.swing.*;
import java.awt.*;

public class JBrainTetris extends JTetris {

    private Brain brain;
    private JCheckBox brainMode;
    private JSlider adversary;
    private JLabel status;

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
        brainMode = new JCheckBox("Brain active");
        adversary = new JSlider(0, 100, 0);
        adversary.setPreferredSize(new Dimension(100,15));
        status = new JLabel();

        panel.add(new JLabel("Brain:"));
        panel.add(new JLabel("Adversary:"));
        panel.add(brainMode);
        panel.add(adversary);
        panel.add(status);

        return panel;
    }

    @Override
    public void tick(int verb){
        if(brainMode.isSelected() && verb == DOWN){
            Brain.Move move = null;
            board.undo();
            move = brain.bestMove(board, currentPiece, board.getHeight(), null);
            if(move!=null){
                if(!move.piece.equals(currentPiece)) {
                    super.tick(ROTATE);
                }
                if(move.x > currentX) {
                    super.tick(RIGHT);
                }
                else if (move.x < currentX) {
                    super.tick(LEFT);
                }
            }
        }
        super.tick(verb);
    }


    @Override
    public Piece pickNextPiece(){
        Piece piece = super.pickNextPiece();
        if(random.nextInt(100) < adversary.getValue()){
            status.setText("*ok*");
            double worstScore = 0;
            for(Piece p : pieces){
                Brain.Move move = brain.bestMove(board,p,board.getHeight(),null);
                board.undo();
                if(move.score > worstScore){
                    piece = p;
                    worstScore = move.score;
                }
            }
            return piece;
        }else{
            status.setText("ok");
            return super.pickNextPiece();
        }
    }



}