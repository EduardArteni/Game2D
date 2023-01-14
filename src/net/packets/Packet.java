package net.packets;

import net.Client;
import net.Server;

public abstract class Packet {
    public byte packetId;

    public Packet(byte packetId) {
        this.packetId = packetId;
    }

    public static PacketTypes lookupPacket(int id) {
        for (PacketTypes p : PacketTypes.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return PacketTypes.INVALID;
    }

    public static PacketTypes lookupPacket(String packetId) {
        try {
            return lookupPacket(Integer.parseInt(packetId));
        } catch (Exception e) {
            e.printStackTrace();
            return PacketTypes.INVALID;
        }
    }

    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(2);
    }

    public abstract void writeData(Client client);

    public abstract void writeData(Server server);

    public abstract byte[] getData();

    public enum PacketTypes {
        INVALID(-1), PING(00);
        private int packetId;

        private PacketTypes(int packetId) {
            this.packetId = packetId;
        }

        public int getId() {
            return packetId;
        }
    }
}
