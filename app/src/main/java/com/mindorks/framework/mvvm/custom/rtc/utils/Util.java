package com.mindorks.framework.mvvm.custom.rtc.utils;

import com.mindorks.framework.mvvm.custom.common.IceServerType;

import org.webrtc.PeerConnection;
import org.webrtc.SessionDescription;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static SessionDescription.Type convertCustomTypeToSessionType(String type) {
        switch (type) {
            case Type.OFFER:
                return SessionDescription.Type.OFFER;
            case Type.ANSWER:
                return SessionDescription.Type.ANSWER;
        }
        return null;
    }

    public static String convertSessionTypeToCustomType(SessionDescription.Type type) {
        switch (type) {
            case OFFER:
                return Type.OFFER;
            case ANSWER:
                return Type.ANSWER;
        }
        return null;
    }

    public static List<PeerConnection.IceServer> convertIceServerDataToIceServer(List<IceServerData> iceServerData) {
        List<PeerConnection.IceServer> iceServers = new ArrayList<>();
        for (IceServerData serverData : iceServerData) {
            if (serverData.getIceServerType() == IceServerType.STUN_SERVER) {
                iceServers.add(PeerConnection.IceServer.builder(serverData.getUri()).createIceServer());
            }
            if (serverData.getIceServerType() == IceServerType.TURN_SERVER) {
                iceServers.add(PeerConnection.IceServer.builder(serverData.getUri())
                        .setUsername(serverData.getUserName())
                        .setPassword(serverData.getPassWord())
                        .createIceServer());
            }
        }
        return iceServers;
    }
}
