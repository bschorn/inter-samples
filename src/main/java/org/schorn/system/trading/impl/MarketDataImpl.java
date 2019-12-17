/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schorn.system.trading.impl;

import java.io.IOException;
import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.schorn.system.trading.Entity;
import org.schorn.system.trading.Entity.Instrument;
import org.schorn.system.trading.MarketData;

/**
 *
 * @author bschorn
 */
public class MarketDataImpl implements MarketData {

    static public final int MAP_SIZE = 6 * 4 * 1000;
    static public final MarketData instance = new MarketDataImpl();

    private MappedByteBuffer mappedByteBuffer;
    private final AtomicInteger feedIds = new AtomicInteger(0);
    private final Map<Instrument, LevelOne> registered = new HashMap<>();

    @Override
    public LevelOne register(Entity.Instrument instrument) {
        int feedId = this.feedIds.getAndIncrement();
        this.registered.put(instrument, new LevelOneImpl(this, instrument, feedId));
        return this.registered.get(instrument);
    }

    @Override
    public LevelOne getLevelOne(Instrument instrument) {
        return this.registered.get(instrument);
    }

    public void connect(URI uri) throws IOException {
        int mapSize = this.registered.size() * 4 * 6;
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(Path.of(uri), EnumSet.of(StandardOpenOption.READ))) {
            this.mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, mapSize);
        }
    }

    float read(int pos) {
        return this.mappedByteBuffer.getFloat(pos);
    }

    void write(int pos, float... values) {
        for (int i = 0; i < values.length; i += 1) {
            this.mappedByteBuffer.putFloat(pos + 4, values[i]);
        }
    }

    @Override
    public void setBid(Instrument instrument, float px, float size) {
        this.write(this.registered.get(instrument).feedId(), px, size);
    }

    @Override
    public void setAsk(Instrument instrument, float px, float size) {
        this.write(this.registered.get(instrument).feedId() + 8, px, size);
    }

    @Override
    public void setLast(Instrument instrument, float px, float qty) {
        this.write(this.registered.get(instrument).feedId() + 16, px, qty);
    }

}
