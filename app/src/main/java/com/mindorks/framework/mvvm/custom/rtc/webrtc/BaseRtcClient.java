package com.mindorks.framework.mvvm.custom.rtc.webrtc;

import android.content.Context;

import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;
import com.mindorks.framework.mvvm.custom.common.CameraType;

import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.Camera1Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.DataChannel;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SdpObserver;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.nio.ByteBuffer;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseRtcClient {

    private final Context context;
    private final PeerConnectionFactory peerConnectionFactory;
    protected PeerConnection peerConnection;

    private VideoCapturer videoCapturer;
    private VideoSource videoSource;
    protected VideoTrack videoTrack;

    private AudioSource audioSource;
    protected AudioTrack audioTrack;

    protected DataChannel dataChannel;

    public int CAMERA_HEIGHT = 1024;
    public int CAMERA_WIDTH = 720;
    public int CAMERA_FRAME_PER_SEC = 30;

    public static final String VIDEO_TAG = "video_tag";
    public static final String AUDIO_TAG = "audio_tag";
    public static final String MEDIA_TAG = "media_tag";
    public static final String DATA_CHANNEL_TAG = "data_channel_tag";


    public BaseRtcClient(@NonNull Context context) {
        if (context == null)
            throw new IllegalArgumentException("Context should not be null");

        this.context = context;
        this.peerConnectionFactory = initiateConnectionFactory();
        if (this.peerConnectionFactory == null)
            throw new NullPointerException("Peerconnection factory should not be null");
    }

    @NonNull
    protected PeerConnectionFactory initiateConnectionFactory() {
        PeerConnectionFactory.InitializationOptions initializationOptions =
                PeerConnectionFactory.InitializationOptions.builder(this.context)
                        .setEnableVideoHwAcceleration(true)
                        .createInitializationOptions();
        PeerConnectionFactory.initialize(initializationOptions);

        return PeerConnectionFactory.builder().createPeerConnectionFactory();
    }

    @Nullable
    public VideoCapturer createCameraCapturer(CameraType cameraType) {
        CameraEnumerator enumerator = new Camera1Enumerator(false);
        final String[] deviceNames = enumerator.getDeviceNames();

        for (String deviceName : deviceNames) {
            if (cameraType == CameraType.FRONT_CAMERA && enumerator.isFrontFacing(deviceName)) {
                return enumerator.createCapturer(deviceName, getCameraEventHandler());
            }

            if (cameraType == CameraType.BACK_CAMERA && enumerator.isBackFacing(deviceName)) {
                return enumerator.createCapturer(deviceName, getCameraEventHandler());
            }
        }
        return null;
    }

    public void startVideoCapturing(@NonNull VideoCapturer videoCapturer) {
        this.videoCapturer = videoCapturer;
        videoSource = peerConnectionFactory.createVideoSource(this.videoCapturer);
        videoTrack = peerConnectionFactory.createVideoTrack(VIDEO_TAG, videoSource);
        this.videoCapturer.startCapture(CAMERA_HEIGHT, CAMERA_WIDTH, CAMERA_FRAME_PER_SEC);
    }

    public void startAudioCapturing() {
        audioSource = peerConnectionFactory.createAudioSource(getAudioMediaConstraints());
        audioTrack = peerConnectionFactory.createAudioTrack(AUDIO_TAG, audioSource);
    }

    public boolean isCameraTypeAvailable(CameraType cameraType) {
        CameraEnumerator enumerator = new Camera1Enumerator(false);
        final String[] deviceNames = enumerator.getDeviceNames();

        // Trying to find camera
        for (String deviceName : deviceNames) {
            if (cameraType == CameraType.FRONT_CAMERA && enumerator.isFrontFacing(deviceName)) return true;
            if (cameraType == CameraType.BACK_CAMERA && enumerator.isBackFacing(deviceName)) return true;
        }
        return false;
    }

    @Nullable
    public VideoTrack getVideoTrack() {
        return videoTrack;
    }

    @Nullable
    public AudioTrack getAudioTrack() {
        return audioTrack;
    }

    public void createPeerConnection() {
        PeerConnection.RTCConfiguration rtcConfig = new PeerConnection.RTCConfiguration(getIceServers());
        peerConnection = peerConnectionFactory.createPeerConnection(rtcConfig, getConnectionObserver());
    }

    public void addStreamToConnection() {
        if (peerConnection == null)
            throw new NullPointerException("Peer connection should not be null");
        MediaStream stream = peerConnectionFactory.createLocalMediaStream(MEDIA_TAG);
        if (videoTrack != null) stream.addTrack(videoTrack);
        if (audioTrack != null) stream.addTrack(audioTrack);
        peerConnection.addStream(stream);
    }

    public void createOffer() {
        if (peerConnection == null)
            throw new NullPointerException("Peer connection is null");
        peerConnection.createOffer(getSdpObserver(), getSdpConstraints());
    }

    public void createAnswer() {
        if (peerConnection == null)
            throw new NullPointerException("Peer connection is null");
        peerConnection.createAnswer(getSdpObserver(), new MediaConstraints());
    }

    public void createDataChannel() {
        if (peerConnection == null)
            throw new NullPointerException("Peer connection is null");
        DataChannel.Init dcInit = new DataChannel.Init();
        dataChannel = peerConnection.createDataChannel(DATA_CHANNEL_TAG, dcInit);
        dataChannel.registerObserver(getDataChannelObserver());
    }

    public void sendMessage(@NonNull byte[] msg) {
        if (dataChannel == null)
            throw new NullPointerException("Data channel is null");
        if (dataChannel.state() == DataChannel.State.OPEN) {
            ByteBuffer buffer = ByteBuffer.wrap(msg);
            dataChannel.send(new DataChannel.Buffer(buffer, false));
        }
    }

    public void closeConnection() {
        // Cleaning video streams
        if (videoCapturer != null) {
            try {
                videoCapturer.stopCapture();
                videoCapturer.dispose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (videoSource != null) videoSource.dispose();
            if (videoTrack != null) videoTrack.dispose();
        }
        // Cleaning audio streams
        if (audioSource != null) {
            audioSource.dispose();
            if (audioTrack != null) audioTrack.dispose();
        }

        // Closing data channel
        if (dataChannel != null && dataChannel.state() == DataChannel.State.OPEN) dataChannel.close();
        // Closing the connection
        if (peerConnection != null) peerConnection.close();

        // Assigning variables to null
        videoCapturer = null;
        videoSource = null;
        videoTrack = null;
        audioSource = null;
        audioTrack = null;
        dataChannel = null;
        peerConnection = null;
    }

    // Abstract methods

    @NonNull public abstract List<PeerConnection.IceServer> getIceServers();

    @NonNull public abstract MediaConstraints getAudioMediaConstraints();

    @NonNull public abstract MediaConstraints getSdpConstraints();

    @NonNull protected abstract CameraVideoCapturer.CameraEventsHandler getCameraEventHandler();

    @NonNull protected abstract PeerConnection.Observer getConnectionObserver();

    @NonNull protected abstract SdpObserver getSdpObserver();

    @NonNull protected abstract DataChannel.Observer getDataChannelObserver();

    public abstract DataChannel.State getDataChannelState();

    public abstract PeerConnection.IceConnectionState getIceConnectionState();

    public abstract boolean muteVideoTrack();

    public abstract boolean muteAudioTrack();

    public abstract void addIceCandidate(@NonNull BaseIceCandidateData baseIceCandidateData);

    public abstract void addSessionDescription(@NonNull BaseSessionDescData baseSessionDescData);
}
