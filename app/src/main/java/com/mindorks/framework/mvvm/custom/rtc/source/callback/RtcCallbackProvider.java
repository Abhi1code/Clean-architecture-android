package com.mindorks.framework.mvvm.custom.rtc.source.callback;

import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.interfaces.RtcInterface;

import org.webrtc.VideoTrack;

import androidx.annotation.NonNull;

public class RtcCallbackProvider implements RtcInterface {

    private RtcCallbacks.IceCandidateCallback iceCandidateCallback;
    private RtcCallbacks.RemoteStreamCallback remoteStreamCallback;
    private RtcCallbacks.SessionDescCallback sessionDescCallback;
    private RtcCallbacks.ConnectionStateChangeCallback connectionStateChangeCallback;
    private RtcCallbacks.DataChannelStateChangeCallback dataChannelStateChangeCallback;
    private RtcCallbacks.MessageCallback messageCallback;
    private RtcCallbacks.ErrorCallback errorCallback;

    public RtcCallbackProvider() { }

    public void removeAllCallbacks() {
        this.iceCandidateCallback = null;
        this.remoteStreamCallback = null;
        this.sessionDescCallback = null;
        this.connectionStateChangeCallback = null;
        this.dataChannelStateChangeCallback = null;
        this.messageCallback = null;
        this.errorCallback = null;
    }

    @Override
    public void onIceCandidate(@NonNull BaseIceCandidateData baseIceCandidateData) {
        if (iceCandidateCallback != null) iceCandidateCallback.onIceCandidate(baseIceCandidateData);
    }

    public void addIceCandidateCallback(RtcCallbacks.IceCandidateCallback iceCandidateCallback) {
        this.iceCandidateCallback = iceCandidateCallback;
    }

    public void removeIceCandidateCallback() {
        this.iceCandidateCallback = null;
    }

    @Override
    public void onRemoteStreamAdded(@NonNull VideoTrack videoTrack) {
        if (remoteStreamCallback != null) remoteStreamCallback.onRemoteStreamAdded(videoTrack);
    }

    public void addRemoteStreamCallback(RtcCallbacks.RemoteStreamCallback remoteStreamCallback) {
        this.remoteStreamCallback = remoteStreamCallback;
    }

    public void removeRemoteStreamCallback() {
        this.remoteStreamCallback = null;
    }

    @Override
    public void onSessionDescription(@NonNull BaseSessionDescData baseSessionDescData) {
        if (sessionDescCallback != null) sessionDescCallback.onSessionDescription(baseSessionDescData);
    }

    public void addSessionDescriptionCallback(RtcCallbacks.SessionDescCallback sessionDescCallback) {
        this.sessionDescCallback = sessionDescCallback;
    }

    public void removeSessionDescriptionCallback() {
        this.sessionDescCallback = null;
    }

    @Override
    public void onConnectionOpenStateChange(boolean state) {
        if (connectionStateChangeCallback != null) connectionStateChangeCallback.onConnectionOpenStateChange(state);
    }

    public void addConnectionOpenStateChangeCallback(RtcCallbacks.ConnectionStateChangeCallback connectionStateChangeCallback) {
        this.connectionStateChangeCallback = connectionStateChangeCallback;
    }

    public void removeConnectionOpenStateChangeCallback() {
        this.connectionStateChangeCallback = null;
    }

    @Override
    public void onDataChannelOpenStateChange(boolean state) {
        if (dataChannelStateChangeCallback != null) dataChannelStateChangeCallback.onDataChannelOpenStateChange(state);
    }

    public void addDataChannelOpenStateChangeCallback(RtcCallbacks.DataChannelStateChangeCallback dataChannelStateChangeCallback) {
        this.dataChannelStateChangeCallback = dataChannelStateChangeCallback;
    }

    public void removeDataChannelOpenStateChangeCallback() {
        this.dataChannelStateChangeCallback = null;
    }

    @Override
    public void onMessage(@NonNull byte[] msg) {
        if (messageCallback != null) messageCallback.onMessage(msg);
    }

    public void addMessageCallback(RtcCallbacks.MessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }

    public void removeMessageCallback() {
        this.messageCallback = null;
    }

    @Override
    public void onError(@NonNull Exception e) {
        if (errorCallback != null) errorCallback.onError(e);
    }

    public void addErrorCallback(RtcCallbacks.ErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }

    public void removeErrorCallback() {
        this.errorCallback = null;
    }
}
