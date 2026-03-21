import static org.junit.Assert.*;
import org.junit.Test;

import java.security.MessageDigest;
import java.util.*;

public class TestProyecto {
    @Test
    public void testP2PKHCorrecto() throws Exception {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        byte[] signature = "firma".getBytes();
        byte[] pubKey = "clavePublica".getBytes();

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] sha = sha256.digest(pubKey);
        byte[] pubKeyHash = Arrays.copyOfRange(sha, 0, 20);

        List<ScriptOp> script = List.of(
                new PushData(signature),
                new PushData(pubKey),
                new OpDup(),
                new OpHash160(),
                new PushData(pubKeyHash),
                new OpEqualVerify(),
                new OpCheckSig()
        );

        boolean result = interpreter.execute(script);

        assertTrue(result);
    }

    @Test
    public void testP2PKHIncorrecto() throws Exception {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        byte[] badSignature = new byte[]{}; // firma vacía
        byte[] pubKey = "clavePublica".getBytes();

        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] sha = sha256.digest(pubKey);
        byte[] pubKeyHash = Arrays.copyOfRange(sha, 0, 20);

        List<ScriptOp> script = List.of(
                new PushData(badSignature),
                new PushData(pubKey),
                new OpDup(),
                new OpHash160(),
                new PushData(pubKeyHash),
                new OpEqualVerify(),
                new OpCheckSig()
        );

        boolean result = interpreter.execute(script);

        assertFalse(result);
    }

    @Test
    public void testOpDup() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        List<ScriptOp> script = List.of(
                new OpN(5),
                new OpDup()
        );

        interpreter.execute(script);

        Deque<byte[]> stack = interpreter.getStack();

        assertEquals(2, stack.size());
    }

    @Test
    public void testOpDrop() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        List<ScriptOp> script = List.of(
                new OpN(5),
                new OpDrop()
        );

        interpreter.execute(script);

        assertTrue(interpreter.getStack().isEmpty());
    }

    @Test
    public void testOpEqualTrue() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        List<ScriptOp> script = List.of(
                new OpN(5),
                new OpN(5),
                new OpEqual()
        );

        boolean result = interpreter.execute(script);

        assertTrue(result);
    }

    @Test
    public void testOpEqualFalse() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        List<ScriptOp> script = List.of(
                new OpN(5),
                new OpN(3),
                new OpEqual()
        );

        boolean result = interpreter.execute(script);

        assertFalse(result);
    }

    @Test
    public void testOpEqualVerifyFalla() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        List<ScriptOp> script = List.of(
                new OpN(5),
                new OpN(3),
                new OpEqualVerify()
        );

        boolean result = interpreter.execute(script);

        assertFalse(result);
    }

    @Test
    public void testStackVacia() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        List<ScriptOp> script = List.of(
                new OpDrop() // no funciona porque no hay datos
        );

        boolean result = interpreter.execute(script);

        assertFalse(result);
    }

    @Test
    public void testOpHash160() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        byte[] data = "hola".getBytes();

        List<ScriptOp> script = List.of(
                new PushData(data),
                new OpHash160()
        );

        interpreter.execute(script);

        byte[] result = interpreter.getStack().peek();

        assertNotNull(result);
        assertEquals(20, result.length); // hash160 = 20 bytes
    }

    @Test
    public void testOpCheckSig() {

        ScriptInterpreter interpreter = new ScriptInterpreter(false);

        List<ScriptOp> script = List.of(
                new PushData("firma".getBytes()),
                new PushData("clave".getBytes()),
                new OpCheckSig()
        );

        boolean result = interpreter.execute(script);

        assertTrue(result);
    }
}