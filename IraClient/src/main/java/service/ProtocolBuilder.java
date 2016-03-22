package service;

/**
 * Created by xlo on 16/2/23.
 * it's the protocol builder
 */
public final class ProtocolBuilder {

    public static byte[] testCommand() {
        return "testCommand#{}".getBytes();
    }

}
