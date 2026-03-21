import java.util.Deque;

public class OpIf extends ScriptOp {

    @Override
    public void execute(Deque<byte[]> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("Error: OP_IF sin valor.");
        }

        byte[] condition = stack.pop();

        if (condition[0] == 0) {
            throw new RuntimeException("SKIP_IF");
        }
    }
}