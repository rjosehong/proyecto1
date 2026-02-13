import java.util.Deque;

public class OpN extends ScriptOp {

    private final int value;

    public OpN(int value) {
        if (value < -1 || value > 16) {
            throw new IllegalArgumentException("OP_N solo soporta valores entre -1 y 16");
        }
        this.value = value;
    }

    @Override
    public void execute(Deque<byte[]> stack) {
        stack.push(new byte[]{(byte) value});
    }
}
