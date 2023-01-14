package net.packets;

import net.Client;
import net.Server;

public class Packet00Ping extends Packet {

    public long sentAt;
    public long returnedAt;

    public Packet00Ping() {
        super((byte) 00);
    }

    public Packet00Ping(byte[] data) {
        super((byte) 00);
        String message = new String(data).trim().substring(2);
        String split[] = message.split(",");
        sentAt = Long.parseLong(split[0]);
        returnedAt = Long.parseLong(split[1]);
    }

    @Override
    public void writeData(Client client) {
        sentAt = System.currentTimeMillis();
        client.sendData(getData());
    }

    @Override
    public void writeData(Server server) {
        //server.sendData(getData());
    }

    @Override
    public byte[] getData() {
        return ("00" + sentAt + "," + returnedAt).getBytes();
    }
}
