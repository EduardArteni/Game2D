package net;

import net.packets.Packet;
import net.packets.Packet00Ping;

import java.io.IOException;
import java.net.*;

public class Client implements Runnable {
    public int ping = 0;
    private InetAddress ipAddress;
    private DatagramSocket socket;

    public Client(String ipAddress) {
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        switch (type) {
            default:
            case INVALID:
                break;
            case PING:
                Packet00Ping packet00Ping = new Packet00Ping(data);
                packet00Ping.returnedAt = System.currentTimeMillis();
                //System.out.println("[FROM SERVER][" + address.getHostAddress() + ":" + port + "]> PONG sent at " + packet00Ping.sentAt + ", returned at " + packet00Ping.returnedAt);
                ping = (int) (packet00Ping.returnedAt - packet00Ping.sentAt);
                break;
        }
    }

    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
