import java.util.*;

public class ScriptInterpreter {

    private final Deque<byte[]> stack = new ArrayDeque<>();
    private final boolean trace;

    public ScriptInterpreter(boolean trace) {
        this.trace = trace;
    }

    public boolean execute(List<ScriptOp> script) {

        for (ScriptOp op : script) {
            op.execute(stack);

            if (trace) {
                printStack();
            }
        }

        if (stack.isEmpty()) return false;
            byte[] top = stack.peek();
            return top.length > 0 && top[0] != 0;
    }


    private void printStack() {
        System.out.println("STACK:");
        for (byte[] item : stack) {
            System.out.println(Arrays.toString(item));
        }
        System.out.println("--------");
    }

    public Deque<byte[]> getStack() {
        return stack;
    }
}
