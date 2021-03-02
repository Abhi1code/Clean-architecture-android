package com.mindorks.framework.mvvm.custom.rtc.webrtc.observers;

import org.webrtc.DataChannel;

import java.nio.ByteBuffer;

import timber.log.Timber;

public class CustomDataChannelObserver implements DataChannel.Observer {

    @Override
    public void onBufferedAmountChange(long l) {
        Timber.v("onBufferedAmountChange ", l);
    }

    @Override
    public void onStateChange() {
        Timber.v("onStateChange ");
    }

    @Override
    public void onMessage(DataChannel.Buffer buffer) {
        if (buffer == null) return;;
        ByteBuffer data = buffer.data;
        byte[] bytes = new byte[data.remaining()];
        data.get(bytes);
        Timber.v("onMessage ", new String(bytes));
    }
}
