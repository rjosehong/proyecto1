import java.util.Deque;

public class OpDrop extends ScriptOp {
    public void execute(Deque<byte[]> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("Error: OP_DROP ejecutado en una pila vacía.");
        }
        stack.pop();
    }
}