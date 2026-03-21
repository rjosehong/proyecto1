import java.util.Deque;

public class OpElse extends ScriptOp {
    @Override
    public void execute(Deque<byte[]> stack) {
        throw new RuntimeException("ELSE");
    }
}