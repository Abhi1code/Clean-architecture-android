package com.mindorks.framework.mvvm.custom.rtc.webrtc;

import android.content.Context;

import com.mindorks.framework.mvvm.custom.rtc.exception.DescriptionFailedException;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.interfaces.RtcInterface;
import com.mindorks.framework.mvvm.custom.rtc.model.IceCandidateRtcData;
import com.mindorks.framework.mvvm.custom.rtc.utils.IceServerData;
import com.mindorks.framework.mvvm.custom.rtc.utils.SdpData;
import com.mindorks.framework.mvvm.custom.rtc.model.SessionDescRtcData;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.observers.CustomCameraEventsHandler;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.observers.CustomDataChannelObserver;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.observers.CustomPeerConnectionObserver;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.observers.CustomSdpObserver;
import com.mindorks.framework.mvvm.custom.rtc.utils.Util;

import org.webrtc.CameraVideoCapturer;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

import java.nio.ByteBuffer;
import java.util.List;

import androidx.annotation.NonNull;

public class DefaultRtcClient extends BaseRtcClient {

    @NonNull private final RtcInterface rtcInterface;
    @NonNull private final List<IceServerData> iceServerData;
    @NonNull private final List<SdpData> sdpData;

    public DefaultRtcClient(@NonNull Context context, @NonNull RtcInterface rtcInterface,
                            @NonNull List<IceServerData> iceServerData, @NonNull List<SdpData> sdpData) {
        super(context);
        if (rtcInterface == null)
            throw new IllegalArgumentException("RTC Interface should not be null");

        this.rtcInterface = rtcInterface;
        this.iceServerData = iceServerData;
        this.sdpData = sdpData;
    }

    @NonNull
    @Override
    public List<PeerConnection.IceServer> getIceServers() {
        return Util.convertIceServerDataToIceServer(iceServerData);
    }

    @NonNull
    @Override
    public MediaConstraints getAudioMediaConstraints() {
        return new MediaConstraints();
    }

    @NonNull
    @Override
    public MediaConstraints getSdpConstraints() {
        MediaConstraints sdpConstraints = new MediaConstraints();
        for (SdpData sdpData : sdpData) {
            sdpConstraints.mandatory.add(new MediaConstraints.KeyValuePair(sdpData.getKey(),
                    sdpData.getValue()));
        }
        return sdpConstraints;
    }

    @NonNull
    @Override
    protected CameraVideoCapturer.CameraEventsHandler getCameraEventHandler() {
        return new CustomCameraEventsHandler();
    }

    @NonNull
    @Override
    protected PeerConnection.Observer getConnectionObserver() {
        return new CustomPeerConnectionObserver() {
            @Override
            public void onIceCandidate(IceCandidate iceCandidate) {
                super.onIceCandidate(iceCandidate);
                if (iceCandidate != null) {
                    rtcInterface.onIceCandidate(new IceCandidateRtcData(iceCandidate.sdp,
                            iceCandidate.sdpMid, iceCandidate.sdpMLineIndex));
                }
            }

            @Override
            public void onAddStream(MediaStream mediaStream) {
                super.onAddStream(mediaStream);
                if (mediaStream != null && mediaStream.videoTracks.size() > 0) {
                    rtcInterface.onRemoteStreamAdded(mediaStream.videoTracks.get(0));
                }
            }

            @Override
            public void onDataChannel(DataChannel channel) {
                super.onDataChannel(channel);
                if (channel == null) return;
                dataChannel = channel;
                dataChannel.registerObserver(getDataChannelObserver());
            }

            @Override
            public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
                super.onIceConnectionChange(iceConnectionState);
                switch (iceConnectionState) {
                    case CONNECTED:
                        rtcInterface.onConnectionOpenStateChange(true);
                        break;
                    case DISCONNECTED:
                        rtcInterface.onConnectionOpenStateChange(false);
                        break;
                }
            }
        };
    }

    @NonNull
    @Override
    protected SdpObserver getSdpObserver() {
        return new CustomSdpObserver() {
            @Override
            public void onCreateSuccess(SessionDescription sessionDescription) {
                super.onCreateSuccess(sessionDescription);
                if (sessionDescription == null) return;
                SessionDescRtcData sessionDescRtcData = new SessionDescRtcData(sessionDescription.description,
                        Util.convertSessionTypeToCustomType(sessionDescription.type));
                rtcInterface.onSessionDescription(sessionDescRtcData);
            }

            @Override
            public void onCreateFailure(String s) {
                super.onCreateFailure(s);
                rtcInterface.onError(new DescriptionFailedException(s));
            }
        };
    }

    @NonNull
    @Override
    protected DataChannel.Observer getDataChannelObserver() {
        return new CustomDataChannelObserver() {
            @Override
            public void onMessage(DataChannel.Buffer buffer) {
                super.onMessage(buffer);
                if (buffer == null) return;
                ByteBuffer data = buffer.data;
                byte[] bytes = new byte[data.remaining()];
                data.get(bytes);
                rtcInterface.onMessage(bytes);
            }

            @Override
            public void onStateChange() {
                super.onStateChange();
                if (dataChannel == null) return;
                switch (dataChannel.state()) {
                    case OPEN:
                        rtcInterface.onDataChannelOpenStateChange(true);
                        break;
                    case CLOSED:
                        rtcInterface.onDataChannelOpenStateChange(false);
                        break;
                }
            }
        };
    }

    @Override
    public void addIceCandidate(@NonNull BaseIceCandidateData baseIceCandidateData) {
        if (peerConnection == null)
            throw new NullPointerException("Peer connection is null");
        peerConnection.addIceCandidate(new IceCandidate(baseIceCandidateData.getSdpMid(),
                baseIceCandidateData.getSdpMLineIndex(), baseIceCandidateData.getSdp()));
    }

    @Override
    public void addSessionDescription(@NonNull BaseSessionDescData baseSessionDescData) {
        if (peerConnection == null)
            throw new NullPointerException("Peer connection is null");
        peerConnection.setRemoteDescription(getSdpObserver(),
                new SessionDescription(Util.convertCustomTypeToSessionType(baseSessionDescData.getType()),
                        baseSessionDescData.getSdp()));
    }

    @Override
    public DataChannel.State getDataChannelState() {
        if (dataChannel == null) return null;
        return dataChannel.state();
    }

    @Override
    public PeerConnection.IceConnectionState getIceConnectionState() {
        if (peerConnection == null) return null;
        return peerConnection.iceConnectionState();
    }

    @Override
    public boolean muteVideoTrack() {
        if (videoTrack == null) return false;
        videoTrack.setEnabled(!videoTrack.enabled());
        return videoTrack.enabled();
    }

    @Override
    public boolean muteAudioTrack() {
        if (audioTrack == null) return false;
        audioTrack.setEnabled(!audioTrack.enabled());
        return audioTrack.enabled();
    }
}
