package net;

import net.packets.Packet;
import net.packets.Packet00Ping;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server implements Runnable {
    private DatagramSocket socket;

    public Server() {
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(Integer.parseInt(message.substring(0, 2)));
        Packet packet = null;
        switch (type) {
            default:
            case INVALID:
                break;
            case PING:
                packet = new Packet00Ping(data);
                //System.out.println("[FROM CLIENT][" + address.getHostAddress() + ":" + port + "]> PING at " + ((Packet00Ping) packet).sentAt);
                sendData(packet.getData(), address, port);
                break;
        }
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            this.socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

