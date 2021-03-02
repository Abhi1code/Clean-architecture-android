package com.mindorks.framework.mvvm.custom.rtc.webrtc.observers;

import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.RtpReceiver;

import timber.log.Timber;

public class CustomPeerConnectionObserver implements PeerConnection.Observer {

    @Override
    public void onSignalingChange(PeerConnection.SignalingState signalingState) {
        Timber.v("onSignalingChange ", signalingState.toString());
    }

    @Override
    public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
        Timber.v("onIceConnectionChange ", iceConnectionState.toString());
    }

    @Override
    public void onIceConnectionReceivingChange(boolean b) {
        Timber.v("onIceConnectionReceivingChange ", b);
    }

    @Override
    public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
        Timber.v("onIceGatheringChange ", iceGatheringState.toString());
    }

    @Override
    public void onIceCandidate(IceCandidate iceCandidate) {
        Timber.v("onIceCandidate ", iceCandidate.toString());
    }

    @Override
    public void onIceCandidatesRemoved(IceCandidate[] iceCandidates) {
        Timber.v("onIceCandidatesRemoved ");
    }

    @Override
    public void onAddStream(MediaStream mediaStream) {
        Timber.v("onAddStream ");
    }

    @Override
    public void onRemoveStream(MediaStream mediaStream) {
        Timber.v("onRemoveStream ");
    }

    @Override
    public void onDataChannel(DataChannel dataChannel) {
        Timber.v("onDataChannel ");
    }

    @Override
    public void onRenegotiationNeeded() {
        Timber.v("onRenegotiationNeeded ");
    }

    @Override
    public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreams) {
        Timber.v("onAddTrack ");
    }
}
