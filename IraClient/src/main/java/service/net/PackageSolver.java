package service.net;

import model.log.LogManager;
import net.server.AbstractServer;
import net.tool.connectionManager.ConnectionManager;
import net.tool.connectionSolver.ConnectionMessage;
import net.tool.connectionSolver.ConnectionStatus;
import net.tool.packageSolver.PackageStatus;
import net.tool.packageSolver.packageReader.LadderProtocolReader;
import net.tool.packageSolver.packageReader.PackageReader;
import net.tool.packageSolver.packageWriter.LadderProtocolWriter;
import net.tool.packageSolver.packageWriter.PackageWriter;
import net.tool.packageSolver.packageWriter.packageWriterFactory.LadderProtocolFactory;
import service.ProtocolBuilder;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by xlo on 16-1-1.
 * it's the abstract package server
 */
public class PackageSolver extends AbstractServer {

    private volatile PackageReader packageReader;
    private volatile PackageWriter packageWriter;

    public PackageSolver(ConnectionMessage connectionMessage) {
        super(connectionMessage);
    }

    @Override
    public ConnectionStatus whenInit() {
        LogManager.getLogManager().writeLog("log", "connect", this.getConnectionMessage().getSocket().socket().getInetAddress());
        this.packageReader = new LadderProtocolReader(this.getConnectionMessage().getSocket());
        this.packageWriter = new LadderProtocolWriter(this.getConnectionMessage().getSocket());
        return ConnectionStatus.CONNECTING;
    }

    @Override
    public ConnectionStatus whenConnecting() {
        if (this.getConnectionMessage().getSelectionKey().isConnectable()) {
            SocketChannel socket = this.getConnectionMessage().getSocket();
            try {
                if (socket.finishConnect()) {
                    this.addMessage(ProtocolBuilder.testCommand());
                    return getNextStatusWhenPackageEnd();
                } else {
                    return ConnectionStatus.CONNECTING;
                }
            } catch (IOException e) {
                return ConnectionStatus.ERROR;
            }
        } else {
            return ConnectionStatus.CONNECTING;
        }
    }

    @Override
    public ConnectionStatus whenWaiting() {
        if (this.packageWriter.getPackageQueueSize() == 0) {
            return ConnectionStatus.READING;
        } else {
            return ConnectionStatus.WRITING;
        }
    }

    @Override
    public ConnectionStatus whenReading() {
        try {
            PackageStatus packageStatus = packageReader.read();
            if (packageStatus.equals(PackageStatus.END)) {
                byte[] body = this.packageReader.getBody();
                try {
                    new NetMessageSolver().solveMessage(body, this.getConnectionMessage().getSocket().socket());
                } catch (Exception e) {
                    return ConnectionStatus.ERROR;
                }
                return ConnectionStatus.READING;
            } else if (packageStatus.equals(PackageStatus.WAITING)) {
                return getNextStatusWhenPackageEnd();
            } else if (packageStatus.equals(PackageStatus.ERROR)) {
                return ConnectionStatus.ERROR;
            } else if (packageStatus.equals(PackageStatus.CLOSED)) {
                return ConnectionStatus.CLOSE;
            } else {
                return ConnectionStatus.READING;
            }
        } catch (Exception e) {
            return ConnectionStatus.ERROR;
        }
    }

    @Override
    public ConnectionStatus whenWriting() {
        try {
            PackageStatus packageStatus = this.packageWriter.write();
            if (packageStatus.equals(PackageStatus.END)) {
                return getNextStatusWhenPackageEnd();
            } else if (packageStatus.equals(PackageStatus.WAITING)) {
                return ConnectionStatus.WAITING;
            } else if (packageStatus.equals(PackageStatus.ERROR)) {
                return ConnectionStatus.ERROR;
            } else if (packageStatus.equals(PackageStatus.CLOSED)) {
                return ConnectionStatus.CLOSE;
            } else {
                return ConnectionStatus.READING;
            }
        } catch (IOException e) {
            return ConnectionStatus.ERROR;
        }
    }

    private ConnectionStatus getNextStatusWhenPackageEnd() {
        if (this.packageWriter.getPackageQueueSize() == 0) {
            this.getConnectionMessage().getSelectionKey().interestOps(SelectionKey.OP_READ);
        } else {
            this.getConnectionMessage().getSelectionKey().interestOps(SelectionKey.OP_WRITE);
        }
        return ConnectionStatus.WAITING;
    }

    @Override
    public ConnectionStatus whenClosing() {
        if (this.getConnectionMessage() != null) {
            ConnectionManager.getSolverManager().removeConnection(this.getConnectionMessage().getSocket().socket());
            this.getConnectionMessage().closeSocket();
        }
        return null;
    }

    @Override
    public ConnectionStatus whenError() {
        return ConnectionStatus.CLOSE;
    }

    public void addMessage(byte[] all) {
        byte[] commandPackage = LadderProtocolFactory.getLadderProtocolFactory().setMessage(all).getLadderProtocolByte();
        this.packageWriter.addPackage(commandPackage, 0);
        this.getConnectionMessage().getSelectionKey().interestOps(SelectionKey.OP_WRITE);
        this.getConnectionMessage().getSelectionKey().selector().wakeup();
    }
}
