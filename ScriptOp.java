import java.util.Deque;

public abstract class ScriptOp {
    public abstract void execute(Deque<byte[]> stack);
}